package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack

import org.http4k.client.ApacheClient
import org.http4k.core.Method

import org.http4k.client.OkHttp
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.CachingFilters
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer


/**
 * Author : Emile Achadde 05 mars 2020 at 11:47:43+01:00
 */

fun commandAndParametersOfStringList(str_l: List<String>): Pair<String, List<String>> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

  if(false) println("$here: input str_l $str_l")

  val str = str_l.first()
  if(false) println("$here: for str $str")

  val result = 
      if (str.startsWith('-')) {
	  val command = str.substring(1).toLowerCase()
	  if(false) println("$here: command set as '$command'")
	  val arg_l = str_l.drop(1)
	  Pair (command, arg_l)
      }
      else {
	  fatalErrorPrint("command starts with '-'",str, "Check", here) 
      }

  if(false) println("$here: output result $result")

  exiting(here)
  return result
}

fun executeIpfsOfWordList(wor_l: List<String>) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    // Ex.: (-ipfs) cat QmdKAX85S5uVKWx4ds5NdznJPjgsqAATnnkA8nE2bXQSSa
    var done = false
    if(isTrace(here)) println ("$here: input wor_l '$wor_l'")
    var wor_s = wordStackOfWordList(wor_l)
    
    while (!done) {
	try {
	    val wor = wor_s.pop()
	    val wor_3 = wor.substring(0,3)
	    if(isLoop(here)) println("$here: wor '$wor'")
	    
	    when (wor_3) {
		"add" -> { // "-ipfs add ./parser.bnf" "-ipfs add truc much"
	                   val word = stringOfGlueOfWordStack(" ", wor_s)
			   wor_s.clear()
			   if(isTrace(here)) println ("$here: input word '$word'")
			   
			   val filCon = // file path case
			   if (isFilePathOfWord(word)) {
			       stringReadOfFilePath(word)
			   }
			   else {
			       word
			   }
			   
			   //   val filJav = File(directoryPath)
			   //   val namedHashList = LocalIpfs().add.directory(filJav)
			   
			   //   val filJav = File(filePath)
			   //    val namedHash = LocalIpfs().add.file(filJav).Hash
			   val strH = LocalIpfs().add.string(filCon).Hash
 			   println ("MultiHash: $strH")
			   wor_s.clear()
		}
		"cat" -> { // (-ipfs cat) QmdKAX85S5uVKWx4ds5NdznJPjgsqAATnnkA8nE2bXQSSa
			   val strHas = wor_s.pop()
			   println ("strHas: '$strHas'")
		           wor_s.clear()
        		   val str = LocalIpfs().get.cat(strHas)
			   println ("Content:")
			   println (str)
    		}
		"com" -> { // commit
		           wor_s.clear()
                           val result = LocalIpfs().info.version()!!.Commit
			   println ("Ipfs Commit: '$result'")
	    	}
		"inf" -> { // (-ipfs inf) QmdKAX85S5uVKWx4ds5NdznJPjgsqAATnnkA8nE2bXQSSa
		           wor_s.clear()
        		   val str = LocalIpfs().info.version()
			   println ("Version:")
			   println (str)
    		}
		"pee" -> {
			try {
			    val peeId = LocalIpfs().peerid.peerId()
			    val result = peeId!!.Value
			    println ("Ipfs PeerID: '$result'")
			}
		    catch (e: java.net.UnknownHostException) {
			fatalErrorPrint ("Connection to 127.0.0.1:5001", "Connection refused", "launch Host :\n\tgo to minichain jsm; . config.sh; ipmsd.sh", here)
		    }
		}
		else -> {
		    fatalErrorPrint ("command were 'add' 'cat' 'com'mmit 'con'fig 'get' 'hel'p 'pee'rid","'"+wor+"'", "Check input", here)
		} // else
	    } // when
	} // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	
    } // while
    exiting(here)
}

