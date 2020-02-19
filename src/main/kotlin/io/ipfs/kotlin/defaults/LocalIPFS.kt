package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.*
import io.ipfs.kotlin.IPFS
import io.ipfs.kotlin.IPFSConfiguration

val localIPFSConfig by lazy {
    val here = "localIPFSConfig"
    val caller = callerName()
    entering (here, caller)
    
    val host = hostNameFromParameterMap()
    val port = portNameFromParameterMap()
    val url = host + ":" + port

    println("$here : url $url")

    val result = IPFSConfiguration("http://$url/api/v0/", createOKHTTP(), createMoshi())

    if(isVerbose(here)) println("$here : output result $result")
    exiting (here)
    result
}

open class LocalIPFS : IPFS(localIPFSConfig)
