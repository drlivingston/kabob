# KaBOB Clojure Rule Visualizer #

#this script requires Graphviz in order to create the visualizations

# import needed libraries
import argparse
import networkx as nx
import re
import os



def SpecialChar(trip_list):
    '''
    function takes a list of lists, where each list represents a triple and converts each of the nodes within the
    triples, containing special characters (e.g., [rdfs:subClassOf*]), and converts them to strings so they don't get
    counted as two separate nodes (e.g., '['rdfs:subClassOf*]'
    :param trip_list: list of lists, where each list represents a triple
    :return: list of lists, where each list represents a triple having formatted with special characters accounted for
    '''

    # deal with special characters
    mod_trip = []
    for trip in trip_list: #deal with nodes in []
        if len(trip) > 3:
            good = [None] * 4 # len of triple + 1
            for node in trip:
                if not node.startswith('[') and not node.endswith(']'): #gets nodes in brackets
                    pos = trip.index(node)
                    good[pos] = node
                if node.startswith('['): #gets bracketed nodes by position in triple
                    pos = trip.index(node)
                    if pos > 1 and node not in good: #at end of line
                        good[pos] = ''.join(trip[trip.index(node):len(trip) + 1])
                        good[0:pos] = trip[0:pos]
                    else:  #middle of line
                        good[pos] = ''.join(trip[trip.index(node):trip.index(node) + 2])
            mod_trip.append([x for x in good if x != None])

        elif '!=' in trip: # deal with '!=' nodes
            switched = [trip[1], trip[0], trip[2]]
            mod_trip.append(switched)
        else:
            mod_trip.append([x for x in trip if x])

    return mod_trip



def TripleMaker(rule):
    '''
    function takes a list of of text, composed of parts of triples, from a Clojure rule and performs some string
    formatting (e.g., ?/ice --> ?ice, rdfs/label --> rdfs:label). The function returns a list of lists where each list
    consists of a triple
    :param rule: list of text
    :return: list of lists where each list is a triple
    '''
    # separate out each triple
    r_set = []
    for i in rule[0].split(')'):
        if '/' in i and '@' not in i:
            r_set.append(re.sub(',', ' ', i.strip().split('(')[-1]))

    # split triples into separate lists and clean up formatting
    triples = []
    for rlist in r_set:
        if '/' in rlist and ';' not in rlist:
            triples.append([node.replace('/', '') if node.startswith('?') or node.startswith('_')
                            else node.replace('/', ':') if not node.startswith('[') and not node.startswith('_')
                            else node.replace('/', ':') if '[' and '/' in node
                            else node for node in ''.join(rlist).split(' ')])

    triples = [' '.join(i).split() for i in triples] #remove form nested lists whitespace
    modified_triples = SpecialChar(triples) #deal with special characters

    return modified_triples



def RulesDict(rule_set):
    '''
    function takes a list of rule lists as inputs, processes the rules in an effort to isolate the specific parts that
    are needed for graphical representation (e.g., :name, :head, and :body). The function returns a dictionary
    where the keys are the names of each rule (extracted from the 'name' field of the rule) and the values are a list
    of triple lists
    :param rule_set: list of rules lists
    :return: dictionary
    where the keys are the names of each rule (extracted from the 'name' field of the rule) and the values are a list of
    triple lists
    '''
    rules_dict = {}

    for rule in rule_set:
        if [x for x in rule.split(':') if x.startswith('name')]:
            key = [i for i in [x.strip('name').replace('"', '').strip() for x in rule.split(':') if x.startswith('name')] if x][0]
            rules_dict[key] = {}
            rules_dict[key]['head'] = []; rules_dict[key]['body'] = []
            rules_dict[key]['head'] += TripleMaker([x.strip('head') for x in rule.split(':') if x.startswith('head')])
            rules_dict[key]['body'] += TripleMaker([x.strip('body') for x in rule.split(':') if x.startswith('body')])

    return rules_dict