fun main(args: Array<String>) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val parMap = parameterMapOfArguments(args)
    ParameterMap = parMap.toMap() // Globalization for Trace ...
    
    if (parMap.size > 0) {
	println ("Commands with their parameter list:")
	for ( (k, v) in parMap) {
	    println ("$k => $v")
	}
    }

    val com_s = parMap.keys
    var step = 0
    
    for (com in com_s) { 
      step = step + 1
      println("$here: ----- command # $step '$com' -----")
      val com_3 = com.substring(0,3)
      
      val wor_ml = parMap.get(com)
      val wor_l = wor_ml!!.map({w -> w.toString()}) 
      if (isLoop(here)) println("$here: wor_l '$wor_l'")
      
      when (com_3) {
	  "deb", "ent", "loo", "tra", "ver", "whe" -> {
	      val str = stringOfStringList(wor_ml)
	      println("$here: '$com' activated for '$str' functions")
	  }
	  "end", "exi" -> {println("\nnormal termination") }
	  "exa" -> {
	      // we can bind HttpHandlers (which are just functions from  Request -> Response) to paths/methods to create a Route,
	      // then combine many Routes together to make another HttpHandler
	      
	      val app: HttpHandler = routes(
		  "/ping" bind GET to { _: Request -> Response(OK).body("pong!") },
		  "/greet/{name}" bind GET to { req: Request ->
						    val name: String? = req.path("name")
						Response(OK).body("hello ${name ?: "anon!"}")
		  }
	      )

	      // call the handler in-memory without spinning up a server
	      val inMemoryResponse: Response = app(Request(GET, "/greet/Bob"))
	      println("$here: inMemoryResponse")
	      println(inMemoryResponse)

// Produces:
//    HTTP/1.1 200 OK
//
//
//    hello Bob

    // this is a Filter - it performs pre/post processing on a request or response
    val timingFilter = Filter {
        next: HttpHandler ->
        {
            request: Request ->
            val start = System.currentTimeMillis()
            val response = next(request)
            val latency = System.currentTimeMillis() - start
            println("Request to ${request.uri} took ${latency}ms")
            response
        }
    }

    // we can "stack" filters to create reusable units, and then apply them to an HttpHandler
    val compositeFilter = CachingFilters.Response.NoCache().then(timingFilter)
    val filteredApp: HttpHandler = compositeFilter.then(app)

    // only 1 LOC to mount an app and start it in a container
    filteredApp.asServer(Jetty(9000)).start()

    // HTTP clients are also HttpHandlers!
    val client: HttpHandler = OkHttp()

    val networkResponse: Response = client(Request(GET, "http://localhost:9000/greet/Bob"))
    println("$here: networkResponse")
    println(networkResponse)

// Produces:
//    Request to /api/greet/Bob took 1ms
//    HTTP/1.1 200
//    cache-control: private, must-revalidate
//    content-length: 9
//    date: Thu, 08 Jun 2017 13:01:13 GMT
//    expires: 0
//    server: Jetty(9.3.16.v20170120)
//
//    hello Bob

	      }
	  "hel" -> {helpOfParameterMap(parMap)}
          "inp" -> {// -input <file-path>
		    val inpFilPat = (ParameterMap.getValue("input")).first()
		    println("$here: input file path '$inpFilPat'")
	  }
	  "ipf" -> { 
	      try {
		  executeIpfsOfWordList(wor_l)
	      }
	      catch (e: java.net.ConnectException){
		  fatalErrorPrint ("Connection to 127.0.0.1:5001", "Connection refused", "launch Ipfs :\n\tgo to minichain jsm; . config.sh; ipmsd.sh", here)}
	  } // ipfs
	  "kwe" -> {val inpFilPat = try { (ParameterMap.getValue("input")).first()}
		    catch(e:java.util.NoSuchElementException){
			fatalErrorPrint("an input file path were given","there are none","add '-input <file-name>' to command line",here)
		    }
		    val keyValMap = kwextract(inpFilPat)
		    println ("Extracted (key, value) from input")
		    for ( (k, v) in keyValMap) {
			println ("$k => $v")
		    }
	  }
	  "htt" -> {

	      // https://www.http4k.org/quickstart/
	      val app = { request: Request -> Response(OK).body("Hello, ${request.query("name")}!") }

	      println ("$here: app $app")
    
	      val jettyServer = app.asServer(Jetty(9000)).start()
    
	      val request = Request(Method.GET, "http://localhost:9000").query("name", "John Doe")
	      println ("$here: request $request")
    
	      val client = ApacheClient()
	      println ("$here: client $client")
    
	      println(client(request))

	      jettyServer.stop()
	      
	      }
	  else -> {
	      fatalErrorPrint ("command were one of end, exi[t], hel[p], inp[put], ipf[s], kwe[xtract], run", "'"+com+"'", "re Run", here)
	    } // else
      } // when
    } // for
}

fun parameterMapOfArguments(args: Array<String>): Map<String, List<String>> {
  val (here, caller) = moduleHereAndCaller()
  entering(here, caller)

  if(false) println("$here: input args $args")

  var parMap = mutableMapOf<String, List<String>>()

  val arg_l = args.toList()
  val str_ll = stringListListOfDelimiterOfStringList ("-", arg_l)
  if(false) println("$here: str_ll $str_ll")
  
  for (str_l in str_ll) {
      if(false) println("$here: for str_l $str_l")
      var (command, par_l) = commandAndParametersOfStringList(str_l)
      if(parMap.contains(command)) {
	  val str_ = command.substring(3)
	  if(false) println("$here: Warning: command '$command' is repeated")
	  if(false) println("$here: Warning: to avoid this, modify the end command name '$command' from its 4th character (i.e. modify '$str_')")
	  command = command + "_"
	  if(false) println("$here: Warning: command has been currently modified to '$command'") 
      }
      parMap.put (command, par_l)
      if(false) println("$here: command '$command' added with par_l $par_l") 
  } // for arg_l

  val result = parMap.toMap()
  if(false) println("$here: output result $result")

  exiting(here)
  return result
}


