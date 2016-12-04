JAVA_HOME=/Users/ivan/openjdk9_build/images/jdk-bundle/jdk-9.jdk/Contents/Home/
PATH=${JAVA_HOME}/bin

JAR=jar
JAVA=java
JAVAC=javac

all: show

compile: module1 module2

module1:
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/module-info.java
	${JAVAC} --module-source-path .  -d target com.azul.modules.module1/com/azul/testpackage1/A.java

module2:
	${JAVAC} --module-source-path .  --module-source-path . -d target com.azul.modules.module2/module-info.java
	${JAVAC} --module-source-path . --module-source-path .  -d target com.azul.modules.module2/com/azul/testpackage2/A.java

module1jar: module1
	mkdir -p mlib
	${JAR} --create --file=mlib/module1.jar -C target/com.azul.modules.module1 .

module2jar: module2
	mkdir -p mlib
	${JAR} --create --file=mlib/module2.jar --main-class=com.azul.testpackage2.A -C target/com.azul.modules.module2 .

run: compile
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A

show: compile
	pygmentize -g com.azul.modules.module1/module-info.java
	pygmentize -g com.azul.modules.module1/com/azul/testpackage1/A.java
	pygmentize -g com.azul.modules.module2/module-info.java
	pygmentize -g com.azul.modules.module2/com/azul/testpackage2/A.java
	${JAVA} -p target -m com.azul.modules.module2/com.azul.testpackage2.A


jarshow: module1jar module2jar
	pygmentize -g com.azul.modules.module1/module-info.java
	pygmentize -g com.azul.modules.module1/com/azul/testpackage1/A.java
	pygmentize -g com.azul.modules.module2/module-info.java
	pygmentize -g com.azul.modules.module2/com/azul/testpackage2/A.java
	ls mlib
	${JAVA} -p mlib -m com.azul.modules.module2

clean:
	rm -rf mods mlib
