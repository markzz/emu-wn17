# This program is to verify a conjecture while doing the third exploration
# assignment.
#
# The conjecture is as follows:
#     A and B are FINITE NON EMPTY sets where |A| = r and |B| = s
#
#     If a function is defined f: A -> B, then there will be s ** r
#     different possibilities for the function.
#
#     This program tests to see whether this is true for sets ranging
#     from size 1 to size 5 (in any order).

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

# this is the main loop, this just loops through any possibility of either list
# with cardinality of 1-5
for x in range(1, limit+1):
    for y in range(1, limit+1):
        # generate list of functions and get its length
        list3 = [list(zip(list1[:x], item)) for item in product(list2[:y], repeat=len(list1[:x]))]
        z = len(list3)

        # this is the test to see if the conjecture is correct, if it's not for
        # ANY case, it will say so
        if y ** x != z:
            print("r: %d; s: %d; FALSE" % (x, y))

