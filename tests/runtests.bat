@echo off
echo Running test 1...
java -jar ../proto/Kigyofarm/dist/Kigyofarm.jar test1.in >curtest
java -jar compare/TestCompare.jar test1.out curtest

echo Running test 2...
java -jar ../proto/Kigyofarm/dist/Kigyofarm.jar test2.in >curtest
java -jar compare/TestCompare.jar test2.out curtest

echo Running test 3...
java -jar ../proto/Kigyofarm/dist/Kigyofarm.jar test3.in >curtest
java -jar compare/TestCompare.jar test3.out curtest
