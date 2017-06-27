# KaBOB Clojure Rule Visualizer #

#this script requires Graphviz in order to create the visualizations

# import needed libraries
import argparse
import networkx as nx
import re
import os
import graphviz as gv



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


def RuleSplitter(rule, start, stop):
    '''
    function takes a string as well as two sub-strings and returns the string between the sub-strings
    :param rule: string representing a rule
    :param start: sub-string that is present in rule
    :param stop: sub-string that is present in rule
    :return: sub-string from rule that was between the start and stop sub-strings
    '''
    result = re.search(str(start) + '(.*)' + str(stop), rule)
    return result.group(1)


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

    triples = [' '.join(i).split() for i in triples]  # remove form nested lists whitespace
    modified_triples = SpecialChar(triples)  # deal with special characters

    return modified_triples


def TripleMaker_SS(rule):
    '''
    function takes a list of of text, composed of parts of triples, from a Clojure rule and performs some string
    formatting (e.g., ?/ice --> ?ice, rdfs/label --> rdfs:label). The function returns a list of lists where each list
    consists of a triple (SPARQL-STRING Version)
    :param rule: list of text
    :return: list of lists where each list is a triple
    '''
    if 'select' in rule: # accounts for sparql-string formatting
        mod_triples = []

        if 'optional' in rule:
            rule = rule.split('optional')[0]
        if 'contains' in rule:
            rule = rule.split('contains')[0]

        if 'bind' in rule.lower():
            triple = [y for y in
                      filter(None, [x.strip('"') for x in rule.lower().split('bind')[:-1][0].split(',') if 'select' not in x.lower()])
                      if '?' in y]
            triple = [x.split("#")[0].strip(' .') for x in triple if not 'filter' in x and "'" not in x and '&&' not in x]
        else:
            triple = [y for y in filter(None, [x.strip('"') for x in rule.split(',') if 'select' not in x.lower()]) if '?' in y]
            triple = [x.split("#")[0].strip(' .') for x in triple if not 'filter' in x and "'" not in x and '&&' not in x]

        for i in triple:
            if 'minus' in i:
                trip = i.split('{')[-1].rstrip().strip('}').split(' ')
                trip[0] = '-' + str(trip[0])
                mod_triples.append(trip[:3])
            elif 'optional' in i:
                trip = i.split('{')[-1].rstrip().strip('}').split(' ')
                trip[0] = 'o' + str(trip[0])
                # trip[0] = str(trip[0])
                mod_triples.append(trip[:3])
            else:
                mod_triples.append([x.strip('}').strip("'") for x in filter(None, i.strip('.').split(' '))])

        return mod_triples

    if '(' in rule:
        r_set = []
        for i in rule.split(')'):
            if '/' in i and '@' not in i:
                r_set.append(re.sub(',', ' ', i.strip().split('(')[-1]))
        # split triples into separate lists and clean up formatting
        triples = []
        for rlist in r_set:
            if '/' in rlist and ';' not in rlist:
                triples.append([node.replace('/', '') if
                                node.startswith('?') or
                                node.startswith('_')
                                else node.replace('/', ':') if not node.startswith('[') and not node.startswith('_')
                else node.replace('/', ':') if '[' and '/' in node
                else node for node in ''.join(rlist).split(' ')])

        triples = [x for x in [' '.join(i).split() for i in triples] if
                   not any([y.startswith(':') for y in x]) and not len(x) > 3]  # remove form nested lists whitespace
        mod_triples2 = SpecialChar(triples)  # deal with special characters

        return mod_triples2


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
            key = [i for i in [x.strip('name').replace('"', '').strip() for x in rule.split(':') if x.startswith('name')] if x][0].strip(',')

            rules_dict[key] = {}
            rules_dict[key]['head'] = []; rules_dict[key]['body'] = []; rules_dict[key]['description'] = []
            rules_dict[key]['head'] += TripleMaker([x.strip('head') for x in rule.split(':') if x.startswith('head')])
            rules_dict[key]['body'] += TripleMaker([x.strip('body') for x in rule.split(':') if x.startswith('body')])
            rules_dict[key]['description'] = [x.split('"')[1] for x in rule.split(':') if x.startswith('description')][0]

    return rules_dict


