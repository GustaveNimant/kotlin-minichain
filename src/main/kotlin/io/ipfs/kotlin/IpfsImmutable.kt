package io.ipfs.kotlin

/**
 * What    : The different kind of Immutable stored on IPFS
 * Author  : François Colonna 22 février 2020 at 10:38:18+01:00
 */

sealed class IpfsImmutable {
    object IpfsImmutableBlock
    object IpfsImmutableCode
    object IpfsImmutableText
    object IpfsImmutableSmartContract
}
