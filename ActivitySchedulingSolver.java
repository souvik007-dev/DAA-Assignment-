import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivitySchedulingSolver {


    public static class Activity {
        int start;
        int finish;

        public Activity(String name, int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    // 1. BRUTE FORCE APPROACH 

    public static int solveBruteForce(Activity[] activities) {
        int n = activities.length;
        int maxActivities = 0;
        int totalSubsets = 1 << n; 

        
        for (int mask = 1; mask < totalSubsets; mask++) {
            List<Activity> currentSubset = new ArrayList<>();         
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentSubset.add(activities[i]);
                }
            }
            if (isValidSchedule(currentSubset)) {
                maxActivities = Math.max(maxActivities, currentSubset.size());
            }
        }

        return maxActivities;
    }

    private static boolean isValidSchedule(List<Activity> subset) {
        subset.sort((a, b) -> Integer.compare(a.start, b.start));

        for (int i = 1; i < subset.size(); i++) {
            if (subset.get(i).start < subset.get(i - 1).finish) {
                return false; 
            }
        }
        return true;
    }

    // 2. GREEDY APPROACH 
    
    public static int solveGreedy(Activity[] activities) {
        if (activities.length == 0) return 0;
        Arrays.sort(activities, (a, b) -> Integer.compare(a.finish, b.finish));
        int count = 1; 
        int lastSelectedFinishTime = activities[0].finish;

        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastSelectedFinishTime) {
                count++;
                lastSelectedFinishTime = activities[i].finish;
            }
        }

        return count;
    }

    
    public static void main(String[] args) {
       
        Activity[] activities = {
            new Activity("A", 1, 4),
            new Activity("B", 3, 5),
            new Activity("C", 0, 6),
            new Activity("D", 5, 7),
            new Activity("E", 3, 8),
            new Activity("F", 5, 9),
            new Activity("G", 6, 10),
            new Activity("H", 8, 11),
            new Activity("I", 8, 12),
            new Activity("J", 2, 13),
            new Activity("K", 12, 14)
        };

        System.out.println("--- Activity Scheduling Execution Results ---");
        
        // 1. Call Brute Force

        long start1 = System.nanoTime();
        int result1 = solveBruteForce(activities);
        long end1 = System.nanoTime();
        System.out.println("Brute Force Result : " + result1 + " activities (Time: " + (end1 - start1) / 1000 + " µs)");

        // 2. Call Greedy

        long start2 = System.nanoTime();
        int result2 = solveGreedy(activities);
        long end2 = System.nanoTime();
        System.out.println("Greedy Result      : " + result2 + " activities (Time: " + (end2 - start2) / 1000 + " µs)");
    }
}