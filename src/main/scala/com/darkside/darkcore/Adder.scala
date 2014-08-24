package com.darkside.darkcore
import Chisel._

class HalfAdder extends Module {
	val io = new Bundle {
		val A = UInt(INPUT, 1)
		val B = UInt(INPUT, 1)
		val Sum = UInt(OUTPUT, 1)
		val Cout = UInt(OUTPUT, 1)
	}

	io.Sum := A ^ B
	io.Cout := A & B
}


class FullAdder extends Module {
	val io = new Bundle {
		val A = UInt(INPUT, 1)
		val B = UInt(INPUT, 1)
		val Cin = UInt(INPUT, 1)
		val Sum = UInt(OUTPUT, 1)
		val Cout = UInt(OUTPUT, 1)
	}

	val adder1 = Module(HalfAdder())
	adder1.io.A := io.A
	adder1.io.B := io.B
	val adder2 = Module(HalfAdder())
	adder2.io.A := adder1.io.Sum
	adder2.io.B := io.Cin
	io.Sum := adder2.io.Sum
	io.Cout := adder1.io.Cout | adder2.io.Cout
}


class Adder(size: Int) extends Module {
	val io = new Bundle {
		val A = UInt(INPUT, size)
		val B = UInt(INPUT, size)
		val Cin = UInt(INPUT, 1)
		val Sum = UInt(OUTPUT, size)
		val Cout = UINT(OUTPUT, 1)
	}
}

class HalfAdderTest(c: HalfAdder) extends Tester(c) {
	for(i <- 0 until 4) {
		// Test all possibilities (0 - 3)
		val A = i & 1
		val B = i & 2

		poke(c.io.A, A)
		poke(c.io.B, B)
		step(1)

		val sum = UInt(A + B, width=2)
		expect(c.io.Sum, sum(0).litValue())
		expect(c.io.Cout, sum(1).litValue())
	}
}

class FullAdderTest(c: FullAdder) extends Tester(c) {
	for(i <- 0 until 8) {
		// Test all possibilities (0 - 7)
		val A = i & 1
		val B = i & 2
		val cin = i & 4

		poke(c.io.A, A)
		poke(c.io.B, B)
		poke(c.io.Cin, cin)
		step(1)

		val sum = UInt(A + B + cin, width=2)
		expect(c.io.Sum, sum(0))
		expect(c.io.Cout, sum(1))
	}
}
