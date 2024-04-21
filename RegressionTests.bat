echo "Welcome to batch Execution"
cd D:\synechron_REST_API\Eclipse_WS\Training
d:
set path=%path%;D:\maven\apache-maven-3.6.1\bin
set classpath=D:\synechron_REST_API\Eclipse_WS\Training\target\*;.
mvn clean test -Dsurefire.suiteXmlFiles=Regression.xml
pause