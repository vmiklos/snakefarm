set PATH=C:\Program Files\Java\jdk1.6.0_03\bin;%PATH%
javac snakefarm/*.java snakefarm/creators/*.java snakefarm/viewfactories/*.java snakefarm/views/*.java
jar cmf manifest.mf Kigyofarm.jar snakefarm/*.class snakefarm/creators/*.class snakefarm/viewfactories/*.class snakefarm/views/*.class
