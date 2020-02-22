package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*
import io.ipfs.kotlin.IpfsImmutable.*
import io.ipfs.kotlin.IpfsImmutableRegister.*

/**
 * Provision : the content of an immutable file from its Hash
 * Done      : LocalIpfs().get.cat(ipfsH)
 * Needs     : IpfsHash 
 * Needed by : IpfsHash 
 * Command   : gradlew run --args="-ipfs get QmbEm7hDJ9zB22UPnXRGfaWrFoEbJZbHPTEa6udMZ48riz" 
 * Author : François Colonna 22 février 2020 at 11:02:59+01:00
 */

class IpfsImmutableProvider {

    val register = IpfsImmutableRegister()
    
    fun build (ipfsH: IpfsHash): IpfsImmutableContent {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input ipfsH '$ipfsH'")

	val strH = ipfsH.toString()
	val str = LocalIpfs().get.cat(strH)

	val result = IpfsImmutableContent(str)
	println("$here: output result $result")
	
	exiting(here)
	return result 
    }
    
    fun buildAndStore(ipfsH: IpfsHash){
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input ipfsH '$ipfsH'")
	
	val immCon = build(ipfsH)
	register.store(ipfsH, immCon)
	
	exiting(here)
    }
    
    fun provide(ipfsH: IpfsHash) : IpfsImmutableContent {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input ipfsH '$ipfsH'")
	
	if (register.isStored(ipfsH)){
	    register.retrieve(ipfsH)
	}
	else {
	    buildAndStore(ipfsH)
	}
	
	val result = register.retrieve(ipfsH)
	if (isTrace(here)) println("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun print (ipfsH: IpfsHash) {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	val hash = provide (ipfsH)
	println ("Hash: $hash")
	exiting(here)
    }
}
