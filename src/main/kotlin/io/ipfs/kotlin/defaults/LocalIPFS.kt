package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.IPFS
import io.ipfs.kotlin.*
import io.ipfs.kotlin.IPFSConfiguration

val localIPFSConfig by lazy {
    val host = hostNameFromParameterMap()
    val port = portFromParameterMap()
    val url = host + ":" + port

    println("url $url")

    IPFSConfiguration("http://$url/api/v0/", createOKHTTP(), createMoshi())
}

open class LocalIPFS : IPFS(localIPFSConfig)
