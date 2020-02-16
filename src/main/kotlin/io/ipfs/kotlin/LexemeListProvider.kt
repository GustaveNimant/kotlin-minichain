package io.ipfs.kotlin

import java.io.File
import java.util.Stack

object lexemeListRegister {
     var list = mutableListOf<Lexeme>()

     fun isEmpty () : Boolean {
     	 return list.isEmpty()
     }
     
     fun store (lex_l:List<Lexeme>) {
     	 lex_l.forEach {lex -> list.add(lex)}
     }
     
     fun retrieve () : List<Lexeme> {
         val (here, caller) = hereAndCaller()
    	 entering(here, caller)

	 var lex_l = mutableListOf<Lexeme>()
     	 list.forEach {lex -> lex_l.add(lex)}

	 exiting(here)
	 return lex_l
     }
}

fun buildAndStoreLexemeList() {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val ymlFileName = provideAnyFileNameOfWhat ("Yml")
    var lex_l = lexemeListOfFileName(ymlFileName)
    lexemeListRegister.store (lex_l)

    if (isTrace(here)) println("$here: output lexeme List '$lex_l'")
    exiting(here)
}

fun provideLexemeList() : List<Lexeme> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (lexemeListRegister.isEmpty()){
       buildAndStoreLexemeList()
    }
    
    val lex_l = lexemeListRegister.retrieve()

    if (isTrace(here)) println("$here: output lexeme List '$lex_l'")
    exiting(here)
    return lex_l
}

fun printLexemeList () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val lex_l = provideLexemeList ()
    val str_l = fullnameListOfLexemeList (lex_l)
    val content = stringOfGlueOfStringList ("\n", str_l)

    println ("List of Lexemes from Yml file")
    println (content)
    exiting(here)
}

fun writeLexemeList () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val lex_l = provideLexemeList ()
    val str_l = fullnameListOfLexemeList (lex_l)
    val content = stringOfGlueOfStringList ("\n", str_l)

    val lexFileName = provideAnyFileNameOfWhat("Lexeme")
    outputWrite (lexFileName, content)

    val siz = lex_l.size
    println("$here: $siz lexemes written to File '$lexFileName'")
    exiting(here)
}

