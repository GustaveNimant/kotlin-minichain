package io.ipfs.kotlin

import java.io.File
import java.util.Stack

data class Url (val host: String, val port:String) {

    fun isEmpty (): Boolean {
	return host.isNullOrEmpty() || port.isNullOrEmpty()
    }

    override fun toString (): String {
       return host + ":" + port
    }
}

object urlRegister {
     var value:Url = Url("", "")
	 
     fun isEmpty (): Boolean {
     	 return value.isEmpty()
     }
     
     fun store (url:Url) {
         value = url
     }
     
     fun retrieve(): Url {
         val (here, caller) = hereAndCaller()
    	 entering(here, caller)

	 var result = value

	 exiting(here)
	 return result
     }
}

fun hostNameFromParameterMap(): String {
    val result = 
	if (ParameterMap.containsKey("host")) {
	   (ParameterMap.getValue("host")).first()
    }
    else {
	"127.0.0.1"
    }
    return result 
}

fun portNameFromParameterMap(): String {
    val result = 
    if (ParameterMap.containsKey("port")) { 
          ParameterMap.getValue("port").first()
    }
    else {
	"5122"
    }
    return result 
}

fun buildAndStoreUrl() {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val host = hostNameFromParameterMap()
    val port = portNameFromParameterMap()

    if (! isIntegerOfString(port)) {
	fatalErrorPrint("an Integer", port, "Check", here)
    }
    var result: Url = Url(host, port)
    
    urlRegister.store (result)

    exiting(here)
    return
}

fun provideUrl() : Url {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (urlRegister.isEmpty()){
       buildAndStoreUrl()
    }
    
    val result = urlRegister.retrieve()

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun printUrl () {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val url = provideUrl ()
    println ("Url $url")
    exiting(here)
}

