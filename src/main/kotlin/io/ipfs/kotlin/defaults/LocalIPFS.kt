package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.*
import io.ipfs.kotlin.Ipfs
import io.ipfs.kotlin.IpfsConfiguration

val localIpfsConfig by lazy {
    val here = "localIpfsConfig"
    val caller = callerName()
    entering (here, caller)
    
    val url = provideUrl() 
    val str = url.toString()
    println("$here : url $str")

    val result = IpfsConfiguration("http://$str/api/v0/", createOKHTTP(), createMoshi())

    if(isVerbose(here)) println("$here : output result $result")
    exiting (here)
    result
}

open class LocalIpfs : Ipfs(localIpfsConfig)
