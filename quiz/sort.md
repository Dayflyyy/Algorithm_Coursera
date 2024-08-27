# Sort quiz1

## Question 1
Intersection of two sets. Given two arrays
a
[
]
a[] and
b
[
]
b[], each containing
n
n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array
a
[
]
a[] and array
b
[
]
b[].

### answer
make a compare role that same x and same y rerturn 0,same x bigger y return 1 and -1, smaller x return -1, sort b and make every a[i] binary search in b


## Question 2
3.
Question 3
Dutch national flag. Given an array of
n
n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

s
w
a
p
(
i
,
j
)
swap(i,j):  swap the pebble in bucket
i
i with the pebble in bucket
j
j.

c
o
l
o
r
(
i
)
color(i): determine the color of the pebble in bucket
i
i.

The performance requirements are as follows:

At most
n
n calls to
c
o
l
o
r
(
)
color().

At most
n
n calls to
s
w
a
p
(
)
swap().

Constant extra space.

遍历一遍数组，蓝的插到链表最后，白的插到链表最前
