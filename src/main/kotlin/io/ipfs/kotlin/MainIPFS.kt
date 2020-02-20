package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun ipfsExecuteOfString(lin: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if(isTrace(here)) println ("$here: input lin '$lin'")
    try {
	val multihash = LocalIPFS().add.string("test-string").Hash
	println("$here: multihash $multihash")
	
	val content = LocalIPFS().get.cat(multihash)
	println("$here: content $content")
	
	val commit = LocalIPFS().info.version()!!.Commit
	println("$here: commit $commit")
	
    }
    catch (e: java.net.ConnectException) {
	fatalErrorPrint ("Connection to 127.0.0.1:5122", "Connection refused", "launch IPFS : go to minichain; . config.sh; ipmsd.sh", here)
  }

  exiting(here)
}

fun main(args: Array<String>) {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  ParameterMap = parameterMapOfArguments(args)

  if (ParameterMap.size == 0) {
      println ("Commands are:")
      val hel_l = helpList()
      for (hel in hel_l) {
	  println (hel)
      }
      exitProcess(0)
  }

  if (ParameterMap.size > 0) {
      println ("Parameter lists are:")
      for ( (k, v) in ParameterMap) {
	  println ("$k => $v")
      }
  }

  val command_set = commandSetOfParameterMap (ParameterMap)
  
  var command_sta = Stack<String>()
  command_set.reversed().forEach {com -> command_sta.push(com) }
  var done = false
  
  while (!done){
      val com = command_sta.pop()
      println("$here: com '$com'")
      val com_3 = com.substring(0,3)

      val lin = ParameterMap.get(com).toString()
      println("$here: lin '$lin'")

      when (com_3) {
	  "hel" -> {printStringList (helpList())}
	  "ipf" -> {
	      ipfsExecuteOfString(lin)
	      }
	  else -> {
	      fatalErrorPrint ("command were one of hel[p], ipf[s], run", "'"+com+"'", "re Run", here)
	      }//catch
      }
      } // while


  println("\nnormal termination")
  exiting(here)
}
