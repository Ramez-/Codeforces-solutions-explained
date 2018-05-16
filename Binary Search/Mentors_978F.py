import bisect

n, k = map(int, raw_input().split())

inputArray = list(map(int, raw_input().split()))

# array to put if this number is bigger than its pair programmer
num = [0] * n

while k > 0:
    x, y = map(int, raw_input().split())
    x -= 1
    y -= 1
    if inputArray[x] > inputArray[y]:
        num[x] += 1
    if inputArray[y] > inputArray[x]:
        num[y] += 1
    k -= 1

ans = [0] * n

inputSorted = sorted(inputArray)

# gives the index and value in array
for index, value in enumerate(inputArray):
    # for bisect_left it gets the most left INDEX in the list that is equal to value
    smaller = bisect.bisect_left(inputSorted, value)
    ans[index] = smaller - num[index]

res = ''

for x in ans:
    res += str(x) + " "

print res
