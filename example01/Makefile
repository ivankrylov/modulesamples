JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home

JAR=${JAVA_HOME}/bin/jar
JAVA=${JAVA_HOME}/bin/java
JAVAC=${JAVA_HOME}/bin/javac

all: show

compile: path1.jar path2.jar Example01.class

path1.jar: path1/src/com.azul.test/A.java
	rm -rf path1/dist
	mkdir -p path1/dist
	cd path1/dist ; \
	${JAVAC}  -d . ../src/com.azul.test/A.java ; \
	${JAR} cvf ../../$@ .

path2.jar: path2/src/com.azul.test/A.java
	rm -rf path2/dist
	mkdir -p path2/dist
	cd path2/dist; \
	${JAVAC}  -d . ../src/com.azul.test/A.java ; \
	${JAR} cvf ../../$@ .

Example01.class: path1.jar Example01.java
	${JAVAC}  -cp .:path1.jar Example01.java

run: compile
	${JAVA} -cp .:path1.jar:path2.jar Example01
	${JAVA} -cp .:path1.jar:path2.jar Example02

show: compile
	cat path1/src/com.azul.test/A.java
	cat path2/src/com.azul.test/A.java
	cat ./Example01.java
	${JAVA} -cp .:path1.jar:path2.jar Example01
	${JAVA} -cp .:path2.jar:path1.jar Example01

clean:
	rm -rf path1/dist path2/dist path1.jar path2.jar Example01.class
