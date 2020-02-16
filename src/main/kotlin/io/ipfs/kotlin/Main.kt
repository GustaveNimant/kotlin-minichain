package io.ipfs.kotlin

import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun main(args: Array<String>) {
  val here = functionName()
  entering(here, "Parser")

  ParameterMap = parameterMapOfArguments(args, here)

  println ("Parameters are:")
  for ( (k, v) in ParameterMap) {
       println ("$k => $v")
  }

  writeLexemeList (here)
  
  println("\nnormal termination")
  exiting(here)
}
