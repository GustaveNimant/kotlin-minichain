package io.ipfs.kotlin

import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

sealed class Lexeme ()
  data class KeywordWithPersonName (val name: String) : Lexeme ()
  data class KeywordWithDate (val name: String) : Lexeme ()
  data class KeywordWithQmHash (val name: String) : Lexeme ()
  data class KeywordWithZ2Hash (val name: String) : Lexeme () 
  data class KeywordWithString (val name: String) : Lexeme ()
  data class KeywordWithFile (val name: String) : Lexeme ()
  data class KeywordWithInteger (val name: String) : Lexeme ()
  data class KeywordFromUser (val name: String) : Lexeme ()
  
  data class AuthorName (val name: String) : Lexeme ()
  data class Comment (val name: String) : Lexeme ()
  data class DateValue (val value: String) : Lexeme ()
  data class FilePath (val name: String) : Lexeme ()
  data class NextName (val name: String) : Lexeme ()
  data class QmHash (val hash: String) : Lexeme ()
  data class Signature (val value: String) : Lexeme ()
  data class Spot (val value: String) : Lexeme ()
  data class Tic (val value: String) : Lexeme ()
  data class Z2Hash (val hash: String) : Lexeme ()

  data class FromUserKeywordValue (val name: String) : Lexeme ()

  data class TextRecordConstant (val record: String) : Lexeme ()
  data class TextStringConstant (val string: String) : Lexeme ()
  data class TextWordConstant (val word: String) : Lexeme ()
  data class TextVariableSubstituable (val variable: String) : Lexeme ()
	  
  object TokenUnknown : Lexeme ()
  object TokenSkipped : Lexeme ()

  object TokenEmptySharpedLine : Lexeme ()
  object TokenEmptyLine : Lexeme ()

  object TokenAt : Lexeme ()
  object TokenDollar : Lexeme ()
  object TokenSpace : Lexeme ()
  object TokenEndOfLine : Lexeme ()
  object TokenVee : Lexeme ()
  object TokenComma : Lexeme ()
  object TokenColon : Lexeme ()
  object TokenSemicolon : Lexeme ()
  object TokenSharp : Lexeme ()
  object TokenDot : Lexeme ()
  object TokenHyphen : Lexeme ()
  object TokenLeftCurvedBracket : Lexeme ()
  object TokenLeftSquareBracket : Lexeme ()
  object TokenRightCurvedBracket : Lexeme ()
  object TokenRightSquareBracket : Lexeme ()

fun fullnameOfLexeme (lexeme: Lexeme): String {
    val string = when (lexeme) {

	is AuthorName -> "AuthorName("+lexeme.name+")"
	is Comment -> "Comment("+lexeme.name+")"
	is DateValue -> "DateValue("+lexeme.value+")"
	is FilePath -> "FilePath("+lexeme.name+")"
	is FromUserKeywordValue -> "FromUserKeywordValue("+lexeme.name+")"
	is NextName -> "NextName("+lexeme.name+")"
	is QmHash -> "QmHash("+lexeme.hash+")"
	is Signature -> "Signature("+lexeme.value+")"	
	is Spot -> "Spot("+lexeme.value+")"
	is TextRecordConstant -> "TextRecordConstant("+lexeme.record+")"
	is TextStringConstant -> "TextStringConstant("+lexeme.string+")"
	is TextVariableSubstituable -> "TextVariableSubstituable("+lexeme.variable+")"	
	is TextWordConstant -> "TextWordConstant("+lexeme.word+")"
	is Tic -> "Tic("+lexeme.value+")"	
	is Z2Hash -> "Z2Hash("+lexeme.hash+")"

	is KeywordWithDate    -> "KeywordWithDate("+lexeme.name+")"
    	is KeywordWithFile    -> "KeywordWithFile("+lexeme.name+")"
    	is KeywordWithInteger -> "KeywordWithInteger("+lexeme.name+")"
    	is KeywordWithQmHash  -> "KeywordWithQmHash("+lexeme.name+")"
    	is KeywordWithString  -> "KeywordWithString("+lexeme.name+")"
        is KeywordWithPersonName -> "KeywordWithPersonName("+lexeme.name+")"
	is KeywordWithZ2Hash -> "KeywordWithZ2Hash("+lexeme.name+")"

	is KeywordFromUser -> "KeywordFromUser("+lexeme.name+")"

	TokenAt	-> "TokenAt"
	TokenLeftCurvedBracket -> "TokenLeftCurvedBracket"
	TokenLeftSquareBracket -> "TokenLeftSquareBracket"
	TokenRightCurvedBracket -> "TokenRightCurvedBracket"
	TokenRightSquareBracket -> "TokenRightSquareBracket"
	TokenColon	-> "TokenColon"
	TokenComma	-> "TokenComma"
	TokenDollar	-> "TokenDollar"
	TokenDot	-> "TokenDot"
	TokenEmptyLine  -> "TokenEmptyLine"
	TokenEmptySharpedLine -> "TokenEmptySharpedLine"
	TokenEndOfLine	-> "TokenEndOfLine"
	TokenHyphen	-> "TokenHyphen"
	TokenSemicolon	-> "TokenSemicolon"
	TokenSharp	-> "TokenSharp"
	TokenSkipped    -> "skipped "
	TokenSpace	-> "TokenSpace"
	TokenUnknown    -> "unknown "
	TokenVee	-> "TokenVee"
	}
    return string
}

