#!/bin/bash

# $1 - load request directory
# $2 - file that was submitted as a load request
#
# This script largely inspired by https://bbs.archlinux.org/viewtopic.php?id=186989
#
main() {
    trap cleanup SIGHUP SIGINT SIGTERM SIGQUIT
    
    local load_request_dir=$1; shift
    local filename=$1; shift

    # monitor the load_request_directory for newly created file. If
    # the load is successful, a file named $filename.success will be
    # created. If the load fails, a file named $filename.error will be
    # created. Also created is a file named $filename.log which
    # contains the load log (useful for debugging if there is a load
    # failure).
    inotifywait -m ${load_request_dir} -e create |
	while read path action file; do
	    case "${file}" in
		"${filename}.success")
		    # if the load was a success, then delete ${file}
		    # and ${file}.success, then call cleanup() to kill
		    # the inotifywait process
		    echo "Load success for file: ${file}"
		    rm "${load_request_dir}/${filename}"
		    rm "${load_request_dir}/${filename}.success"
		    cleanup 0
		    ;;
		"${filename}.error")
		    # the load was a failure, so we call cleanup() to
		    # kill the inotifywait process but return 1
		    # indicating a failure
		    echo "Load failed for file: ${filename}. For details, please see log: $load_request_dir/${filename}.log."
		    cleanup 1
		    ;;
	    esac
	done
}

# kills the process so that the inotifywait process is cleaned up
cleanup() {
    trap - SIGHUP SIGINT SIGTERM SIGQUIT
    echo "dollardollar: $$  BashPID: $BASHPID"
    ps -ef 
    #kill -- $BASHPID
    pkill inotifywait
    exit $1
}

main "$@"
