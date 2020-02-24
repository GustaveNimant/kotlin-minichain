package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * What    : A Map storing the content of an Immutable from its hash.
 * Author  : François Colonna 22 février 2020 at 10:47:03+01:00
 */

class IpfsImmutableRegister {
    
    var register : MutableMap<MultiHashType, IpfsImmutableContent> = mutableMapOf<MultiHashType, IpfsImmutableContent>()

fun isEmpty (): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val result = register.isEmpty()
    
    if(isTrace(here)) println ("$here: output result $result")
    
    return result
}

fun store (mulTyp: MultiHashType, immCon: IpfsImmutableContent) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input mulTyp '$mulTyp'")
    if(isTrace(here)) println ("$here: input immCon '$immCon'")
    
    if (isStored(mulTyp)) {
	val con = retrieve(mulTyp)
	if (con != immCon) {
	    fatalErrorPrint("IpfsImmutableContent already stored for mulTyp '$mulTyp' were equal to new one", immCon.toString(), "Check", here)
		}
    }
    else {
	register.put(mulTyp, immCon)
    }
    if(isTrace(here)) println ("$here: immCon couple has been stored")
}

fun isStored (mulTyp: MultiHashType): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input mulTyp '$mulTyp'")

    val immCon = register.get(mulTyp)
    val result = when (immCon) {
	is IpfsImmutableContent -> register.contains(mulTyp) 
	else -> false
    }

    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}

fun retrieve (mulTyp: MultiHashType): IpfsImmutableContent {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val immCon = register.get(mulTyp)
    val result = when (immCon) {
	is IpfsImmutableContent -> immCon 
	else -> {fatalErrorPrint ("", "", "", here)}
    }
    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}
}
