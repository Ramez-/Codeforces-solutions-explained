package main

import (
	"bufio"
	. "fmt"
	"io"
	"os"
	"strings"
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
	var n, a, b int
	Fscan(in, &t)
	for ; t > 0; t-- {
		Fscan(in, &n, &a, &b)
		var s strings.Builder
		alph := 'a'
		countUnique := 0
		strlen := 0
		for i := 0; i < n; i++ {
			if countUnique < b-1 {
				s.WriteString(string(alph))
				countUnique++
				strlen++
				alph++
			} else {
				for ; i < n && strlen < a; i++ {
					s.WriteString(string(alph))
					strlen++
				}
				break
			}
		}
		temp := s.String()
		for i := 0; i < n/a; i++ {
			s.WriteString(temp)
		}
		sol := s.String()
		Fprintln(out, sol[:n])
	}
}

func main() {
	run(os.Stdin, os.Stdout)
}
