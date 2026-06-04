public class KnapsackSolver {

    // 1. BRUTE FORCE APPROACH

    public static int solveBruteForce(int[] weights, int[] profits, int capacity, int n) {
        
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return solveBruteForce(weights, profits, capacity, n - 1);
        } else {
            int include = profits[n - 1] + solveBruteForce(weights, profits, capacity - weights[n - 1], n - 1);
            int exclude = solveBruteForce(weights, profits, capacity, n - 1);
            return Math.max(include, exclude);
        }
    }

    // 2. TOP-DOWN DP (MEMOIZATION)

    public static int solveMemoization(int[] weights, int[] profits, int capacity) {
        Integer[][] memo = new Integer[profits.length + 1][capacity + 1];
        return memoHelper(weights, profits, capacity, profits.length, memo);
    }

    private static int memoHelper(int[] weights, int[] profits, int capacity, int n, Integer[][] memo) {
  
        if (n == 0 || capacity == 0) {
            return 0;
        }

        
        if (memo[n][capacity] != null) {
            return memo[n][capacity];
        }

        if (weights[n - 1] > capacity) {
            memo[n][capacity] = memoHelper(weights, profits, capacity, n - 1, memo);
        } else {
            int include = profits[n - 1] + memoHelper(weights, profits, capacity - weights[n - 1], n - 1, memo);
            int exclude = memoHelper(weights, profits, capacity, n - 1, memo);
            memo[n][capacity] = Math.max(include, exclude);
        }

        return memo[n][capacity];
    }

    // 3. BOTTOM-UP DP (TABULATION)

    public static int solveTabulation(int[] weights, int[] profits, int capacity) {
        int n = profits.length;
        int[][] dp = new int[n + 1][capacity + 1];

        
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    int include = profits[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }


    public static void main(String[] args) {
        
        int[] profits = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int n = profits.length;

        System.out.println("--- 0/1 Knapsack Execution Results ---");
        
        // 1. Call Brute Force
        long start1 = System.nanoTime();
        int result1 = solveBruteForce(weights, profits, capacity, n);
        long end1 = System.nanoTime();
        System.out.println("Brute Force Result : " + result1 + " (Time: " + (end1 - start1) + " ns)");

        // 2. Call Memoization
        long start2 = System.nanoTime();
        int result2 = solveMemoization(weights, profits, capacity);
        long end2 = System.nanoTime();
        System.out.println("Memoization Result : " + result2 + " (Time: " + (end2 - start2) + " ns)");

        // 3. Call Tabulation
        long start3 = System.nanoTime();
        int result3 = solveTabulation(weights, profits, capacity);
        long end3 = System.nanoTime();
        System.out.println("Tabulation Result  : " + result3 + " (Time: " + (end3 - start3) + " ns)");
    }
}