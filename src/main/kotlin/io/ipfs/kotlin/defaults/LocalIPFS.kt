package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.*
import io.ipfs.kotlin.Ipfs
import io.ipfs.kotlin.IpfsConfiguration

val localIpfsConfig by lazy {
    val str = "127.0.0.1:5001"
    IpfsConfiguration("http://$str/api/v0/", createOKHTTP(), createMoshi())
}

open class LocalIpfs : Ipfs(localIpfsConfig)
