set PATH=C:\Program Files\Java\jdk1.6.0_03\bin;%PATH%
javac snakefarm/*.java
jar cmf manifest.mf Kigyofarm.jar snakefarm/*.class
