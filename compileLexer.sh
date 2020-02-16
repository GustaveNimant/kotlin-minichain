#!/bin/bash
kotlinc MyLibrary.kt -include-runtime -d MyLibrary.jar
kotlinc -classpath MyLibrary.jar Lexeme.kt -include-runtime -d Lexeme.jar
kotlinc -classpath MyLibrary.jar:Lexeme.jar Lexer.kt -include-runtime -d Lexer.jar
kotlinc -classpath MyLibrary.jar:Lexeme.jar:Lexer.jar LexemeListProvider.kt -include-runtime -d LexemeListProvider.jar
kotlinc -classpath MyLibrary.jar:Lexer.jar:Lexeme.jar:LexemeListProvider.jar MainLexer.kt -include-runtime -d MainLexer.jar 



java -esa --class-path MyLibrary.jar:Lexer.jar:Lexeme.jar:LexemeListProvider.jar:MainLexer.jar MainLexerKt -trace all -loop all -when all  | tee o
