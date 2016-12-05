JAVA_HOME=/Users/ivan/openjdk9_build/images/jdk-bundle/jdk-9.jdk/Contents/Home
PATH=${JAVA_HOME}/bin

JAR=jar
JAVA=java
JAVAC=javac
JMOD=${JAVA_HOME}/bin/jmod
JLINK=${JAVA_HOME}/bin/jlink
MODDIR=${JAVA_HOME}/jmods

all: show

compile: module1 module2 moduleMain

module1:
	@echo == What is in com.azul.modules.module1 ==
	@tree com.azul.modules.module1
	@echo == Let\'s compile ==
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/com/azul/testpackage/A.java

module2:
	@echo == What is in com.azul.modules.module2 ==
	@tree com.azul.modules.module2
	@echo == Let\'s compile ==
	${JAVAC} --module-source-path .  -d target com.azul.modules.module2/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.module2/com/azul/testpackage/A.java

moduleMain:
	@echo == What is in com.azul.modules.moduleMain ==
	@tree com.azul.modules.moduleMain
	@echo == Let\'s compile ==
	${JAVAC} --module-source-path .  -d target com.azul.modules.moduleMain/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.moduleMain/com/azul/runpackage/Example04.java

module1jar: module1
	mkdir -p mlib
	${JAR} --create --file=mlib/module1.jar -C target/com.azul.modules.module1 .

module2jar: module2
	mkdir -p mlib
	${JAR} --create --file=mlib/module2.jar -C target/com.azul.modules.module2 .

moduleMainjar: moduleMain
	mkdir -p mlib
	${JAR} --create --file=mlib/moduleMain.jar --main-class=com.azul.runpackage.Example04 -C target/com.azul.modules.moduleMain .

run: compile
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A

show: compile
	echo == Listing of source files ==
	pygmentize -g com.azul.modules.module1/module-info.java
	pygmentize -g com.azul.modules.module1/com/azul/testpackage/A.java
	pygmentize -g com.azul.modules.module2/module-info.java
	pygmentize -g com.azul.modules.module2/com/azul/testpackage/A.java
	@echo == What is in target ==
	@tree mlib
	echo == Time to Run! ==
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage.A

jarshow: module1jar module2jar moduleMainjar
	@echo == What is in mlib ==
	@tree mlib
	@echo == Time to Run jars! ==
	${JAVA} -p mlib -m com.azul.modules.module2

clean:
	rm -rf mlib target image jmod onlyExample
