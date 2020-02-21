package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * 
 *
 *
 */

class IpfsHashProvider

    fun build (path: String): String {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input path '$path'")
	
	val str =
	    if (isFilePathOfWord(path)) {
		stringReadOfFilePath(path)
	    }
	else {
	    path
	}
	
	val result = LocalIpfs().add.string(str).Hash
	println("$here: output result $result")
	
	exiting(here)
	return result 
    }
    
    fun buildAndStore(path: String){
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input path '$path'")
	
	val hash = build(path)
	store(path, hash)
	
	exiting(here)
    }
    
    fun provide(path: String) : String {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input path '$path'")
	
	if (isStored(path)){
	    retrieve(path)
	}
	else {
	    buildAndStore(path)
	}
	
	val result = retrieve(path)
	if (isTrace(here)) println("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun print (path: String) {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	val hash = provide (path)
	println ("Hash: $hash")
	exiting(here)
    }
