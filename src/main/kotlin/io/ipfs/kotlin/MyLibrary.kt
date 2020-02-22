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

fun commandSetOfParameterMap (parM: Map<String, List<String>>): Set<String> {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if(isTrace(here)) println ("$here: input parM $parM")
    val result = parM.keys

    if(isTrace(here)) println ("$here: output result $result")
    exiting(here)
    return result 
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

fun functionName(): String {
    val sta = Thread.currentThread().stackTrace[2]
    val str = sta.getMethodName()
    return str	
}

fun helpList(): List<String> {
    var hel_l = listOf(
	"gradlew [-q] build [--info]",
	"gradlew run --args=\"-help ''|all|compile|host|port|run|url",
	"gradlew run --args=\"-debug <function name>|all\"",
	"gradlew run --args=\"-ipfs add [Options] <path> add a file or a directory to Ipfs https://docs.ipfs.io/reference/api/cli/#ipfs-add",
	"gradlew run --args=\"-ipfs add <path> add a file or a directory to Ipfs",
	"gradlew run --args=\"-trace <function name>|all\" print input and output data",
	"gradlew run --args=\"-verbose<function name>|all\"",
	"gradlew run --args=\"-loop<function name>|all\" print message inside a loop",
	"gradlew run --args=\"-when<function name>|all\" print message inside a when",
	"gradlew run --args=\"-port 5122\" defines port with host default (127.0.0.1)",
	"gradlew run --args=\"-host 127.0.0.1|<host name>\" defines host with port default (5001)",
	"gradlew run --args=\"-url 127.0.0.1|<host name>:5001<port>\" defines an url"
	)
    return hel_l
}

fun helpListOfStringList(str_l: List<String>): List<String> {
    var hel_l = helpList()
    var mut_l = mutableListOf<String>()

    for (str in str_l) {
	val helps =
	    when (str) {
		"all" -> hel_l
		"ipfs" -> hel_l.filter({h -> h.contains("-ipfs ")})
		"run" -> hel_l.filter({h -> h.contains(" run ")})
		"compile" -> listOf("gradlew -q build --info")
		"host" -> listOf("gradlew run --args=\"-host 127.0.0.1|<host name>\" defines host with port default (5001)")
		"port" -> listOf("gradlew run --args=\"-port 5122\" defines port with host default (127.0.0.1)")
		"url" -> listOf("gradlew run --args=\"-url 127.0.0.1|<host name>:5001<port>\" defines an url")
		else -> hel_l
		
	    }
	mut_l.addAll(helps)
    }
    return mut_l
}

fun helpFromParameters() {
    if (ParameterMap.containsKey("help"))
    { 
      val str_l = ParameterMap.getValue("help")
      val hel_l = helpListOfStringList(str_l)
      printOfStringList(hel_l)
      exitProcess(0)
    }
}

fun hereAndCaller(): Pair<String, String> {
    val sta = Thread.currentThread().stackTrace
    val here = (sta[2]).getMethodName()
    val caller = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}
    
    val result = Pair(here, caller) 
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

fun isLoop(here:String): Boolean {
  if (ParameterMap.containsKey("loop")) { 
    val loop_l = ParameterMap.getValue("loop")
    val result = loop_l.contains("all") || loop_l.contains(here)
    return result 
  }
  else {return false}
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

fun notYetImplemented(fun_nam: String){
    throw Exception("Error: function '$fun_nam' is not yet implemented")}

fun parameterMapOfArguments(args: Array<String>): MutableMap<String, MutableList<String>> {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

//  printOfStringArray(args)
  
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