def RulesDict_SS(rule_set):
    '''
    function takes a list of rule lists as inputs, processes the rules in an effort to isolate the specific parts that
    are needed for graphical representation (e.g., :name, :head, and :body). The function returns a dictionary
    where the keys are the names of each rule (extracted from the 'name' field of the rule) and the values are a list
    of triple lists (SPARQL-STRING Version)
    :param rule_set: list of rules lists
    :return: dictionary
    where the keys are the names of each rule (extracted from the 'name' field of the rule) and the values are a list of
    triple lists
    '''
    rules_dict = {}
    for rule in rule_set:
        if 'name' in rule:
            key = re.sub('[", ]', '', RuleSplitter(rule, ':name', ':')).split(':')[0]

            rules_dict[key] = {}
            rules_dict[key]['head'] = []; rules_dict[key]['sparql-string'] = []; rules_dict[key]['description'] = []
            rules_dict[key]['head'] += TripleMaker_SS(RuleSplitter(rule, ':head', ':sparql-string').split(':reify')[0].strip())
            rules_dict[key]['sparql-string'] += [x for x in TripleMaker_SS(RuleSplitter(rule, ':sparql-string', '"')) if ':reify' not in x]
            rules_dict[key]['description'] = [x.split('"')[1] for x in rule.split(':') if x.startswith('description')][0]

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

        if 'body' in value.keys():
            for trip in value['body']: #body of rule - set node and edge attributes
                if str(trip[0]).startswith('?') or str(trip[0]).startswith('['):
                    graph.add_node(str(trip[0]),shape='box', label=str(trip[0]), style='filled', color='gray65')
                if not str(trip[0]).startswith('=') and not (str(trip[0]).startswith('?') or str(trip[0]).startswith('[')):
                    graph.add_node(str(trip[0]), label=str(trip[0]), shape='ellipse', color='gray30')
                if str(trip[2]).startswith('?') or str(trip[2]).startswith('['):
                    graph.add_node(str(trip[2]),  shape='box', label=str(trip[2]), style='filled', color='gray65')
                else:
                    graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='gray30')


                # set edges and edge attributes
                if 'subClass' in str(trip[1]): #gives subClassOf relations a different a different arrowhead type
                    graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='onormal', arrowsize=1.5)
                elif '!=' in str(trip[1]):
                    graph.add_edge(str(trip[0]), str(trip[2]), label='!equal', color='red', arrowhead='tee', arrowsize=1.0)
                elif '=' in str(trip[0]):
                    graph.add_edge(str(trip[1]), str(trip[2]), label='equal', color='gray65', arrowhead='tee', arrowsize=1.0)
                else:
                    graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='normal', arrowsize=1.0)

        if 'sparql-string' in value.keys():
            for trip in value['sparql-string']: #body of rule - set node and edge attributes
                if 'subClass' in str(trip[1]):
                    if str(trip[0]).startswith('-'):  # gives subClassOf relations a different a different arrowhead type
                        graph.add_edge(str(trip[0].strip('-')), str(trip[2]), label=str(trip[1]), color='red', arrowhead='onormal', arrowsize=1.5)
                        if str(trip[0].strip('-')).startswith('?'):
                            graph.add_node(str(trip[0].strip('-')), shape='box', style='filled', label=str(trip[0].strip('-')), color='red')
                        else:
                            graph.add_node(str(trip[0].strip('-')), shape='ellipse', label=str(trip[0].strip('-')), color='red')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='red')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='red')

                    elif str(trip[0]).startswith('o'):
                        graph.add_edge(str(trip[0].strip('o')), str(trip[2]), label=str(trip[1]), color='green', arrowhead='onormal', arrowsize=1.5)
                        if str(trip[0].strip('o')).startswith('?'):
                            graph.add_node(str(trip[0].strip('o')), shape='box', style='filled', label=str(trip[0].strip('o')), color='green')
                        else:
                            graph.add_node(str(trip[0].strip('o')), shape='ellipse', label=str(trip[0].strip('o')), color='green')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='green')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='green')
                    else:
                        graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='onormal', arrowsize=1.5)
                        if str(trip[0]).startswith('?'):
                            graph.add_node(str(trip[0]), shape='box', style='filled', label=str(trip[0]), color='gray65')
                        else:
                            graph.add_node(str(trip[0]), shape='ellipse', label=str(trip[0]), color='gray65')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='gray65')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='gray65')

                if 'subClass' not in str(trip[1]):
                    if str(trip[0]).startswith('-'):  # gives subClassOf relations a different a different arrowhead type
                        graph.add_edge(str(trip[0].strip('-')), str(trip[2]), label=str(trip[1]), color='red', arrowhead='normal', arrowsize=1.0)
                        if str(trip[0].strip('-')).startswith('?'):
                            graph.add_node(str(trip[0].strip('-')), shape='box', style='filled', label=str(trip[0].strip('-')), color='red')
                        else:
                            graph.add_node(str(trip[0].strip('-')), shape='ellipse', label=str(trip[0].strip('-')), color='red')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='red')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='red')

                    elif str(trip[0]).startswith('o'):
                        graph.add_edge(str(trip[0].strip('o')), str(trip[2]), label=str(trip[1]), color='green', arrowhead='normal', arrowsize=1.0)
                        if str(trip[0].strip('o')).startswith('?'):
                            graph.add_node(str(trip[0].strip('o')), shape='box', style='filled', label=str(trip[0].strip('o')), color='green')
                        else:
                            graph.add_node(str(trip[0].strip('o')), shape='ellipse', label=str(trip[0].strip('o')), color='green')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='green')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='green')
                    else:
                        graph.add_edge(str(trip[0]), str(trip[2]), label=str(trip[1]), color='gray65', arrowhead='normal', arrowsize=1.0)
                        if str(trip[0]).startswith('?'):
                            graph.add_node(str(trip[0]), shape='box', style='filled', label=str(trip[0]), color='gray65')
                        else:
                            graph.add_node(str(trip[0]), shape='ellipse', label=str(trip[0]), color='gray65')
                        if str(trip[2]).startswith('?'):
                            graph.add_node(str(trip[2]), shape='box', style='filled', label=str(trip[2]), color='gray65')
                        else:
                            graph.add_node(str(trip[2]), label=str(trip[2]), shape='ellipse', color='gray65')

        #add legend
        graph.add_node('bodyConstant', shape='ellipse', label='bodyConstant', color='gray65')
        # head + not subclass
        graph.add_node('?headVar', shape='box', style='filled', label='?headVar', color='orange')
        graph.add_edge('?headVar', 'bodyConstant', label='subClassOf', color='orange', arrowhead='onormal', arrowsize=1.5)
        # minus + not subclass
        graph.add_node('?var{MINUS}', shape='box', style='filled', label='?var{MINUS}', color='red')
        graph.add_edge('?var{MINUS}', 'bodyConstant', label='!subClassOf', color='red', arrowhead='normal', arrowsize=1.0)
        # option
        graph.add_node('?bodyVar', shape='box', style='filled', label='?bodyVar', color='gray65')
        graph.add_edge('bodyConstant', '?bodyVar', label='!equal', color='red', arrowhead='tee', arrowsize=1.0)
        # graph.add_node('?var{OPTION}', shape='box', style='filled', label='?var{OPTION}', color='green')
        # graph.add_edge('?var{OPTION}', 'Constant', label='subClassOf', color='green', arrowhead='onormal', arrowsize=1.5)

        graph_dict[(key, value['description'])] = graph

    return graph_dict



