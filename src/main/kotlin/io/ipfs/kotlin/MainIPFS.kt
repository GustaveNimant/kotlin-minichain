package io.ipfs.kotlin.main

import io.ipfs.kotlin.*
import io.ipfs.kotlin.defaults.LocalIPFS
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun main(args: Array<String>) {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  ParameterMap = parameterMapOfArguments(args)

  println ("Parameters are:")
  for ( (k, v) in ParameterMap) {
       println ("$k => $v")
  }

  try {
      val multihash = LocalIPFS().add.string("test-string").Hash
      println("$here: multihash $multihash")

      val content = LocalIPFS().get.cat(multihash)
      println("$here: content $content")
      
      val commit = LocalIPFS().info.version()!!.Commit
      println("$here: commit $commit")
      
  }
  catch (e: java.net.ConnectException) {
      fatalErrorPrint ("Connection to 127.0.0.1:5001", "Connection refused", "Check", here)
  }
  
  println("\nnormal termination")
  exiting(here)
}
