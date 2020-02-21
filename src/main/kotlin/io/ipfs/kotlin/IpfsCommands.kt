package io.ipfs.kotlin

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
		    val hash = provide(word)
		    println("$here: hash '$hash'")
		}
		else -> {
		    fatalErrorPrint ("command were 'add'","'"+wor+"'", "Check input", here)
		} // else
	    } // when
	} // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	
    } // while
    exiting(here)
}

