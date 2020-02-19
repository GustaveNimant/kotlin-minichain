package io.ipfs.kotlin
import kotlin.system.exitProcess
import java.io.File
import java.io.InputStream
import java.lang.Character.MIN_VALUE as nullChar
import java.util.Base64
import java.util.Stack

class MyLibrary

// beginning of library

class TreeNode<T>(value:T){
    var value:T = value

    var parent:TreeNode<T>? = null

    var children:MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(node:TreeNode<T>){
        children.add(node)
        node.parent = this
    }

    override fun toString(): String {
        var s = "${value}"
        if (!children.isEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}

data class pairString (val first: String, val second: String)

var ParameterMap = mutableMapOf<String, MutableList<String>>() 

var level = 0
var dots = "........|........|........|........|........|........|........|"

fun <T> teeStackOfTeeList (tee_l: List<T>): Stack<T> {
    var stack = Stack<T>()
    tee_l.reversed().forEach { t -> stack.push (t)}
    return stack

}

fun <T> teeStackFromTeeOfTeeStack (tee:T, tee_s: Stack<T>): Stack<T> {
// return the subStack where all elements untill tee (excluded) are poped-off

   var cha = tee_s.pop()
   var done = false
   
   if (cha == tee) {return tee_s}

   while (! done) {
      try {
      	cha = tee_s.pop()
	done = tee == cha
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }
	
    return tee_s
}

fun byteArrayOfFilePath(fil_p: String): ByteArray {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result:ByteArray = file.readBytes()
    
    exiting(here)
    return result
}

fun callerName(): String {
    val sta = Thread.currentThread().stackTrace
    val result = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}

    return result	
}

fun characterStackOfString (str: String) : Stack<Char> {
    var stack = Stack<Char>()
    str.reversed().forEach { c -> stack.push(c)}
    return stack
}

fun countOfCharOfString (cha: Char, str: String) : Int {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    var count = 0

    for (c in str) {
        if (c == cha){count = count + 1}
    }	
		
    exiting(here + " with count " + count.toString())
    return count
}

fun entering(here: String, caller: String):Unit {
    level = level + 1
    if (level > 70) {
       println ("Error maximum number of nesting levels reached")
    } else {
        var points = dots.substring(0, level)
        println("$points Entering  in $here called by $caller")
    }
}

fun exiting(here: String):Unit {
    var points = dots.substring(0, level)
    println("$points Exiting from $here")
    level = level - 1	
}

fun fatalErrorPrint (expecting: String, found: String, cure: String, where: String): Nothing {
  val message: String = "\n$where: Expecting $expecting\n" + "$where: Found $found\n" + "$where: Cure: $cure\n"

  throw Exception(message)
}

fun fileExtensionOfFileName (fil_nam:String): String {
    val file = File(fil_nam)
    val ext = file.extension
    return ext
}

fun functionName(): String {
    val sta = Thread.currentThread().stackTrace[2]
    val str = sta.getMethodName()
    return str	
}

fun helpListOfStringList(str_l: List<String>): List<String> {
    var hel_l = mutableListOf("list of commands:")
    
    for (str in str_l) {
	var helps = when (str) {
	    "run" -> listOf(
		"gradlew run --args=\"-debug all\"",
		"gradlew run --args=\"-verbose all\"",
		"gradlew run --args=\"-trace all\"")
	    "compile" -> listOf("gradlew -q build --info")
	    "url" -> listOf("gradlew run --args=\"-port 5122 -verbose all\"")
	    else -> listOf(
		"gradlew -q build --info",
		"gradlew run --args=\"-debug all\"",
		"gradlew run --args=\"-verbose all\"",
		"gradlew run --args=\"-trace all\"",
		"gradlew run --args=\"-port 5122 -verbose all\""
	    )
	    
	}
	hel_l.addAll(helps)
    }
    return hel_l
}

fun helpFromParameters() {
    if (ParameterMap.containsKey("help"))
    { 
      val str_l = ParameterMap.getValue("help")
      val hel_l = helpListOfStringList(str_l)
      printStringList (hel_l)
      exitProcess(0)
    }
}

fun hereAndCaller(): Pair<String, String> {
    val caller = callerName()
    val here = functionName()

    val result = Pair(here, caller) 
    return result
}

fun inputStreamOfFilePath(fil_p: String): InputStream {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result:InputStream = file.inputStream()
    
    exiting(here)
    return result
}

fun isAlphabeticalOfChar(cha: Char): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[a-zA-Z_]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun isAlphanumericalOfChar(cha: Char): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[a-zA-Z_0-9]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun isDebug(here:String): Boolean {
  if (ParameterMap.containsKey("debug")) { 
    val debug_l = ParameterMap.getValue("debug")
    val result = debug_l.contains("all") || debug_l.contains(here)
    return result 
  }
  else {return false}
}

fun isIntegerOfString(str: String): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[0-9][0-9]*")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    exiting(here + " with result '$result'")
    return result
}

fun isLoop(here:String): Boolean {
  if (ParameterMap.containsKey("loop")) { 
    val loop_l = ParameterMap.getValue("loop")
    val result = loop_l.contains("all") || loop_l.contains(here)
    return result 
  }
  else {return false}
}

fun isNumericalOfChar(cha: Char): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[0-9]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun isTrace(here:String): Boolean {
  if (ParameterMap.containsKey("trace")) { 
    val trace_l = ParameterMap.getValue("trace")
    val result = trace_l.contains("all") || trace_l.contains(here)
    return result
  }
  else {return false}
}

fun isVerbose(here:String): Boolean {
  if (ParameterMap.containsKey("verbose")) { 
    val verbose_l = ParameterMap.getValue("verbose")
    val result = verbose_l.contains("all") || verbose_l.contains(here)
    return result 
  }
  else {return false}
}

fun isWhen(here:String): Boolean {
  if (ParameterMap.containsKey("when")) {
    val when_l = ParameterMap.getValue("when")
    val result = when_l.contains("all") || when_l.contains(here)
    return result
  }
  else {return false}
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

fun lineStackOfLineList (str_l: List<String>) : Stack<String> {
    var stack = Stack<String>()
    str_l.reversed().forEach { l -> stack.push(l) }
    return stack
}

fun nextStringAndStackOfEndCharOfCharacterStack(del: Char, cha_s: Stack<Char>): Pair<String, Stack<Char>> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input cha_s '$cha_s'")
    var done = false
    var str = ""
    var cha = cha_s.pop()
    
    while (! done){
      if (isDebug(here)) println("$here: cha '$cha'")
      str = str.plus(cha.toString())
      try {
      	  cha = cha_s.pop()
          done = cha.equals(del)
	  if (done) {cha_s.push(cha)}
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }

    assert (str.isNotEmpty())
    
    if (isTrace(here)) println("$here: output str '$str'")
    if (isTrace(here)) println("$here: output cha_s '$cha_s'")
    exiting(here)
    return Pair (str, cha_s)
}

fun nextWordAndEndCharOfEndCharListOfString(cha_l: List<Char>, str: String): Pair<String, Char> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha_l '$cha_l'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""
    var end_cha = 'x'
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (cha_l.contains(c)) {
	  end_cha = c
	  break
	  }
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return Pair(word, end_cha)
}

fun nextWordAndStackOfEndCharOfCharacterStack(del: Char, cha_s: Stack<Char>): Pair<String, Stack<Char>> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input cha_s '$cha_s'")
    var done = false
    var str = ""
    var cha = cha_s.pop()
    