fun isInMetaOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
	is TextRecordConstant  -> false
	is TextStringConstant  -> false
	is TextVariableSubstituable  -> false
	is TextWordConstant  -> false

	is FromUserKeywordValue  -> false

	TokenAt	         -> true
	TokenColon	 -> true
	TokenComma	 -> true
	TokenDollar	 -> true
	TokenDot	 -> true
	TokenEndOfLine	 -> true
	TokenHyphen	 -> true
	TokenSemicolon	 -> true
	TokenSharp	 -> true
	TokenLeftCurvedBracket -> true
        TokenLeftSquareBracket -> true
	TokenRightCurvedBracket -> true
        TokenRightSquareBracket -> true
	TokenSpace	 -> true

	is AuthorName  -> true
	is Comment  -> true
	is DateValue  -> true
	is FilePath  -> true
	is NextName  -> true
	is QmHash  -> true
	is Signature  -> true
	is Spot  -> true
	is Tic  -> true
	is Z2Hash  -> true

	is KeywordWithDate     -> true
    	is KeywordWithFile     -> true
    	is KeywordWithInteger  -> true
    	is KeywordWithQmHash   -> true
    	is KeywordWithString   -> true
        is KeywordWithPersonName -> true
	is KeywordWithZ2Hash  -> true

	is KeywordFromUser -> false

	TokenSkipped     -> true
	TokenUnknown     -> true
	TokenVee	 -> true
	TokenEmptyLine   -> true
	TokenEmptySharpedLine  -> true
    }
    exiting(here + " with result '$result'")
    return result
}

fun isMetaKeywordOfString (str: String): Boolean {
  val (here, caller) = hereAndCaller()
  entering(here, caller)

  if (isTrace(here)) println("$here: str '$str'")

    val result = when (str) {
       "Author" -> true
       "Date" -> true
       "Source" -> true
       "Signature" -> true
       "members" -> true 
       "mutable" -> true 
       "parents" -> true 
       "previous" -> true
       "next" -> true 
       "tic" -> true 
       "qm" -> true 
       "spot" -> true
       else -> {
       	    false
	}
  }

  if (isTrace(here)) println("$here: output result $result")

  exiting(here)
  return result
}

fun isMetaKeywordValueOfDollarStringDollar (dol_dol: String): Boolean {
// $keywordFromUser: anything$
// $keyword: value$  
  val (here, caller) = hereAndCaller()
  entering(here, caller)
  
   if (! dol_dol.contains(':')){
    fatalErrorPrint ("A string enclosed with '$' contains a ':'", "'"+dol_dol+"'", "check", here)
  }

  val (keyword, str) = dol_dol.split(':')
  if (isDebug(here)) println("$here: keyword '$keyword'")
  if (isDebug(here)) println("$here: str '$str'")

  val lex = lexemeOfWord (keyword)
  val result = isKeywordWithOfLexeme (lex)
  
  if (isTrace(here)) println("$here: output result $result")

  exiting(here)
  return result
}

fun isInTextOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
	is TextRecordConstant  -> true
	is TextStringConstant  -> true
	is TextVariableSubstituable  -> true
	is TextWordConstant  -> true

	TokenAt		 -> true
	TokenColon	 -> true
	TokenLeftSquareBracket -> true
	TokenLeftCurvedBracket -> true
	TokenRightSquareBracket -> true
	TokenRightCurvedBracket -> true
	TokenComma	 -> true
	TokenDollar	 -> true
	TokenDot	 -> true
	TokenEndOfLine	 -> true
	TokenHyphen	 -> true
	TokenSemicolon	 -> true
	TokenSharp	 -> true
	TokenSpace	 -> true

	is AuthorName  -> false
	is Comment  -> false
	is DateValue  -> false
	is FilePath  -> false
	is KeywordWithZ2Hash  -> false
	is NextName  -> false
	is QmHash  -> false
	is Signature  -> false
	is Spot  -> false
	is Tic  -> false
	is Z2Hash  -> false

	is FromUserKeywordValue  -> true
	
	is KeywordWithDate     -> false
    	is KeywordWithFile     -> false
    	is KeywordWithInteger  -> false
    	is KeywordWithQmHash   -> false
    	is KeywordWithString   -> false
        is KeywordWithPersonName  -> false

        is KeywordFromUser  -> true

	TokenSkipped     -> false
	TokenUnknown     -> false
	TokenVee	 -> false
	TokenEmptyLine   -> false
	TokenEmptySharpedLine  -> false
    }
    exiting(here + " with result '$result'")
    return result
}

