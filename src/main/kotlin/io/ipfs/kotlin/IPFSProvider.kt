package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

data class Ipfs (val key: String, val value: String) {
}
data class IpfsRegister (val key: String, val value: String) {
     var reg = mutableMapOf(key to "")
	 
     fun isEmpty (): Boolean {
     	 return reg.isEmpty()
     }
     
     fun store (){
         reg.put (key, value)
     }
     
     fun retrieve(key: String): String {
         val (here, caller) = hereAndCaller()
    	 entering(here, caller)

	 var value = reg.get(key).toString()

	 exiting(here)
	 return value
     }
}

fun buildAndStoreIpfs(key: String): IpfsRegister {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val hash = LocalIPFS().add.string("test-string").Hash
    println("$here: hash $hash")
    
    val reg = IpfsRegister(key, hash)
    reg.store ()

    exiting(here)
    return reg 
}

fun provideIpfs(key: String) : String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (IpfsRegister.isEmpty()){
       val reg = buildAndStoreIpfs(key)
    }
    
    val result = reg.retrieve(key)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun printIpfs (key: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val hash = provideIpfs (key)
    println ("Hash $hash")
    exiting(here)
}

