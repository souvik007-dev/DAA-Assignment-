import java.util.Arrays;

public class FractionalKnapsackSolver {

    
    public static class Item {
        int profit;
        int weight;

        public Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }
    }

 
    // 1. BRUTE FORCE APPROACH (Permutations)
   
    static double maxBruteForceProfit = 0.0;

    public static double solveBruteForce(Item[] items, int capacity) {
        maxBruteForceProfit = 0.0;
        generatePermutations(items, capacity, 0);
        return maxBruteForceProfit;
    }

  
    private static void generatePermutations(Item[] items, int capacity, int index) {
        if (index == items.length - 1) {
            evaluatePermutation(items, capacity);
            return;
        }
        for (int i = index; i < items.length; i++) {
            swap(items, i, index);
            generatePermutations(items, capacity, index + 1);
            swap(items, i, index); 
        }
    }

    
    private static void evaluatePermutation(Item[] items, int capacity) {
        double currentProfit = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                
                currentWeight += item.weight;
                currentProfit += item.profit;
            } else {
                int remainingCapacity = capacity - currentWeight;
                currentProfit += item.profit * ((double) remainingCapacity / item.weight);
                break; 
            }
        }
        maxBruteForceProfit = Math.max(maxBruteForceProfit, currentProfit);
    }

    private static void swap(Item[] items, int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    
    // 2. GREEDY APPROACH (Optimal Ratio)

    public static double solveGreedy(Item[] items, int capacity) {
    
        Arrays.sort(items, (item1, item2) -> {
    double ratio1 = (double) item1.profit / item1.weight;
    double ratio2 = (double) item2.profit / item2.weight;
    return Double.compare(ratio2, ratio1);
});

        double totalProfit = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
         
                currentWeight += item.weight;
                totalProfit += item.profit;
            } else {
               
                int remainingCapacity = capacity - currentWeight;
                totalProfit += item.profit * ((double) remainingCapacity / item.weight);
                break; 
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;

        System.out.println("--- Fractional Knapsack Execution Results ---");
        
        // 1. Call Brute Force (Permutation)

        Item[] bruteForceItems = items.clone(); 
        long start1 = System.nanoTime();
        double result1 = solveBruteForce(bruteForceItems, capacity);
        long end1 = System.nanoTime();
        System.out.println("Brute Force Result : " + result1 + " (Time: " + (end1 - start1) + " ns)");

        // 2. Call Greedy

        Item[] greedyItems = items.clone();
        long start2 = System.nanoTime();
        double result2 = solveGreedy(greedyItems, capacity);
        long end2 = System.nanoTime();
        System.out.println("Greedy Result      : " + result2 + " (Time: " + (end2 - start2) + " ns)");
    }
}