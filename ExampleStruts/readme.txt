Java2Word Generator - Struts Application Example

NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED
NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED NON-MAVENIZED

Open the ant/build.xml and change the server address according to your needs.

STEPS:

1 - Rename the file build.properties.SMAPLE to build.properties
2 - Open your new build.properties and change your JBoss deployment folder.
3 - Add the JBoss runtime server libraries to your project. Right click on the project/properties, click in "Java build properties".
Click in "Add Library", server runtime and select your JBoss Server.


I tested this in JBoss 5.1.0.GA - xstream-1.3.1.jar is needed if you use Image component in your code

Tomcat 6 - You need xstream-1.3.1.jar and log4j 


