package io.ipfs.kotlin

/**
 * What    : Any Hash used on IPFS.
 * Author  : François Colonna 22 février 2020 at 10:38:18+01:00

 */

sealed class IpfsHash ()

    data class IpfsHashQm (val strH: String) : IpfsHash ()
    data class IpfsHashZ2 (val strH: String) : IpfsHash ()
    
    fun isQmHash (strH: String): Boolean {
	val result = strH.substring(0,2) == "Qm"
	return result
	}

    fun isZ2Hash (strH: String): Boolean {
	val result = strH.substring(0,2) == "z2"
	return result
    }

    fun stringOfIpfsHash (ipfsH: IpfsHash): String {
	val result = when (ipfsH) {
	    is IpfsHashQm -> ipfsH.strH
	    is IpfsHashZ2 -> ipfsH.strH
	    }
	return result
    }

    fun ipfsHashOfString (str: String): IpfsHash {
	val (here, caller) = hereAndCaller()
	entering(here, caller)

	if (isTrace(here)) println("$here: input str '$str'")
	
	val str_2 = str.substring(0, 2)
	val result = when (str_2) {
	    "Qm" -> IpfsHashQm(str)
	    "Z2" -> IpfsHashZ2(str)
	    else -> {
		fatalErrorPrint("hash starts with 'Qm' or 'Z2'", str, "Check", here)
	    }
	}

	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
