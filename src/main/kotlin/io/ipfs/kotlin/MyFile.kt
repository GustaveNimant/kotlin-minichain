package io.ipfs.kotlin

import java.io.File
import java.io.InputStream

fun byteArrayOfFilePath(fil_p: String): ByteArray {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result:ByteArray = file.readBytes()
    
    exiting(here)
    return result
}

fun fileExtensionOfFilePath (fil_p:String): String {
    val file = File(fil_p)
    val ext = file.extension
    return ext
}

fun inputStreamOfFilePath(fil_p: String): InputStream {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result:InputStream = file.inputStream()
    
    exiting(here)
    return result
}

fun isFilePathOfWord(wor: String): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input wor '$wor'")
    
    val pattern = Regex("""^((\.)?/(\.)?\w[a-zA-Z_0-9]*)(/([a-zA-Z_0-9]+))*\.\w+$""")
    if (isTrace(here)) println("$here: input wor '$wor'")
    val result = pattern.matches(wor)

    if (isTrace(here)) println ("$here: output result '$result'")	
    exiting(here)
    return result
}

fun lineListOfFileName (nof: String) : MutableList<String> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input nof '$nof'")

    val result = mutableListOf<String>()
 
    File(nof).useLines {
    	lines -> lines.forEach { result.add(it)}
	}

  if (isTrace(here)) println ("$here: output result '$result'")	
  exiting(here)
  return result
}

fun outputWriteOfFilePath(fil_p: String, content: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
	
    File(fil_p).bufferedWriter().use { out -> out.write(content)}
    
    exiting(here)
}

fun provideAnyFileNameOfWhat(what: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input what '$what'")
    val whatLc = what.toLowerCase()
    var result =
      when (whatLc) {
        "lexeme" -> "test.lex"
	"block" -> what+".yml"
	"yml" -> "test.yml"
        else -> what+".txt"
      }
    println("$here: enter file name for '$what'. Default '$result'")
    val any_f = standardInputReadLine()
    if (! (any_f.isNullOrBlank() || any_f.equals("null"))) {
        result = any_f
    }
    
    println("$here: output result '$result'")

    exiting(here)
    return result
}

fun standardInputReadLine(): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
	
    val str = readLine().toString()
    
    exiting(here)
    return str
}

fun stringListOfFilePath(fil_p: String): List<String> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val bufferedReader = file.bufferedReader()
    val result:List<String> = bufferedReader.readLines()
    
    exiting(here)
    return result
}

fun stringReadOfFilePath(fil_p: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result: String = file.readText() 
    
    exiting(here)
    return result
}

