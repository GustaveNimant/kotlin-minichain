package io.ipfs.kotlin

import java.util.Stack

/**
 * Execution : gradlew run --args="-ipfs get QmTzX91dhqHRunjCtrt4LdTErREUA5Gg1wFMiJz1bEiQxp" val multihash = LocalIPFS().add.string("test-string").Hash
 * val content = LocalIPFS().get.cat(multihash)
 * val commit = LocalIPFS().info.version()!!.Commit
 * Author : François Colonna 22 février 2020 at 15:32:44+01:00
 */

fun ipfsExecuteOfWordList(wor_l: List<String>) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    // Ex.: -ipfs add truc much
    var done = false
    if(isTrace(here)) println ("$here: input wor_l '$wor_l'")
    var wor_s = wordStackOfWordList(wor_l)
    
    while (!done) {
	try {
	    val wor = wor_s.pop()
	    val wor_3 = wor.substring(0,3)
	    println("$here: wor '$wor'")
	    
	    when (wor_3) {
		"add" -> {
		    val hash = ipfsHashOfAddWordStack(wor_s)
		    println ("Hash: $hash")
		}
		"get" -> {
		        wor_s.clear()
			val immCon = ipfsImmutableContentOfGetWordList(wor_l)
			println ("Content:")
			println (immCon.toString())
    		}
		else -> {
		    fatalErrorPrint ("command were 'add', 'get'","'"+wor+"'", "Check input", here)
		} // else
	    } // when
	} // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	
    } // while
    exiting(here)
}

fun ipfsHashOfAddWordStack (wor_s: Stack<String>): IpfsHash {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val word = if (wor_s.size > 1) {
	(stringOfGlueOfWordStack(" ", wor_s))
    }
    else {
	wor_s.toString()
    }
    wor_s.clear()
    
    println("$here: word '$word'")
    val proIpH = IpfsHashProvider()
    val result = proIpH.provide(word)

    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}
    
fun ipfsImmutableContentOfGetWordList (wor_l: List<String>): IpfsImmutableContent {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("$here: wor_l '$wor_l'")
    val worH =
	if (wor_l.size == 2) {
	    wor_l[1]
	}
    else {
	val str = stringOfGlueOfStringList("\n", wor_l)
	fatalErrorPrint ("one element in get input", str, "Check input", here)
    }
    
    val ipfsH = ipfsHashOfString(worH)
    println("$here: ipfsH '$ipfsH'")
    val proImm = IpfsImmutableProvider()
    val result = proImm.provide(ipfsH)

    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}


