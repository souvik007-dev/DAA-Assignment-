Assignment: Brute Force First, Then Famous Approaches

Objective
For each problem, implement:
1. Brute Force solution (exhaustive search, naive recursion, or enumeration).  
2. Famous algorithmic approach (Greedy, Dynamic Programming, or other canonical method).  
3. (For Coin Exchange only) All three approaches: brute force, greedy, and DP.



Problems to Implement

- 0/1 Knapsack  
  - Brute force: Generate all subsets of items, check weight ≤ capacity, track max profit.  
  - Famous approach: Dynamic Programming (table-based or memoized recursion).  

- Fractional Knapsack  
  - Brute force: Try all possible fractions (discretized or exhaustive splits).  
  - Famous approach: Greedy (sort by value/weight ratio, take highest first).  

- Activity Scheduling  
  - Brute force: Generate all subsets of activities, check feasibility.  
  - Famous approach: Greedy (sort by finish time, select compatible activities).  

- Coin Exchange  
  - Brute force: Recursive enumeration of all combinations.  
  - Greedy: Pick largest coin first (works for canonical coin systems like Indian currency).  
  - Dynamic Programming: Minimum coins using DP table.  

- Subset Generation  
  - Brute force: Recursive inclusion/exclusion.  
  - Famous approach: Bitmask iteration.  

- Staircase Problem (ways to climb n stairs with 1 or 2 steps)  
  - Brute force: Recursive enumeration of all paths.  
  - Famous approach: Dynamic Programming / Fibonacci relation.  

- Subset Existence with Condition (subset sum = target)  
  - Brute force: Generate all subsets, check sum.  
  - Famous approach: Dynamic Programming (subset sum table).  

- Traveling Salesman Problem  
  - Brute force: Try all permutations of cities.  
  - Famous approach: Dynamic Programming (Held-Karp algorithm).  

- Bin Packing  
  - Brute force: Try all possible assignments of items to bins.  
  - Famous approach: Greedy heuristics (Next Fit, First Fit, Best Fit, First Fit Decreasing).  

- Job Sequencing  
  - Brute force: Generate all possible job orders, check deadlines, compute profit.  
  - Famous approach: Greedy (sort jobs by profit, schedule in latest available slot before deadline).  



Deliverables
- Code for each problem in Java/Python/C++ (your choice).  
- Comparison table showing brute force vs famous approach complexities.  
- Short report (2–3 pages) explaining:  
  - Why brute force is inefficient.  
  - Why the famous approach is preferred.  
  - Observed runtime differences on sample inputs.  
