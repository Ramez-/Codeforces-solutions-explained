package main

import (
	"bufio"
	. "fmt"
	"io"
	"os"
)

func Min(x, y int) int {
	if x > y {
		return y
	}
	return x
}

func run(_r io.Reader, _w io.Writer) {
	in := bufio.NewReader(_r)
	out := bufio.NewWriter(_w)
	defer out.Flush()

	var t int
	var n int
	Fscan(in, &t)
	for ; t > 0; t-- {
		Fscan(in, &n)
		arr := make([]int, n)
		for i := range arr {
			Fscan(in, &arr[i])
		}
		i := 0
		j := n - 1
		prev := 0
		turn := true
		a := 0
		b := 0
		turns := 0
		for i <= j {
			sum := 0
			turns++
			if turn {
				for prev >= sum && i <= j {
					sum += arr[i]
					i++
				}
				a += sum
			} else {
				for prev >= sum && i <= j {
					sum += arr[j]
					j--
				}
				b += sum
			}
			turn = !turn
			prev = sum
		}
		Fprintln(out, turns, a, b)
	}

}

func main() {
	run(os.Stdin, os.Stdout)
}
