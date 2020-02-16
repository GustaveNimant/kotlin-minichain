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
     
     fun retrieve (caller: String) : List<Lexeme> {
         val here = functionName()
    	 entering(here, caller)

	 var lex_l = mutableListOf<Lexeme>()
     	 list.forEach {lex -> lex_l.add(lex)}

	 exiting(here)
	 return lex_l
     }
}

fun buildAndStoreLexemeList(caller: String) {
    val here = functionName()
    entering(here, caller)

    val ymlFileName = provideAnyFileNameOfWhat ("Yml", here)
    var lex_l = lexemeListOfFileName(ymlFileName, here)
    lexemeListRegister.store (lex_l)

    if (isTrace(here)) println("$here: output lexeme List '$lex_l'")
    exiting(here)
}

fun provideLexemeList(caller: String) : List<Lexeme> {
    val here = functionName()
    entering(here, caller)

    if (lexemeListRegister.isEmpty()){
       buildAndStoreLexemeList(here)
    }
    
    val lex_l = lexemeListRegister.retrieve(here)

    if (isTrace(here)) println("$here: output lexeme List '$lex_l'")
    exiting(here)
    return lex_l
}

fun printLexemeList (caller: String) {
    val here = functionName()
    entering(here, caller)

    val lex_l = provideLexemeList (here)
    val str_l = fullnameListOfLexemeList (lex_l)
    val content = stringOfGlueOfStringList ("\n", str_l)

    println ("List of Lexemes from Yml file")
    println (content)
    exiting(here)
}

fun writeLexemeList (caller: String) {
    val here = functionName()
    entering(here, caller)

    val lex_l = provideLexemeList (here)
    val str_l = fullnameListOfLexemeList (lex_l)
    val content = stringOfGlueOfStringList ("\n", str_l)

    val lexFileName = provideAnyFileNameOfWhat("Lexeme", here)
    outputWrite (lexFileName, content, here)

    val siz = lex_l.size
    println("$here: $siz lexemes written to File '$lexFileName'")
    exiting(here)
}

