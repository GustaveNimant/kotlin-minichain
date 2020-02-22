package io.ipfs.kotlin

/**
 * What    : Any Hash used on IPFS.
 * Author  : François Colonna 22 février 2020 at 10:38:18+01:00

 */

class IpfsHash (val hash: String) {

    fun isQmHash (hash: String): Boolean {
	val result = hash.substring(0,2) == "Qm"
	return result
	}

    fun isZ2Hash (hash: String): Boolean {
	val result = hash.substring(0,2) == "z2"
	return result
    }

    override fun toString (): String {
	return hash
    }
}
