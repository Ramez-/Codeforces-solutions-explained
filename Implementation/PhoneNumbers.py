# Find the lexicographically smallest string of length k, such that its set of letters is a
# subset of the set of letters of s and s is lexicographically smaller than t.

n, k = map(int, raw_input().split())

inputArray = raw_input()

sortedInput = sorted(list(set(inputArray)))
sol = []

if k > n:
    sol = inputArray + sortedInput[0] *(k - n)
else:
    pos =  k - 1
    # -1 means last element as negtive indecies are counted from right
    while inputArray[pos] == sortedInput[-1]:
        pos -= 1
    indexOfNextElement = sortedInput.index(inputArray[pos]) + 1
    # inputArray[:pos] => the intial part of the array which is the same
    # sortedInput[indexOfNextElement] => elment changed
    # The smallest charchter k - pos-1 times
    sol = inputArray[:pos] + sortedInput[indexOfNextElement] + sortedInput[0] * (k - pos -1)

str = ''.join(sol)
print str