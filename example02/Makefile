#
# Example2 for the Java 9 talk
#
# (c)2016, Ivan Krylov
#
#


JAVA_HOME=/Users/ivan/openjdk9_build/images/jdk-bundle/jdk-9.jdk/Contents/Home/
PATH=${JAVA_HOME}/bin

JAR=jar
JAVA=java
JAVAC=javac
JMOD=jmod

all: show

compile: module1 module2

module1:
	@echo == What is in com.azul.modules.module1 ==
	@tree com.azul.modules.module1
	@echo == Let\'s compile ==
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/com/azul/testpackage1/A.java

module2:
	@echo == What is in com.azul.modules.module2 ==
	@tree com.azul.modules.module2
	@echo == Let\'s compile ==
	${JAVAC} --module-source-path .  -d target com.azul.modules.module2/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.module2/com/azul/testpackage2/A.java

module2alt:
	${JAVAC} --module-path target -d target/com.azul.modules.module2 com.azul.modules.module2/module-info.java
	${JAVAC} --module-path target -d target/com.azul.modules.module2 com.azul.modules.module2/com/azul/testpackage2/A.java

module1jar: module1
	mkdir -p mlib
	${JAR} --create --file=mlib/module1.jar -C target/com.azul.modules.module1 .

module2jar: module2
	mkdir -p mlib
	${JAR} --create --file=mlib/module2.jar --main-class=com.azul.testpackage2.A -C target/com.azul.modules.module2 .

run: compile
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A

show: compile
	echo == Listing of source files ==
	pygmentize -g com.azul.modules.module1/module-info.java
	pygmentize -g com.azul.modules.module1/com/azul/testpackage1/A.java
	pygmentize -g com.azul.modules.module2/module-info.java
	pygmentize -g com.azul.modules.module2/com/azul/testpackage2/A.java
	@echo == What is in target ==
	@tree mlib
	echo == Time to Run! ==
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A

jarshow: module1jar module2jar
	@echo == What is in mlib ==
	@tree mlib
	@echo == Time to Run jars! ==
	${JAVA} -p mlib -m com.azul.modules.module2

clean:
	rm -rf mlib target