def GraphViewer(graph_dict, output, file_name):
    '''
    function takes a dictionary, keyed by rule name, where the keys are a Networkx graph generator as input and returns
    a graphical representation of the rule. The file is named after the individual rules and output to the location of
    the Clojure rule file
    :param graph_dict: dictionary, keyed by rule name, where the keys are a Networkx graph generator
    :param output: a string containing the file pathway information for outputting the graphical representations
    :param file_name: a string containing the name file
    :return: saves a png file containing the graphical figure for each rule
    '''

    for key, graph in graph_dict.items():
        A = nx.nx_agraph.to_agraph(graph)
        A.graph_attr['label'] = str(key[0]) + ' rule'
        A.graph_attr['labelfontsize'] = 80.0
        A.graph_attr['fontname'] = 'Arial'
        A.node_attr['fontname'] = 'Arial'
        A.edge_attr['fontname'] = 'Arial'
        A.edge_attr['fontsize'] = 9.0
        A.node_attr['fontsize'] = 9.0

        A.draw(str(output) + '/' + str(file_name) + '.png', prog='dot')

    return



def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-a', '--input', help='name/path to rule', required=True)
    args = parser.parse_args()

    # iterate over all files in a directory
    # root = "/Users/tiffanycallahan/Dropbox/GraduateSchool/PhD/LabWork/KaBOB/KaBOB_Dev/kabob/resources/rules"
    # files = os.listdir(root)
    for root, dirs, files in os.walk(args.input):
        for file in files:
            if 'deprecated' not in os.path.join(root, file) and file.endswith(".clj"):
                rule_file = (os.path.join(root, file))
                rules = []

                for line in open(rule_file).readlines():
                    line = re.sub(r'\s*;.*', '', line)
                    rules.append(line.strip())

                if any([x for x in rules if ':sparql-string' in x]): # sparql-string
                    rules_mod = [x for x in [x.strip() for x in re.split('`', ','.join(rules))] if x] #split data into list of rules
                    rules_dict = RulesDict_SS(rules_mod) #create dictionary to store triples by rule
                    graphs = GraphMaker(rules_dict) #create a graph for each rule - saves to a dictionary
                    GraphViewer(graphs, root, file.split('.')[0]) #create a visualization for each rule graph
                    # print "sparql-string: " + str(rule_file)

                if any([x for x in rules if ':body' in x]): # body
                    rules_mod = [x for x in [x.strip() for x in re.split('{', ','.join(rules))] if x]  # split data into list of rules
                    rules_dict = RulesDict(rules_mod) #create dictionary to store triples by rule
                    graphs = GraphMaker(rules_dict) #create a graph for each rule - saves to a dictionary
                    GraphViewer(graphs, root, file.split('.')[0]) #create a visualization for each rule graph
                    # print "body: " + str(rule_file)

                print 'Created Graphical Representation of Rule: ' + str(rule_file)


if __name__ == '__main__':
    main()