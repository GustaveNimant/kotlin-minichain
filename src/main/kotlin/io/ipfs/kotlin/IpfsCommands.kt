package io.ipfs.kotlin

/**
 * gradlew run --args="-ipfs get QmTzX91dhqHRunjCtrt4LdTErREUA5Gg1wFMiJz1bEiQxp"
 *
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
		    val word = 
			if (wor_s.size > 1) {
			    (stringOfGlueOfWordStack(" ", wor_s))
			}
		    else {
			wor_s.toString()
		    }
		    wor_s.clear()

		    println("$here: word '$word'")
		    val proIpH = IpfsHashProvider()
		    val hash = proIpH.provide(word)
		    println("$here: hash '$hash'")
		}
		"get" -> {
		    val qmH = wor_s.toString()
		    wor_s.clear()
		    val ipfsH = IpfsHash(qmH)
		    println("$here: ipfsH '$ipfsH'")
		    val proImm = IpfsImmutableProvider()
		    val immCon = proImm.provide(ipfsH)
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

