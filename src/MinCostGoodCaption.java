import java.util.Arrays;

public class MinCostGoodCaption {

    // We'll store the DP array as two separate 3D arrays:
    //   dpCost[i][last][cnt] = minimal cost for substring caption[i..], given 'last' and 'cnt'
    //   dpNext[i][last][cnt] = which character (0..25) to use at index i in that solution
    // If dpCost[i][last][cnt] == -1 => no solution

    private static final int NO_SOLUTION = -1;

    public String minCostGoodCaption(String caption) {
        int n = caption.length();
        // If n < 3 => impossible to form blocks of length >= 3
        if (n < 3) {
            return "";
        }

        char[] arr = caption.toCharArray();
        // We'll have dpCost[i][last][cnt], dpNext[i][last][cnt]
        //   i in [0..n], last in [0..25], cnt in [0..3]
        // dpCost is length (n+1) to handle the base case dp[n][...][...].
        int[][][] dpCost = new int[n + 1][26][4];
        int[][][] dpNext = new int[n + 1][26][4];

        // Initialize everything to -1 (meaning no solution)
        for (int i = 0; i <= n; i++) {
            for (int last = 0; last < 26; last++) {
                Arrays.fill(dpCost[i][last], NO_SOLUTION);
                Arrays.fill(dpNext[i][last], -1);
            }
        }

        // Base cases:
        // For i = n, if cnt >= 3 => cost=0, else no solution.
        for (int last = 0; last < 26; last++) {
            for (int cnt = 0; cnt <= 3; cnt++) {
                if (cnt >= 3) {
                    dpCost[n][last][cnt] = 0;  // "GOOD" in your C++ code
                    dpNext[n][last][cnt] = 26; // dummy nextChar
                } else {
                    dpCost[n][last][cnt] = NO_SOLUTION; // "BAD"
                }
            }
        }

        // Fill from i = n-1 down to 0
        for (int i = n - 1; i >= 0; i--) {
            int original = arr[i] - 'a'; // numeric for 'a'..'z'
            for (int last = 0; last < 26; last++) {
                for (int cnt = 0; cnt <= 3; cnt++) {
                    int bestCost = NO_SOLUTION;
                    int bestCh = -1;  // which character to choose at s[i]

                    // Try all possible ch in [0..25]
                    for (int ch = 0; ch < 26; ch++) {
                        // If 1 <= cnt < 3, we cannot change char away from 'last'
                        // => must pick ch == last
                        if (cnt >= 1 && cnt < 3 && ch != last) {
                            continue;
                        }
                        // cost to change s[i] to char ch
                        int changeCost = Math.abs(original - ch);

                        // nextCnt = min(3, cnt+1) if ch==last, else 1
                        int nextCnt;
                        if (ch == last) {
                            nextCnt = Math.min(3, cnt + 1);
                        } else {
                            nextCnt = 1;
                        }

                        // The cost from the subproblem (i+1, ch, nextCnt)
                        int subCost = dpCost[i + 1][ch][nextCnt];
                        if (subCost == NO_SOLUTION) {
                            continue; // skip invalid
                        }
                        int totalCost = changeCost + subCost;

                        // Compare with current best
                        if (bestCost == NO_SOLUTION || totalCost < bestCost) {
                            bestCost = totalCost;
                            bestCh = ch;
                        } else if (totalCost == bestCost && ch < bestCh) {
                            // tie => pick lexicographically smaller
                            bestCh = ch;
                        }
                    }
                    dpCost[i][last][cnt] = bestCost;
                    dpNext[i][last][cnt] = bestCh;
                }
            }
        }

        // Our desired final answer is dp[0][0][0], i.e. index=0, "last=0" => 'a', but effectively no last,
        // and cnt=0 => haven't formed any block yet.
        if (dpCost[0][0][0] == NO_SOLUTION) {
            // No solution
            return "";
        }

        // Reconstruct the solution
        StringBuilder sb = new StringBuilder(n);
        sb.setLength(n);

        int i = 0;
        int lastChar = 0; // 0 => 'a', but logically it's "dummy" at the start
        int cnt = 0;

        while (i < n) {
            int pick = dpNext[i][lastChar][cnt];
            if (pick < 0 || pick > 25) {
                // shouldn't happen if there's a valid solution
                return "";
            }
            // place that letter
            sb.setCharAt(i, (char) (pick + 'a'));

            // update block length
            if (pick == lastChar) {
                cnt = Math.min(3, cnt + 1);
            } else {
                cnt = 1;
            }
            lastChar = pick;

            i++;
        }

        return sb.toString();
    }
}



