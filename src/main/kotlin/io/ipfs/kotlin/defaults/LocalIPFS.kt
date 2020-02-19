package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.*
import io.ipfs.kotlin.IPFS
import io.ipfs.kotlin.IPFSConfiguration

val localIPFSConfig by lazy {
    val here = "localIPFSConfig"
    val caller = callerName()
    entering (here, caller)
    
    val url = provideUrl() 
    val str = url.toString()
    println("$here : url $str")

    val result = IPFSConfiguration("http://$str/api/v0/", createOKHTTP(), createMoshi())

    if(isVerbose(here)) println("$here : output result $result")
    exiting (here)
    result
}

open class LocalIPFS : IPFS(localIPFSConfig)