    while (! done){
      if (isDebug(here)) println("$here: cha '$cha'")
      str = str.plus(cha.toString())
      try {
      	  cha = cha_s.pop()
          done = cha.equals(del)

	  if (cha.equals(' ')) {
	    fatalErrorPrint ("word ends with '$del'", "a blank", "Check", here)
	  }

	  if (done) {cha_s.push(cha)}
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }

    assert (str.isNotEmpty())
    
    if (isTrace(here)) println("$here: output str '$str'")
    if (isTrace(here)) println("$here: output cha_s '$cha_s'")
    exiting(here)
    return Pair (str, cha_s)
}

fun nextWordInBracketsOfString(str: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
// {someword}

    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (c.equals('{')) {continue}
	  if (c.equals('}')) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun nextWordOfEndCharListOfString(cha_l: List<Char>, str: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha_l '$cha_l'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (cha_l.contains(c)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun nextWordOfEndCharOfString(del: Char, str: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (c.equals(del)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun notYetImplemented(fun_nam: String){
    throw Exception("Error: function '$fun_nam' is not yet implemented")}

fun outputWrite(fileName: String, content: String) {
    val (here, caller) = hereAndCaller()
    entering(here, caller)
	
    File(fileName).bufferedWriter().use { out -> out.write(content)}
    
    exiting(here)
}

fun parameterMapOfArguments(args: Array<String>): MutableMap<String, MutableList<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  var stack = Stack<String>()
  args.reversed().forEach ({s -> stack.push(s)})

  val arg_siz = args.size
  val str = stringOfGlueOfStringList (" ", args.toList())

  if (arg_siz == 0) {
      exiting(here)
      return mutableMapOf ()
  }
  if (arg_siz < 2) {
      exiting(here)
      return mutableMapOf ()
  }

/*
 initialize
*/
  val arg_0 = stack.pop()
  var command = arg_0.substring(1).toLowerCase()
  val arg_1 = stack.pop()

  try {
    if (! arg_0.startsWith('-')) {
      fatalErrorPrint("First character in arguments were '-'", "Arguments are '$str'", "Reset arguments", "main") 
    }
  }
  catch (e: java.lang.ArrayIndexOutOfBoundsException) {
      fatalErrorPrint("There were arguments", "No Arguments", "Set arguments to program", "main") 
  }

/*
 loop on all arguments
*/

  var Done = false
  var arg_l = mutableListOf(arg_1)
  var ParameterMap = mutableMapOf (command to arg_l)

  while (!Done) {
     try {
       var arg = stack.pop()
       
       if (arg.startsWith('-')) {
         command = arg.substring(1).toLowerCase()
	 arg_l = mutableListOf()
       }
       else {
	 arg_l.add (arg)
	 ParameterMap.set(command, arg_l)
       }
     }
     catch (e: java.util.EmptyStackException) {Done=true}
   }

   exiting(here)
   return ParameterMap
}

fun printStringList (str_l: List<String>) {
    val content = stringOfGlueOfStringList ("\n", str_l)

    println (content)
}

fun provideAnyFileNameOfWhat(what: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input what '$what'")
    val whatLc = what.toLowerCase()
    var anyFileName =
      when (whatLc) {
        "lexeme" -> "test.lex"
	"block" -> what+".yml"
	"yml" -> "test.yml"
        else -> what+".txt"
      }
    println("$here: enter file name for '$what'. Default '$anyFileName'")
    val any_f = standardInputRead()
    if (! (any_f.isNullOrBlank() || any_f.equals("null"))) {
        anyFileName = any_f
    }
    println("$here: input File name is '$anyFileName'")

    exiting(here)
    return anyFileName
}

fun standardInputRead(): String {
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

fun stringOfGlueOfStringList (glue: String, str_l: List<String>) : String {
 val str = str_l.fold("", {acc, s -> acc + s + glue })
 return str 
}

fun stringOfStringList (str_l: List<String>) : String {
 val str = str_l.fold("", {acc, s -> acc + s })
 return str 
}

fun stringReadOfFilePath(fil_p: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    val file = File(fil_p)
    val result: String = file.readText() 
    
    exiting(here)
    return result
}

fun wordListOfString (str: String): List<String> {
    val trimedString = str.trim(' ')    
    val regex = Regex("""\s+""")

    val result = trimedString.split(regex)

    return result
}

fun wordStackOfLine (lin: String) : Stack<String> {
    var stack = Stack<String>()
    var wor_l = wordListOfString (lin)
    wor_l.reversed().forEach { w -> stack.push(w)}
    return stack
}

