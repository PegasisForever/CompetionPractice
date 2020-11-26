flips = input()

hCount = 0
vCount = 0

for c in flips:
    if c == "H":
        hCount += 1
    else:
        vCount += 1

hCount %= 2
vCount %= 2
if hCount == 0 and vCount == 0:
    print("1 2")
    print("3 4")
elif hCount == 0 and vCount == 1:
    print("2 1")
    print("4 3")
elif hCount == 1 and vCount == 0:
    print("3 4")
    print("1 2")
else:
    print("4 3")
    print("2 1")

