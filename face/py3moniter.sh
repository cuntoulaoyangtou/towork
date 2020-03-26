#!/bin/bash

while true
	flag=`ps -aux |grep "python3 web_service_example_kunpeng.py" |grep -v "grep" |wc -l`
do
	if [[ $flag -eq 0 ]]
	then
		`nohup python3 web_service_example_kunpeng.py &`
		echo `date` - "web_service_example_kunpeng.py restart" >> running.log
	else
		echo "web_service_example_kunpeng.py is running..." >> /dev/null
	fi
	sleep 5s
done

