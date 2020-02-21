package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

data class IpfsHash (val path: String, val hash:String) {

    fun isEmpty (): Boolean {
	return hash.isNullOrEmpty() || path.isNullOrEmpty()
    }

}

object IpfsHashRegister {
    var value: IpfsHash = IpfsHash ("", "")
    
    fun isEmpty (): Boolean {
	val (here, caller) = hereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input value $value")

     	val result = value.isEmpty()

	if(isTrace(here)) println ("$here: output result $result")
		
     	return result
    }
    
    fun store (ipfsH:IpfsHash) {
	val (here, caller) = hereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input ipfsH '$ipfsH'")
	value = ipfsH
	if(isTrace(here)) println ("$here: ipfsH stored as value")
     }
    
    fun retrieve(): IpfsHash {
        val (here, caller) = hereAndCaller()
    	entering(here, caller)

	var result = value
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
}

fun buildOfPath (path: String): String {
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

    val result = LocalIPFS().add.string(str).Hash
    println("$here: output result $result")
    
    exiting(here)
    return result 
}

fun storeOfPathOfHash(path: String, hash: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val result : IpfsHash = IpfsHash (path, hash)
    println("$here: result $result")
    
    IpfsHashRegister.store (result) 

    exiting(here)
}

fun buildAndStoreOfPath(path: String){
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("$here: input path '$path'")
    
    val hash =  buildOfPath (path)
    println("$here: hash $hash")
    storeOfPathOfHash(path, hash)

    exiting(here)
}

fun provideIpfsHash(path: String) : IpfsHash {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    println("$here: input path '$path'")

    if (IpfsHashRegister.isEmpty()){
       buildAndStoreOfPath(path)
    }
    
    val result = IpfsHashRegister.retrieve()
    if (isTrace(here)) println("$here: output result '$result'")

    exiting(here)
    return result
}

fun printIpfsHash (path: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val hash = provideIpfsHash (path)
    println ("Hash: $hash")
    exiting(here)
}

