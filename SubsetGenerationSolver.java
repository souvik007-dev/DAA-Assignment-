import java.util.ArrayList;
import java.util.List;

public class SubsetGenerationSolver {

    // 1. BRUTE FORCE APPROACH 

    public static List<List<Integer>> generateBruteForce(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), allSubsets);
        return allSubsets;
    }

    private static void backtrack(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> allSubsets) {
        if (index == nums.length) {
            allSubsets.add(new ArrayList<>(currentSubset));
            return;
        }

       
        currentSubset.add(nums[index]);
        backtrack(nums, index + 1, currentSubset, allSubsets);

        currentSubset.remove(currentSubset.size() - 1);
        backtrack(nums, index + 1, currentSubset, allSubsets);
    }

    // 2. BITMASK APPROACH 

    public static List<List<Integer>> generateBitmask(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n;


        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> currentSubset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentSubset.add(nums[i]);
                }
            }
            allSubsets.add(currentSubset);
        }

        return allSubsets;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("--- Subset Generation Execution Results ---");
        System.out.println("Input Array: [1, 2, 3]\n");

        // 1. Call Brute Force

        long start1 = System.nanoTime();
        List<List<Integer>> result1 = generateBruteForce(nums);
        long end1 = System.nanoTime();
        System.out.println("Brute Force Subsets : " + result1);
        System.out.println("Brute Force Time    : " + (end1 - start1) / 1000 + " µs\n");

        // 2. Call Bitmask
        
        long start2 = System.nanoTime();
        List<List<Integer>> result2 = generateBitmask(nums);
        long end2 = System.nanoTime();
        System.out.println("Bitmask Subsets     : " + result2);
        System.out.println("Bitmask Time        : " + (end2 - start2) / 1000 + " µs");
    }
}