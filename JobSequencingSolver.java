import java.util.Arrays;

public class JobSequencingSolver {
    
    public static class Job {
        char id;
        int deadline, profit;
        
        public Job(char id, int deadline, int profit) {
            this.id = id; 
            this.deadline = deadline; 
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Job " + id;
        }
    }

    // 1. BRUTE FORCE
    
    static int maxProfit = 0;

    public static int solveBruteForce(Job[] jobs) {
        maxProfit = 0;
        int maxDeadline = Arrays.stream(jobs).mapToInt(j -> j.deadline).max().orElse(0);
        Job[] schedule = new Job[maxDeadline + 1];
        backtrack(jobs, schedule, 0, 0);
        return maxProfit;
    }

    private static void backtrack(Job[] jobs, Job[] schedule, int index, int currentProfit) {
        if (index == jobs.length) {
            maxProfit = Math.max(maxProfit, currentProfit);
            return;
        }

        for (int t = jobs[index].deadline; t > 0; t--) {
            if (schedule[t] == null) {
                schedule[t] = jobs[index];
                backtrack(jobs, schedule, index + 1, currentProfit + jobs[index].profit);
                schedule[t] = null; 
            }
        }
        backtrack(jobs, schedule, index + 1, currentProfit);
    }

    // 2. GREEDY APPROACH

    public static int solveGreedy(Job[] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit));
        
        int maxDeadline = Arrays.stream(jobs).mapToInt(j -> j.deadline).max().orElse(0);
        boolean[] slots = new boolean[maxDeadline + 1];
        int totalProfit = 0;

        for (Job job : jobs) {
            for (int j = job.deadline; j > 0; j--) {
                if (!slots[j]) {
                    slots[j] = true;
                    totalProfit += job.profit;
                    break;
                }
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('a', 4, 20), new Job('b', 1, 10), new Job('c', 1, 40),
            new Job('d', 1, 30), new Job('e', 3, 50)
        };

        long start1 = System.nanoTime();
        System.out.println("Brute Force : " + solveBruteForce(jobs) + " profit (Time: " + (System.nanoTime() - start1)/1000 + " µs)");

        long start2 = System.nanoTime();
        System.out.println("Greedy Alg  : " + solveGreedy(jobs) + " profit (Time: " + (System.nanoTime() - start2)/1000 + " µs)");
    }
}