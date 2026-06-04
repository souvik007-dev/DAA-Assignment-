public class SubsetSumSolver {

    // 1. BRUTE FORCE
    
    public static boolean solveBruteForce(int[] nums, int n, int target) {
        if (target == 0) return true;
        if (n == 0) return false;

        if (nums[n - 1] > target) {
            return solveBruteForce(nums, n - 1, target);
        }
        return solveBruteForce(nums, n - 1, target) || 
               solveBruteForce(nums, n - 1, target - nums[n - 1]);
    }

    // 2. DYNAMIC PROGRAMMING

    public static boolean solveDP(int[] nums, int target) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;

        long start1 = System.nanoTime();
        boolean res1 = solveBruteForce(nums, nums.length, target);
        System.out.println("Brute Force : " + res1 + " (Time: " + (System.nanoTime() - start1)/1000 + " µs)");

        long start2 = System.nanoTime();
        boolean res2 = solveDP(nums, target);
        System.out.println("DP Approach : " + res2 + " (Time: " + (System.nanoTime() - start2)/1000 + " µs)");
    }
}