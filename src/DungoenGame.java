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
    public int calculateMinimumHP(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int cur = -a[i][j]; // cur is the demand of this cell
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = cur > 0 ? 1 + cur : 1;
                } else if (i == m - 1) {
                    // should max with 1 because at any cell we can't drop below 1
                    dp[i][j] = max(dp[i][j + 1] + cur, 1);
                } else if (j == n - 1) {
                    dp[i][j] = max(dp[i + 1][j] + cur, 1);
                } else {
                    int right = max(dp[i][j + 1] + cur, 1);
                    int down = max(dp[i + 1][j] + cur, 1);
                    dp[i][j] = min(right, down);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new DungoenGame().calculateMinimumHP(ArrayUtils.read("[[-2,-3,3],[-5,-10,1],[10,30,-5]]")));
    }
}
