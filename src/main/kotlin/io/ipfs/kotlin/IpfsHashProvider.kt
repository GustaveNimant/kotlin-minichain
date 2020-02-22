package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * 
 *
 *
 */

class IpfsHashProvider

    fun build (path: String): IpfsHash {
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
	
	val hash = LocalIpfs().add.string(str).Hash

	val result = IpfsHash(hash)
	println("$here: output result $result")
	
	exiting(here)
	return result 
    }
    
    fun buildAndStore(path: String){
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input path '$path'")
	
	val ipfsH = build(path)
	store(path, ipfsH)
	
	exiting(here)
    }
    
    fun provide(path: String) : IpfsHash {
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