def GraphMaker(triple_dict):
    '''
    function takes a nested dictionary as input, where the keys represent the name of each rule and the nested
    dictionary for each key contains information to separate the head and body of the rule. The function returns a
    dictionary, keyed by rule name, where the keys are a Networkx graph generator
    :param triple_dict: nested dictionary keyed for each rule; inner dictionary is keyed by head/body of rule with a
    list of triple lists as the values
    :return: dictionary, keyed by rule name, where the keys are a Networkx graph generator
    '''
    graph_dict = {} # create dictionary to store a graph representation for each rule

    # iterate over graph dictionary
    for key, value in triple_dict.items():
        graph = nx.DiGraph() #creates an empty directed graph
        for trip in value['head']: #head of rule - set node and edge attributes
            if str(trip[0]).startswith('?') or str(trip[0]).startswith('['):
                graph.add_node(str(trip[0]), shape='box', label=str(trip[0]), style='filled', color='orange')
            else:
                graph.add_node(str(trip[0]), label=str(trip[0]), shape='ellipse', color='orange')
            if str(trip[2]).startswith('?') or str(trip[2]).startswith('['):
                graph.add_node(str(trip[2]), shape='box', label=str(trip[2]), style='filled', color='orange')
            else:
                graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='orange')

            # set edges and edge attributes
            if 'subClass' in str(trip[1]): #gives subClassOf relations a different a different arrowhead type
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='orange', arrowhead='onormal', arrowsize=1.5)
            elif '!=' in str(trip[1]):
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='red', arrowhead='tee', arrowsize=1.0)
            else:
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='orange', arrowhead='normal', arrowsize=1.0)


        for trip in value['body']: #body of rule - set node and edge attributes
            if str(trip[0]).startswith('?') or str(trip[0]).startswith('['):
                graph.add_node(str(trip[0]),shape='box', label=str(trip[0]), style='filled', color='gray65')
            else:
                graph.add_node(str(trip[0]), label=str(trip[0]), shape='ellipse', color='gray30')
            if str(trip[2]).startswith('?') or str(trip[2]).startswith('['):
                graph.add_node(str(trip[2]),  shape='box', label=str(trip[2]), style='filled', color='gray65')
            else:
                graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='gray30')

            # set edges and edge attributes
            if 'subClass' in str(trip[1]): #gives subClassOf relations a different a different arrowhead type
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='onormal', arrowsize=1.5)
            elif '!=' in str(trip[1]):
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='red', arrowhead='tee', arrowsize=1.0)
            else:
                graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='normal', arrowsize=1.0)

        graph_dict[key] = graph

    return graph_dict



def GraphViewer(graph_dict, output):
    '''
    function takes a dictionary, keyed by rule name, where the keys are a Networkx graph generator as input and returns
    a graphical representation of the rule. The file is named after the invidual rules and output to the location of
    the Clojure rule file
    :param graph_dict: dictionary, keyed by rule name, where the keys are a Networkx graph generator
    :param output: a string containing the file pathway information for outputting the graphical representations
    :return: saves a ng file containing the graphical figure for each rule
    '''

    for key, graph in graph_dict.items():
        title = key.strip(',')

        A = nx.nx_agraph.to_agraph(graph)
        A.graph_attr['label'] = str(title) + ' Rule'
        A.graph_attr['labelfontsize'] = 80.0
        A.graph_attr['fontname'] = 'Arial'
        A.node_attr['fontname'] = 'Arial'
        A.edge_attr['fontname'] = 'Arial'
        A.edge_attr['fontsize'] = 9.0
        A.node_attr['fontsize'] = 9.0
        A.draw(str(output) + '/' + str(title) + '_Rules_figure.png', prog='dot')

    return



def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-a', '--input', help='name/path to rule', required=True)
    args = parser.parse_args()

    # iterate over all files in a directory
    # root = "/Users/tiffanycallahan/Dropbox/GraduateSchool/PhD/LabWork/KaBOB/KaBOB_Dev/kabob/kabob-build/src/main/resources/edu/ucdenver/ccp/kabob/build/rules/entity_typing"
    # files = os.listdir(root)
    for root, dirs, files in os.walk(args.input):
        for file in files:
            if file.endswith(".clj"):
                rule_file = (os.path.join(root, file))
                rules = []
                for line in open(rule_file).readlines():
                    line = re.sub(r'\s*;.*', '', line)
                    rules.append(line.strip())

                rules_mod = [x for x in [x.strip() for x in re.split('{', ','.join(rules))] if x] #split data into list of rules
                rules_mod = [rule for rule in rules_mod if '@' not in rule] #not making figures for rules with macros - REMOVE THIS ONCE RULES UPDATED
                rules_dict = RulesDict(rules_mod) #create dictionary to store triples by rule
                graphs = GraphMaker(rules_dict) #create a graph for each rule - saves to a dictionary
                GraphViewer(graphs, root) #create a visualization for each rule graph

                print 'Created Graphical Representation of Rule: ' + str(rule_file)


if __name__ == '__main__':
    main()