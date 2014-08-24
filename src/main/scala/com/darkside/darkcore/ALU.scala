package com.darkside.darkcore
import Chisel._

class ALU(size: Int) extends Module {
}

class ALUTest(c: ALU) extends Tester(c) {
	// Test all components
	val halfAdder = new HalfAdder()
	val fullAdder = new FullAdder()
	HalfAdderTest(halfAdder)
	FullAdderTest(fullAdder)
}
