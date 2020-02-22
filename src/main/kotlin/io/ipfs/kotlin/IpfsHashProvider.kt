package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * Provide : the QmHash for a file path or string
 * Done    : LocalIpfs().add.string(str).Hash
 * Command : gradlew run --args="-ipfs add truc much" 
 * Command : gradlew run --args="-ipfs add /home/achadde/profile_achadde"
 * Author  : François Colonna 22 février 2020 at 10:32:18+01:00;
 */

class IpfsHashProvider {

    val register = IpfsHashRegister()
    
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
	register.store(path, ipfsH)
	
	exiting(here)
    }
    
    fun provide(path: String) : IpfsHash {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input path '$path'")
	
	if (register.isStored(path)){
	    register.retrieve(path)
	}
	else {
	    buildAndStore(path)
	}
	
	val result = register.retrieve(path)
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

    }
