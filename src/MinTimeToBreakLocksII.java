import base.ArrayUtils;

import java.util.Arrays;

public class MinTimeToBreakLocksII {
    // Hungarian algo for matching
    public int findMinimumTime(int[] strength) {
        int n = strength.length;

        // Build the cost matrix
        // cost[i][j] = ceil(strength[i]/(j+1))
        // To avoid floating-point operations, use integer arithmetic:
        // ceil(a/b) = (a + b - 1) / b
        // Here: ceil(strength[i] / (j+1)) = (strength[i] + j) / (j+1) is not always correct
        // Correct approach: (strength[i] + (j+1) - 1)/(j+1)
        // = (strength[i] + j) / (j+1)
        // This works the same as ceil if done as integer division.

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = (strength[i] + j) / (j + 1);
            }
        }

        // Solve assignment using Hungarian algorithm
        return hungarianMinCost(cost);
    }

    // Hungarian algorithm (Kuh-Munkres) implementation
    // Finds a minimum cost matching for a given cost matrix
    // costMatrix is n x n
    private int hungarianMinCost(int[][] costMatrix) {
        int n = costMatrix.length;

        int[] u = new int[n + 1];
        int[] v = new int[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[0] = i;
            int j0 = 0; // column
            int[] minv = new int[n + 1];
            Arrays.fill(minv, Integer.MAX_VALUE);
            boolean[] used = new boolean[n + 1];

            do {
                used[j0] = true;
                int i0 = p[j0], j1 = 0;
                int delta = Integer.MAX_VALUE;

                for (int j = 1; j <= n; j++) {
                    if (!used[j]) {
                        int cur = costMatrix[i0 - 1][j - 1] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            way[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                }
                for (int j = 0; j <= n; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        // p[j] = matched row for column j
        // We want sum of costs for these assignments
        int result = 0;
        for (int j = 1; j <= n; j++) {
            result += costMatrix[p[j] - 1][j - 1];
        }
        return result;
    }

    // For manual testing
    public static void main(String[] args) {
        MinTimeToBreakLocksII sol = new MinTimeToBreakLocksII();

        // Example 1
        int[] strength1 = {3, 4, 1};
        System.out.println(sol.findMinimumTime(strength1)); // Expected 4

        // Example 2
        int[] strength2 = {2, 5, 4};
        System.out.println(sol.findMinimumTime(strength2)); // Expected 6
    }
}


