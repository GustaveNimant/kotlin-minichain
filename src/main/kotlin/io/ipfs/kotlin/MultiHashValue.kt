package io.ipfs.kotlin

/**
 * What   : The Value of a MultiHash is a triplet 
 * fn code  dig size hash digest
 * -------- -------- ------------------------------------
 * 00010001 00000100 101101100 11111000 01011100 10110101
 * sha1     4 bytes  4 byte sha1 digest
 * Author : Emile Achadde 23 février 2020 at 11:36:24+01:00
 */

class MultiHashValue (val functionCode: Int, val lengthCode: Int, val digestHash: String) 

    fun isQmMultiHash (strH: String): Boolean {
	val result = strH.substring(0,2) == "Qm"
	return result
	}

    fun isZ2MultiHash (strH: String): Boolean {
	val result = strH.substring(0,2) == "z2"
	return result
    }

