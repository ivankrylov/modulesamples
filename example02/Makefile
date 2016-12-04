JAVA_HOME=/Users/ivan/openjdk9_build/images/jdk-bundle/jdk-9.jdk/Contents/Home/

JAR=${JAVA_HOME}/bin/jar
JAVA=${JAVA_HOME}/bin/java
JAVAC=${JAVA_HOME}/bin/javac

all: show

compile: module1 module2

module1:
	${JAVAC} --module-source-path .  -d mods com.azul.modules.module1/module-info.java
	${JAVAC} --module-source-path .  -d mods com.azul.modules.module1/com/azul/testpackage1/A.java

module2:
	${JAVAC} --module-source-path .  --module-source-path . -d mods com.azul.modules.module2/module-info.java
	${JAVAC} --module-source-path . --module-source-path .  -d mods com.azul.modules.module2/com/azul/testpackage2/A.java

run: compile
	${JAVA} -p mods -m com.azul.modules.module2/com.azul.testpackage2.A

show: compile
	pygmentize -g com.azul.modules.module1/module-info.java
	pygmentize -g com.azul.modules.module1/com/azul/testpackage1/A.java
	pygmentize -g com.azul.modules.module2/module-info.java
	pygmentize -g com.azul.modules.module2/com/azul/testpackage2/A.java
	${JAVA} -p mods -m com.azul.modules.module2/com.azul.testpackage2.A

clean:
	rm -rf mods
