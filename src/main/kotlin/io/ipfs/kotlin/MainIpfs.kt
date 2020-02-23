package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun endProgram () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("\nnormal termination")
    exiting(here)
}

fun ipfsExecute (wor_l: List<String>) {
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
		"ipf" -> {ipfsExecute(wor_l)}
		"deb", "loo", "tra", "whe" -> {
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
