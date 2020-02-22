package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * What    : A Map storing the content of an Immutable from its hash.
 * Author  : François Colonna 22 février 2020 at 10:47:03+01:00
 */

class IpfsImmutableRegister {
    
    var register : MutableMap<IpfsHash, IpfsImmutableContent> = mutableMapOf<IpfsHash, IpfsImmutableContent>()

fun isEmpty (): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val result = register.isEmpty()
    
    if(isTrace(here)) println ("$here: output result $result")
    
    return result
}

fun store (ipfsH: IpfsHash, immCon: IpfsImmutableContent) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input ipfsH '$ipfsH'")
    if(isTrace(here)) println ("$here: input immCon '$immCon'")
    
    if (isStored(ipfsH)) {
	val con = retrieve(ipfsH)
	if (con != immCon) {
	    fatalErrorPrint("IpfsImmutableContent already stored for ipfsH '$ipfsH' were equal to new one", immCon.toString(), "Check", here)
		}
    }
    else {
	register.put(ipfsH, immCon)
    }
    if(isTrace(here)) println ("$here: immCon couple has been stored")
}

fun isStored (ipfsH: IpfsHash): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input ipfsH '$ipfsH'")

    val immCon = register.get(ipfsH)
    val result = when (immCon) {
	is IpfsImmutableContent -> register.contains(ipfsH) 
	else -> false
    }

    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}

fun retrieve (ipfsH: IpfsHash): IpfsImmutableContent {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val immCon = register.get(ipfsH)
    val result = when (immCon) {
	is IpfsImmutableContent -> immCon 
	else -> {fatalErrorPrint ("", "", "", here)}
    }
    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}
}
