package io.ipfs.kotlin

import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun main(args: Array<String>) {
  val (here, caller) = hereAndCaller()
  entering(here, "Parser")

  ParameterMap = parameterMapOfArguments(args, here)

  println ("Parameters are:")
  for ( (k, v) in ParameterMap) {
       println ("$k => $v")
  }

  writeLexemeList (here)

  val lex_l = provideLexemeList (here)
  val tree =
      if (hasKeywordPreviousOfLexemeList(lex_l, here)) {
        provideBlockCurrentTreeNode(here)
      } else {
        provideBlockGenesisTreeNode(here)
      }

  println("Parser tree:")
  println(tree)
  
  println("\nnormal termination")
  exiting(here)
}
