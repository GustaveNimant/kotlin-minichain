run = $(basename $(arg))

KFLAGS := -include-runtime

KT_FILES = MyLibrary.kt Lexer.kt Parser.kt 
JAR_FILES = MyLibrary.jar Lexer.jar Parser.jar 

all: $(run)

MyLibrary.jar: MyLibrary.kt
	kotlinc $< $(KFLAGS) -d $@

$(run): $(JAR_FILES)
	java -esa --class-path MyLibrary.jar:$(run).jar $(run)Kt -debug main 

%Kt.class: %.kt
	kotlinc $<

%.class: %.java
	javac $<

%.jar: %.kt
	kotlinc -classpath MyLibrary.jar $< $(KFLAGS) -d $@ 

.PHONY: clean

clean:
	rm -rf *.jar
	rm *~

mrproper: clean
	rm -rf $(run)
