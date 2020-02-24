package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun commandSetOfParameterMap (parM: Map<String, List<String>>): Set<String> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if(isTrace(here)) println ("$here: input parM $parM")
    val result = parM.keys

    if(isTrace(here)) println ("$here: output result $result")
    exiting(here)
    return result 
    }

fun endProgram () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("\nnormal termination")
    exiting(here)
}

fun mainMenu () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val command_set = commandSetOfParameterMap (ParameterMap)
    var command_sta = Stack<String>()
    command_set.reversed().forEach {com -> command_sta.push(com) }

    var done = false
    var step = 0 
    while (!done){
	try {
	    step = step + 1
	    println("$here: -------------- step # $step --------------")
	    val com = command_sta.pop()
	    if (isLoop(here)) println("$here: com '$com'")
	    val com_3 = com.substring(0,3)
	    
	    val wor_ml = ParameterMap.get(com)
	    val wor_l = wor_ml!!.map({w -> w.toString()}) 
	    if (isLoop(here)) println("$here: wor_l '$wor_l'")
	    
	    when (com_3) {
		"end", "exi" -> {endProgram()}
		"hel" -> {printOfStringList (helpList())}
		"ipf" -> {wrapperIpfsExecuteOfWordList(wor_l)}
		"deb", "loo", "tra", "ver", "whe" -> {
		    val str = stringOfStringList(wor_ml)
		    println("$here: '$com' activated for '$str' functions")
		}
		else -> {
		    fatalErrorPrint ("command were one of hel[p], ipf[s], run", "'"+com+"'", "re Run", here)
	    }//catch
	    }
	} // try 
	catch (e: java.util.EmptyStackException) {
	    done = true
	}
    } // while
    
    exiting(here)
}

fun helpFromParameters() {
    if (ParameterMap.containsKey("help"))
    { 
      val str_l = ParameterMap.getValue("help")
      val hel_l = helpListOfStringList(str_l)
      printOfStringList(hel_l)
      exitProcess(0)
    }
}

fun commandAndParametersOfStringList(str_l: List<String>): Pair<String, List<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  if(isTrace(here)) println("$here: input str_l $str_l")

  val str = str_l.first()
  println("$here: for str $str")

  val result = 
      if (str.startsWith('-')) {
	  val command = str.substring(1).toLowerCase()
	  println("\n$here: while command set as '$command'")
	  val arg_l = str_l.drop(1)
	  Pair (command, arg_l)
      }
      else {
	  fatalErrorPrint("command starts with '-'",str, "Check", here) 
      }

  if(isTrace(here)) println("$here: output result $result")

  exiting(here)
  return result
}

fun parameterMapOfArguments(args: Array<String>): MutableMap<String, List<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  if(isTrace(here)) println("$here: input args:")
  printOfStringArray(args)

  var ParameterMap = mutableMapOf<String, List<String>>()

  val str_ll = stringListListOfDelimiterOfStringList ("-", args.toList())

  for (str_l in str_ll) {
       println("$here: for str_l $str_l")
       var (command, par_l) = commandAndParametersOfStringList(str_l)
       ParameterMap.put (command, par_l)
       if(ParameterMap.contains(command)) {
	   val str_ = command.substring(3)
	   println("$here: Warning: command '$command' is repeated")
	   println("$here: Warning: to avoid this, modify the end command name '$command' from its 4th character (i.e. modify '$str_')")
	   command = command + "_"
	   println("$here: Warning: command has been currently modified to '$command'") 
       }
    } // for arg_l
  
  exiting(here)
  return ParameterMap
}

fun wrapperIpfsExecuteOfWordList (wor_l: List<String>) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isLoop(here)) println("$here: input wor_l '$wor_l'")
    try {
	ipfsExecuteOfWordList(wor_l)
    }
    catch (e: java.net.ConnectException){
	fatalErrorPrint ("Connection to 127.0.0.1:5122", "Connection refused", "launch Ipfs :\n\tgo to minichain jsm; . config.sh; ipmsd.sh", here)}
    
    exiting(here)
}

fun main(args: Array<String>) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val ParameterMap = parameterMapOfArguments(args)
    
    if (ParameterMap.size == 0) {
	println ("Commands are:")
	val hel_l = helpList()
	for (hel in hel_l) {
	    println (hel)
	}
	exitProcess(0)
    }

    if(isVerbose(here)) {
	if (ParameterMap.size > 0) {
	    println ("Parameter lists are:")
	    for ( (k, v) in ParameterMap) {
		println ("$k => $v")
	    }
	}
    }
    
    mainMenu()
    
    println("\nnormal termination")
    exiting(here)
}
