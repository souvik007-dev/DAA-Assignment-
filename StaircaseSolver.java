public class StaircaseSolver {

    // 1. BRUTE FORCE

    public static int solveBruteForce(int n) {
        if (n <= 1) return 1;
        return solveBruteForce(n - 1) + solveBruteForce(n - 2);
    }

    // 2. DYNAMIC PROGRAMMING

    public static int solveDP(int n) {
        if (n <= 1) return 1;
        int prev2 = 1; 
        int prev1 = 1; 
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void main(String[] args) {
        int n = 35;
        
        long start1 = System.nanoTime();
        int res1 = solveBruteForce(n);
        System.out.println("Brute Force : " + res1 + " ways (Time: " + (System.nanoTime() - start1)/1000000 + " ms)");

        long start2 = System.nanoTime();
        int res2 = solveDP(n);
        System.out.println("DP Approach : " + res2 + " ways (Time: " + (System.nanoTime() - start2)/1000 + " µs)");
    }
}