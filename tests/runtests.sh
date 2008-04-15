#!/bin/bash
proto="java -jar ../proto/Kigyofarm/src/Kigyofarm.jar"
compare="java -jar compare/TestCompare.jar"

runtest() {
	num=$1
	echo -ne "\033[32m * \033[0mRunning test $num...	"
	$proto test$num.in > curtest
	result=$($compare test$num.out curtest)
	if [[ $result == "PASSED" ]]; then
		echo -e "[\033[32mPASSED\033[0m]"
	else
		echo -e "[\033[31mFAILED\033[0m]"
		diff -uw test$num.out curtest
	fi
}

if [ -z "$1" ]; then
	start=1
	stop=19
else
	start=$1
	stop=$1
fi
for i in $(seq $start $stop); do
	runtest $i;
done
