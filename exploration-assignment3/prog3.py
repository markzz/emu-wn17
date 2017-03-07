# This program is to figure out how many onto functions there are given
# an r and s value.

from itertools import product

# limit cardinality of lists (so the computer doesn't have to take forever)
# A little warning, the order is O(n**n) whatever limit is set to, so don't
# make it too large.
#
# 5 is a reasonable limit
limit = 5

# generate template sets
list1 = [x for x in range(1, limit+1)]
list2 = [x for x in range(1, limit+1)]

# some other needed variables
tmp = []
is_oto = True
count = 0

# this is the main loop, this just loops through any possibility of either list
# with cardinality of 1-5
for x in range(1, limit+1):
    for y in range(1, limit+1):
        # generate list of functions and set count to 0
        list3 = [list(zip(list1[:x], item)) for item in product(list2[:y], repeat=len(list1[:x]))]
        count = 0

        # loop through and count how many one-to-one functions there are
        for z in range(0, len(list3)):
            tmp = []
            
            for w in range(0, len(list3[z])):
                if list3[z][w][1] not in tmp:
                    tmp.append(list3[z][w][1])

            if set(tmp) == set(list2[:y]):
                count += 1

        print("r: %d; s: %d; onto functions: %d" % (x, y, count))