fun lexemeOfWord (keyword: String) : Lexeme {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input keyword: '$keyword'")

   var lexeme = when (keyword) {
       "Author" -> KeywordWithPersonName (keyword)
       "Date" -> KeywordWithDate (keyword)
       "Source" -> KeywordWithFile (keyword)
       "Signature" -> KeywordWithString (keyword)      
       "members" -> KeywordWithString (keyword)
       "mutable" -> KeywordWithFile (keyword)
       "parents" -> KeywordWithQmHash (keyword)
       "previous" -> KeywordWithQmHash (keyword)
       "next" -> KeywordWithString (keyword)
       "tic" -> KeywordWithInteger (keyword)       
       "qm" -> KeywordWithZ2Hash (keyword)
       "spot" -> KeywordWithInteger (keyword)       
       else -> {
       	    KeywordFromUser (keyword)
	}
  }
  
  if (isTrace(here)) println ("$here: output lexeme '$lexeme'")	
  exiting(here)
  return lexeme
 }

fun isKeywordWithOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
    	is KeywordWithZ2Hash -> true 
	is KeywordWithDate -> true     
    	is KeywordWithFile -> true 
    	is KeywordWithInteger -> true 
    	is KeywordWithQmHash -> true  
    	is KeywordWithString -> true   
        is KeywordWithPersonName -> true
	else -> false
    }
    exiting(here + " with result '$result'")
    return result
}

fun isTokenOfChar(cha: Char) : Boolean {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = when (cha) {
		'#' -> true
		'$' -> true
		' ' -> true 
		'\n' -> true
		'/' -> true
		',' -> true
		':' -> true
		']' -> true
		'[' -> true
		'}' -> true
		'{' -> true
		';' -> true
		'.' -> true
		'-' -> true
		'a','z' -> true
		else -> false
    }
    
  if (isTrace(here)) println ("$here: output result '$result'")	
  exiting(here)
  return result
 }

fun nextWordOfString(pos:Int, lin: String): String {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input pos '$pos'")
    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val str = lin.substring(pos)
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (isTokenOfChar(c)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun stringValueOfLexeme (lexeme: Lexeme): String {
    val string = when (lexeme) {

	is AuthorName -> lexeme.name
	is Comment -> lexeme.name
	is DateValue -> lexeme.value
	is FilePath -> lexeme.name
	is FromUserKeywordValue -> lexeme.name
	is NextName -> lexeme.name
	is QmHash -> lexeme.hash
	is Signature -> lexeme.value	
	is Spot -> lexeme.value
	is TextRecordConstant -> lexeme.record
	is TextStringConstant -> lexeme.string
	is TextVariableSubstituable -> lexeme.variable	
	is TextWordConstant -> lexeme.word
	is Tic -> lexeme.value	
	is Z2Hash -> lexeme.hash

	is KeywordWithDate    -> lexeme.name
    	is KeywordWithFile    -> lexeme.name
    	is KeywordWithInteger -> lexeme.name
    	is KeywordWithQmHash  -> lexeme.name
    	is KeywordWithString  -> lexeme.name
        is KeywordWithPersonName -> lexeme.name
        is KeywordFromUser -> lexeme.name	
	is KeywordWithZ2Hash -> lexeme.name

	TokenAt         -> "@"
	TokenColon	-> ":"	
	TokenComma	-> ","
	TokenDollar	-> "\$"
	TokenDot	-> "."
	TokenEmptyLine  -> ""
	TokenEmptySharpedLine -> ""
	TokenEndOfLine	-> "\n"
	TokenHyphen	-> "-"
	TokenSemicolon	-> ";"
	TokenSharp	-> "#"
	TokenSpace	-> " "
	TokenVee	-> "v"
	TokenLeftSquareBracket	-> "["
	TokenLeftCurvedBracket	-> "{"
	TokenRightSquareBracket	-> "]"
	TokenRightCurvedBracket	-> "}"
	TokenUnknown    -> "unknown"
	TokenSkipped    -> "skipped"
	}
    return string
}

fun tokenOfChar(cha: Char, pos: Int, lin: String) : Lexeme {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha '$cha'")

    val token = when (cha) {
    		'@' -> TokenAt
		'#' -> TokenSharp
		'$' -> TokenDollar
		' ' -> TokenSpace
		'\n' -> TokenEndOfLine
		'v' -> TokenVee
		',' -> TokenComma
		':' -> TokenColon
		';' -> TokenSemicolon
		'.' -> TokenDot	
		'-' -> TokenHyphen
	else -> {
             val message = "$here: Error unknown Character '$cha' at position $pos of line '$lin'"
    	     throw Exception(message)
	     	}
	}

	if (isTrace(here)) println("$here: output token '$token'")
	exiting(here)
	return token
}

