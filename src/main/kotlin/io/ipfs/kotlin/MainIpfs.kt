package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun commandAndParametersOfStringList(str_l: List<String>): Pair<String, List<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  if(false) println("$here: input str_l $str_l")

  val str = str_l.first()
  if(false) println("$here: for str $str")

  val result = 
      if (str.startsWith('-')) {
	  val command = str.substring(1).toLowerCase()
	  if(false) println("$here: command set as '$command'")
	  val arg_l = str_l.drop(1)
	  Pair (command, arg_l)
      }
      else {
	  fatalErrorPrint("command starts with '-'",str, "Check", here) 
      }

  if(false) println("$here: output result $result")

  exiting(here)
  return result
}

fun commandSetOfParameterMap (parM: Map<String, List<String>>): Set<String> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if(false) println ("$here: input parM $parM")
    val result = parM.keys

    if(false) println ("$here: output result $result")
    exiting(here)
    return result 
    }

fun endProgram () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("\nnormal termination")
    exiting(here)
}

fun mainMenu (parM: Map<String, List<String>>) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if(false) println ("$here: input parM $parM")
    val com_s = commandSetOfParameterMap (parM)
    if(false) println ("$here: com_s $com_s")

    var step = 0
    for (com in com_s) { 
	step = step + 1
	println("$here: ----- command # $step '$com' -----")
	val com_3 = com.substring(0,3)
	
	val wor_ml = parM.get(com)
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
    } // for
    
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

fun parameterMapOfArguments(args: Array<String>): Map<String, List<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  if(false) println("$here: input args $args")

  var ParameterMap = mutableMapOf<String, List<String>>()

  val arg_l = args.toList()
  val str_ll = stringListListOfDelimiterOfStringList ("-", arg_l)
  if(false) println("$here: str_ll $str_ll")
  
  for (str_l in str_ll) {
      if(false) println("$here: for str_l $str_l")
      var (command, par_l) = commandAndParametersOfStringList(str_l)
      if(ParameterMap.contains(command)) {
	  val str_ = command.substring(3)
	  if(false) println("$here: Warning: command '$command' is repeated")
	  if(false) println("$here: Warning: to avoid this, modify the end command name '$command' from its 4th character (i.e. modify '$str_')")
	  command = command + "_"
	  if(false) println("$here: Warning: command has been currently modified to '$command'") 
      }
      ParameterMap.put (command, par_l)
      if(false) println("$here: command '$command' added with par_l $par_l") 
  } // for arg_l

  val result = ParameterMap.toMap()
  if(false) println("$here: output result $result")

  exiting(here)
  return result
}

fun wrapperIpfsExecuteOfWordList (wor_l: List<String>) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (false) println("$here: input wor_l '$wor_l'")
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

    if(false) {
	if (ParameterMap.size > 0) {
	    println ("Commands with their parameter list:")
	    for ( (k, v) in ParameterMap) {
		println ("$k => $v")
	    }
	}
    }
    
    mainMenu(ParameterMap)
    
    println("\nnormal termination")
    exiting(here)
}
