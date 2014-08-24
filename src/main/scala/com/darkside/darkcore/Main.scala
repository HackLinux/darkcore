package com.darkside.darkcore
import Chisel._

object main {
	def main(args: Array[String]): Unit = {
		chiselMainTest(args, () => Module(new (32))) {
			c => new TopTest(c)
		}
	}
}
