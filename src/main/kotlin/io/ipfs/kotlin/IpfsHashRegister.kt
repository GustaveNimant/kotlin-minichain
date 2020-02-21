package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * 
 *
 *
 */

class IpfsHashRegister
    
    var register : MutableMap<String, String> = mutableMapOf<String, String>()

fun isEmpty (): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val result = register.isEmpty()
    
    if(isTrace(here)) println ("$here: output result $result")
    
    return result
}

fun store (path: String, hash: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input path '$path'")
    if(isTrace(here)) println ("$here: input hash '$hash'")
    
    if (isStored(path)) {
	val value = retrieve(path)
	if (value != hash) {
	    fatalErrorPrint("hash already stored for path '$path' were equal to new one", hash, "Check", here)
		}
    }
    else {
	register.put(path, hash)
    }
    if(isTrace(here)) println ("$here: ipfsH couple has been stored")
}

fun isStored (path: String): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input path '$path'")
    val result = register.contains(path) && (! (register.get(path)).isNullOrEmpty())
    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}

fun retrieve (path: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val result = register.get(path).toString()
    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}
