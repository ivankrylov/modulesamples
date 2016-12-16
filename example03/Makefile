#
# Example3 for the Java 9 talk
#
# (c)2016, Ivan Krylov
#
#

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home
PATH=${JAVA_HOME}/bin

JAR=${JAVA_HOME}/bin/jar
JAVA=${JAVA_HOME}/bin/java
JAVAC=${JAVA_HOME}/bin/javac
JMOD=${JAVA_HOME}/bin/jmod
JLINK=${JAVA_HOME}/bin/jlink
MODDIR=${JAVA_HOME}/jmods

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
	@tree target
	echo == Time to Run! ==
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A

jarshow: module1jar module2jar
	@echo == What is in mlib ==
	@tree mlib
	@echo == Time to Run jars! ==
	${JAVA} -p mlib -m com.azul.modules.module2

module1jmod: module1
	@echo == Make first jmod ==
	mkdir -p jmod
	${JMOD} create --module-path target/com.azul.modules.module1 --class-path target/com.azul.modules.module1  jmod/module1.jmod

module2jmod: module2
	echo == Make second jmod ==
	mkdir -p jmod
	${JMOD} create --module-path target/com.azul.modules.module2 --class-path target/com.azul.modules.module2 jmod/module2.jmod

Example03: module1
	${JAVAC} --module-path target --add-modules com.azul.modules.module1  -d onlyExample Example03.java

image: module1jmod module2jmod
	@echo == What is in jmod ==
	@tree jmod
	@echo == What is in system jmod ==
	@tree ${MODDIR}
	rm -rf image
	${JLINK} --module-path ${MODDIR}:jmod --add-modules com.azul.modules.module1 --add-modules com.azul.modules.module2  --output image
	@echo == What is in image ==
	@tree image

showimage: Example03 image
	@echo == What is in onlyExample ==
	@tree onlyExample
	@echo == Time to Run Example03! ==
	image/bin/java -cp onlyExample  Example03

clean:
	rm -rf mlib target image jmod onlyExample
