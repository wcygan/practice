## CLRS Chapter 15: Dynamic Programming

We typically apply dynamic programming to ***optimization problems***. Such problems can have many possible solutions.
Each solution has a value, and we wish to find a solution with the optimal (minimum or maximum) value. We call such a
solution *an* optimal solution to the problem, as opposed to *the* optimal solution, since there may be several
solutions that achieve the optimal value.

When developing a dynamic-programming algorithm, we follow a sequence of four steps:

1. Characterize the structure of an optimal solution
2. Recursively define the value of an optimal solution
3. Compute the value of an optimal solution, typically in a bottom-up fashion
4. Construct an optimal solution from computed information

