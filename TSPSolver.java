import java.util.Arrays;

public class TSPSolver {

    // 1. BRUTE FORCE

    static int minPathCost = Integer.MAX_VALUE;
    
    public static int solveBruteForce(int[][] graph) {
        minPathCost = Integer.MAX_VALUE;
        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        dfs(graph, visited, 0, graph.length, 1, 0);
        return minPathCost;
    }

    private static void dfs(int[][] graph, boolean[] visited, int currPos, int n, int count, int cost) {
        if (count == n && graph[currPos][0] > 0) {
            minPathCost = Math.min(minPathCost, cost + graph[currPos][0]);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currPos][i] > 0) {
                visited[i] = true;
                dfs(graph, visited, i, n, count + 1, cost + graph[currPos][i]);
                visited[i] = false;
            }
        }
    }

    // 2. HELD-KARP DP 

    public static int solveHeldKarp(int[][] graph) {
        int n = graph.length;
        int VISITED_ALL = (1 << n) - 1;
        int[][] memo = new int[n][1 << n];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        return tspDP(graph, memo, 1, 0, VISITED_ALL, n);
    }

    private static int tspDP(int[][] graph, int[][] memo, int mask, int pos, int VISITED_ALL, int n) {
        if (mask == VISITED_ALL) return graph[pos][0];
        if (memo[pos][mask] != -1) return memo[pos][mask];

        int ans = Integer.MAX_VALUE;
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newAns = graph[pos][city] + tspDP(graph, memo, mask | (1 << city), city, VISITED_ALL, n);
                ans = Math.min(ans, newAns);
            }
        }
        return memo[pos][mask] = ans;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        long start1 = System.nanoTime();
        System.out.println("Brute Force : " + solveBruteForce(graph) + " (Time: " + (System.nanoTime() - start1)/1000 + " µs)");

        long start2 = System.nanoTime();
        System.out.println("Held-Karp DP: " + solveHeldKarp(graph) + " (Time: " + (System.nanoTime() - start2)/1000 + " µs)");
    }
}