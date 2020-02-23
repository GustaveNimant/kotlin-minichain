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

fun parameterMapOfArguments(args: Array<String>): MutableMap<String, MutableList<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  println("$here: input args:")
  printOfStringArray(args)
  
  var stack = Stack<String>()
  args.reversed().forEach ({s -> stack.push(s)})

  val arg_siz = args.size
  val str = stringOfGlueOfStringList (" ", args.toList())

  if (arg_siz == 0) {
      exiting(here)
      return mutableMapOf ()
  }
  if (arg_siz < 2) {
      exiting(here)
      return mutableMapOf ()
  }

/*
 initialize
*/
  val arg_0 = stack.pop()
  var command = arg_0.substring(1).toLowerCase()
  val arg_one = stack.pop()

  try {
    if (! arg_0.startsWith('-')) {
      fatalErrorPrint("First character in arguments were '-'", "Arguments are '$str'", "Reset arguments", "main") 
    }
  }
  catch (e: java.lang.ArrayIndexOutOfBoundsException) {
      fatalErrorPrint("There were arguments", "No Arguments", "Set arguments to program", "main") 
  }

/*
 loop on all arguments
*/
   
  println("$here: after first step command '$command'")

  var Done = false
  var arg_l = mutableListOf(arg_one)
  println("$here: after first step arg_l:")
  printOfStringList(arg_l)

  var ParameterMap = mutableMapOf (command to arg_l)

  while (!Done) {
     try {
       var arg = stack.pop()
       println("$here: while arg '$arg'")
       
       if (arg.startsWith('-')) {
           command = arg.substring(1).toLowerCase()
	   println("\n$here: while command  set as '$command'")
	   if(ParameterMap.contains(command)) {
	       val str_ = command.substring(3)
	       println("$here: Warning: command '$command' is repeated")
	       println("$here: Warning: to avoid this, modify the end command name '$command' from its 4th character (i.e. modify '$str_')")
	       command = command + "_"
	       println("$here: Warning: command has been currently modified to '$command'") 
	   }
	   
	   arg_l = mutableListOf()
       }
       else {
	   arg_l.add (arg)
	   ParameterMap.set(command, arg_l)
	   println("$here: command '$command' as been stored with arg_l '$arg_l'")
       }
     }
     catch (e: java.util.EmptyStackException) {Done=true}
   }

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
	fatalErrorPrint ("Connection to 127.0.0.1:5122", "Connection refused", "launch Ipfs : go to minichain jsm; . config.sh; ipmsd.sh", here)}
    
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
