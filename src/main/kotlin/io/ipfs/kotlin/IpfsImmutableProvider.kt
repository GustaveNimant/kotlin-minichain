package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*
import io.ipfs.kotlin.IpfsImmutable.*
import io.ipfs.kotlin.IpfsImmutableRegister.*

/**
 * Provision : the content of an immutable file from its MultiHash
 * Done      : LocalIpfs().get.cat(mulTyp)
 * Needs     : MultiHash 
 * Needed by : MultiHash 
 * Command   : gradlew run --args="-ipfs get QmbEm7hDJ9zB22UPnXRGfaWrFoEbJZbHPTEa6udMZ48riz" 
 * Author : François Colonna 22 février 2020 at 11:02:59+01:00
 */

class IpfsImmutableProvider {

    val register = IpfsImmutableRegister()
    
    fun build (mulTyp: MultiHashType): IpfsImmutableContent {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input mulTyp '$mulTyp'")

	val strH = mulTyp.toString()
	val str = LocalIpfs().get.cat(strH)

	val result = IpfsImmutableContent(str)
	println("$here: output result $result")
	
	exiting(here)
	return result 
    }
    
    fun buildAndStore(mulTyp: MultiHashType){
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input mulTyp '$mulTyp'")
	
	val immCon = build(mulTyp)
	register.store(mulTyp, immCon)
	
	exiting(here)
    }
    
    fun provide(mulTyp: MultiHashType) : IpfsImmutableContent {
	val (here, caller) = hereAndCaller()
	entering(here, caller)
	
	println("$here: input mulTyp '$mulTyp'")
	
	if (register.isStored(mulTyp)){
	    register.retrieve(mulTyp)
	}
	else {
	    buildAndStore(mulTyp)
	}
	
	val result = register.retrieve(mulTyp)
	if (isTrace(here)) println("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
}
