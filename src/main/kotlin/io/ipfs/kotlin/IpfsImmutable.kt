package io.ipfs.kotlin

/**
 * What       : The different Types of Immutable
 * Definition : An Immutable is a file adressed by its content (its hash or CID).
 * Definition : IpfsImmutableBlock
 * Definition : IpfsImmutableCode
 * Definition : IpfsImmutableFriends
 * Definition : IpfsImmutableIdentity
 * Definition : IpfsImmutableLabel
 * Definition : IpfsImmutableSmartContract
 * Definition : IpfsImmutableSymbol
 * Definition : IpfsImmutableTag
 * Definition : IpfsImmutableText
 * Author : Emile Achadde 23 février 2020 at 09:33:04+01:00
 */

sealed class IpfsImmutable {
    object IpfsImmutableBlock
    object IpfsImmutableCode
    object IpfsImmutableFriends
    object IpfsImmutableIdentity
    object IpfsImmutableLabel
    object IpfsImmutableSmartContract
    object IpfsImmutableSymbol
    object IpfsImmutableTag
    object IpfsImmutableText
}
