import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinPackingSolver {

    // 1. BRUTE FORCE

    static int minBins = Integer.MAX_VALUE;
    public static int solveBruteForce(int[] items, int capacity) {
        minBins = Integer.MAX_VALUE;
        int[] bins = new int[items.length]; 
        packItems(items, capacity, bins, 0);
        return minBins;
    }

    private static void packItems(int[] items, int capacity, int[] bins, int index) {
        if (index == items.length) {
            int usedBins = 0;
            for (int bin : bins) if (bin > 0) usedBins++;
            minBins = Math.min(minBins, usedBins);
            return;
        }

        for (int i = 0; i < bins.length; i++) {
            if (bins[i] + items[index] <= capacity) {
                bins[i] += items[index];
                packItems(items, capacity, bins, index + 1);
                bins[i] -= items[index]; // Backtrack
            }
            if (bins[i] == 0) break;
        }
    }

    // 2. GREEDY APPROACH 

    public static int solveFFD(int[] items, int capacity) {
        Integer[] objItems = Arrays.stream(items).boxed().toArray(Integer[]::new);
        Arrays.sort(objItems, Collections.reverseOrder());
        List<Integer> bins = new ArrayList<>();
        
        for (int item : objItems) {
            boolean placed = false;
            for (int i = 0; i < bins.size(); i++) {
                if (bins.get(i) + item <= capacity) {
                    bins.set(i, bins.get(i) + item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                bins.add(item); 
            }
        }
        return bins.size();
    }

    public static void main(String[] args) {
        int[] items = {2, 5, 4, 7, 1, 3, 8};
        int capacity = 10;

        long start1 = System.nanoTime();
        System.out.println("Brute Force : " + solveBruteForce(items, capacity) + " bins (Time: " + (System.nanoTime() - start1)/1000 + " µs)");

        long start2 = System.nanoTime();
        System.out.println("Greedy FFD  : " + solveFFD(items, capacity) + " bins (Time: " + (System.nanoTime() - start2)/1000 + " µs)");
    }
}