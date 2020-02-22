package io.ipfs.kotlin

/**
 * 
 * 
 *
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
}
