/*
LC#174
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */

import base.ArrayUtils;

import java.util.Arrays;

import static java.lang.Math.*;


public class DungoenGame {
    // go to the min path either on the right or down
    // at each cell, the health we need is next-current. if it's <=0, health needed is 1
    // at the final point, the "next" cell health is 1 to survive
    public int calculateMinimumHP(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = Math.max(1 - a[i][j], 1);
                } else if (i == m - 1) {
                    dp[i][j] = Math.max(dp[i][j + 1] - a[i][j], 1);
                } else if (j == n - 1) {
                    dp[i][j] = Math.max(dp[i + 1][j] - a[i][j], 1);
                } else {
                    int right = Math.max(dp[i][j + 1] - a[i][j], 1);
                    int down = Math.max(dp[i + 1][j] - a[i][j], 1);
                    dp[i][j] = Math.min(right, down);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new DungoenGame().calculateMinimumHP(ArrayUtils.read("[[-2,-3,3],[-5,-10,1],[10,30,-5]]")));
    }
}

class DungeonGameOptimized {
    // rolling array to get rid of one dimension
    public int calculateMinimumHP(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] dp = new int[n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[j] = Math.max(1 - a[i][j], 1);
                } else if (i == m - 1) {
                    dp[j] = Math.max(dp[j + 1] - a[i][j], 1);
                } else if (j == n - 1) {
                    dp[j] = Math.max(dp[j] - a[i][j], 1);
                } else {
                    int right = Math.max(dp[j + 1] - a[i][j], 1);
                    int down = Math.max(dp[j] - a[i][j], 1);
                    dp[j] = Math.min(right, down);
                }
            }
        }
        return dp[0];
    }
}