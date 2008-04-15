#!/bin/sh -e
javac *.java
jar cmf manifest.mf TestCompare.jar *.class
