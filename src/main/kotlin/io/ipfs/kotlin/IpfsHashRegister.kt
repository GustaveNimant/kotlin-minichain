package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * 
 *
 *
 */

class IpfsHashRegister
    
    var register : MutableMap<String, IpfsHash> = mutableMapOf<String, IpfsHash>()

fun isEmpty (): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val result = register.isEmpty()
    
    if(isTrace(here)) println ("$here: output result $result")
    
    return result
}

fun store (path: String, ipfsH: IpfsHash) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input path '$path'")
    if(isTrace(here)) println ("$here: input ipfsH '$ipfsH'")
    
    if (isStored(path)) {
	val value = retrieve(path)
	if (value != ipfsH) {
	    fatalErrorPrint("IpfsHash already stored for path '$path' were equal to new one", ipfsH.toString(), "Check", here)
		}
    }
    else {
	register.put(path, ipfsH)
    }
    if(isTrace(here)) println ("$here: ipfsH couple has been stored")
}

fun isStored (path: String): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input path '$path'")

    val ipfsH = register.get(path)
    val result = when (ipfsH) {
	is IpfsHash -> register.contains(path) 
	else -> false
    }

    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}

fun retrieve (path: String): IpfsHash {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val ipfsH = register.get(path)
    val result = when (ipfsH) {
	is IpfsHash -> ipfsH 
	else -> {fatalErrorPrint ("", "", "", here)}
    }
    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}
