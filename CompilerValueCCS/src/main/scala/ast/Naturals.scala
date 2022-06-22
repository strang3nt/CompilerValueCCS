package main.scala.ast

object Naturals:
  
  opaque type Natural = Int

  object Natural:
    def apply(i: Int): Natural = 
      require(i >= 0); i
  
  extension (x: Natural)
    // arithmetic operators
    def +(y: Natural): Natural = x + y
    def -(y: Natural): Natural = 
      assert((x - y) > 0); x - y
    def *(y: Natural): Natural = x * y
    def /(y: Natural): Natural = x / y

    // boolean operators
    def <(y: Natural): Boolean = x < y
    def <=(y: Natural): Boolean = x <= y
    def >=(y: Natural): Boolean = x >= y
    def >(y: Natural): Boolean = x > y
    def ==(y: Natural): Boolean = x == y
