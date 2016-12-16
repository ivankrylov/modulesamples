#
# 5 examples for my Java 9 talk
#
# (c)2016, Ivan Krylov
#
#

I use a makefile to compile and run. Make should be available on all platforms, but I only tested on Mac. Should work on Linux with almost no modifications. On Windows - probably easier if you have Cygwin installed, otherwise you may need to create cmd or batch program with the same javac / jar / java commands

"make clean" deletes all artifacts in the corresponding directories

Some other commands like "tree" or "pygmentize" may not be available on your machine. It is safe to delete those from the makefiles - they are only for informational purposes.

In all 5 makefiles set JAVA_HOME to where your java 9 is installed (or unzipped).

Description of different tests

Example 1: Demonstrate a simplest case of jar hell. The same app with different value of -cp gives different output

make show

Example2: A simplest jigsaw module example. Module1 exports a packet, module2 requires the first module and uses a class from that exported packet.

make show  - compile and run the example
make jarshow - the same but using modularized jars

Example 3: Show how jimage can be used:
make showimage - create a JRE bundled with module1 and use it to run non-modularized application Example03

Example 4: A negative test. Try to compile an app that uses 2 modules that export packages with the same name. A compilation error is expected

Example 5: 2 modules export packages with the same package and class names. An application creates two Jigsaw layers and reflectively calls public static methods in both classes.

If you have questions - email me at ivankrylov.java @ gmail.com
