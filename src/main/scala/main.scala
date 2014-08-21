package main
import Chisel._

class Counter(size: Int) extends Module {
	val io = new Bundle {
		val out = UInt(OUTPUT, size)
	}
	val r1 = Reg(init = UInt(0, size))
	r1 := r1 + UInt(1)
	io.out := r1
}

object main {
	def main(args: Array[String]): Unit = {
		// chiselMain(args, () => Module(new Counter(10)));
		chiselMain(args, () => Module(new Counter(4)))
	}
}
