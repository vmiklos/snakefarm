#!/bin/sh -e

javac snakefarm/*.java
jar cmf manifest.mf Kigyofarm.jar snakefarm/*.class
cd ../../../tests/compare
./compile.sh
