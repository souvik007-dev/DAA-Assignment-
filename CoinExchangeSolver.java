import java.util.Arrays;

public class CoinExchangeSolver {

    // 1. BRUTE FORCE APPROACH 

    public static int solveBruteForce(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            int result = solveBruteForce(coins, amount - coin);
            if (result >= 0 && result < minCoins) {
                minCoins = result + 1; 
            }
        }

        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // 2. GREEDY APPROACH 

    public static int solveGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        int remainingAmount = amount;

        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];
            if (coin <= remainingAmount) {
               
                int numOfCoins = remainingAmount / coin;
                count += numOfCoins;
                remainingAmount -= (numOfCoins * coin);
            }
            if (remainingAmount == 0) break;
        }

        return remainingAmount == 0 ? count : -1;
    }

  
    // 3. DYNAMIC PROGRAMMING 
   
    public static int solveDP(int[] coins, int amount) {
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        int targetAmount = 10;

        System.out.println("--- Coin Exchange Execution Results ---");
        System.out.println("Coins: " + Arrays.toString(coins) + " | Target: " + targetAmount + "\n");

        // 1. Call Brute Force

        long start1 = System.nanoTime();
        int result1 = solveBruteForce(coins, targetAmount);
        long end1 = System.nanoTime();
        System.out.println("Brute Force Result : " + result1 + " coins (Time: " + (end1 - start1) / 1000 + " µs)");

        // 2. Call Greedy
        
        int[] greedyCoins = coins.clone(); 
        long start2 = System.nanoTime();
        int result2 = solveGreedy(greedyCoins, targetAmount);
        long end2 = System.nanoTime();
        System.out.println("Greedy Result      : " + result2 + " coins (Time: " + (end2 - start2) / 1000 + " µs) -> *SUBOPTIMAL!*");

        // 3. Call DP

        long start3 = System.nanoTime();
        int result3 = solveDP(coins, targetAmount);
        long end3 = System.nanoTime();
        System.out.println("Tabulation DP      : " + result3 + " coins (Time: " + (end3 - start3) / 1000 + " µs)");
    }
}