import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1349
Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..

Students must be placed in seats in good condition.



Example 1:


Input: seats = [["#",".","#","#",".","#"],
                [".","#","#","#","#","."],
                ["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
Example 2:

Input: seats = [[".","#"],
                ["#","#"],
                ["#","."],
                ["#","#"],
                [".","#"]]
Output: 3
Explanation: Place all students in available seats.

Example 3:

Input: seats = [["#",".",".",".","#"],
                [".","#",".","#","."],
                [".",".","#",".","."],
                [".","#",".","#","."],
                ["#",".",".",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.


Constraints:

seats contains only characters '.' and'#'.
m == seats.length
n == seats[i].length
1 <= m <= 8
1 <= n <= 8
 */
public class MaxStudentsTakingExam {
    // https://leetcode.com/problems/maximum-students-taking-exam/discuss/503568/Accepted-Java-DP-solution
    // Intuition: current selection of putting student on i,j or not only depends on the last column, so the status is no bigger than 2^8
    // DP Status: we are at i,j. last column status (which rows are taken) stored in last, current column status stored in cur
    // O(m*n**2^n*2^n). A quicker version is mentioned below
    int[][][][] dp;

    public int maxStudents(char[][] s) {
        int m = s.length;
        int n = s[0].length;
        dp = new int[m][n][1 << m][1 << m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        return domax(0, 0, 0, 0, s);
    }

    int domax(int i, int j, int last, int cur, char[][] s) {

        int m = s.length;
        int n = s[0].length;
        if (i == m || j == n) {
            return 0;
        }
        if (dp[i][j][last][cur] != -1) {
            return dp[i][j][last][cur];
        }
        int nop = i + 1 < m ? domax(i + 1, j, last, cur, s) : domax(0, j + 1, cur, 0, s);
        if (s[i][j] == '#') {
            dp[i][j][last][cur] = nop;
            return nop;
        }
        if (bad(last, i)) {
            dp[i][j][last][cur] = nop;
            return nop;
        }
        int ns = cur | (1 << i);
        int p = i + 1 < m ? 1 + domax(i + 1, j, last, ns, s) : 1 + domax(0, j + 1, ns, 0, s);
        int rt = Math.max(p, nop);
        dp[i][j][last][cur] = rt;
        return rt;
    }

    private boolean bad(int last, int i) {
        if (((last >> i) & 1) == 1) {
            return true;
        }
        if (((last >> (i - 1)) & 1) == 1) {
            return true;
        }
        if (((last >> (i + 1)) & 1) == 1) {
            return true;
        }
        return false;
    }


}

class MaxStudentsTakingExamByRow {
    // row by row using bit mask tricks to reduce the O(n) cost for conflict checking
    // O(m*2^n*2^n)
    int[][] dp;

    public int maxStudents(char[][] s) {
        int m = s.length;
        int n = s[0].length;
        dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return domax(0, 0, s);
    }

    int domax(int i, int last, char[][] s) {
        int m = s.length;
        int n = s[0].length;
        if (i == m) {
            return 0;
        }
        if (dp[i][last] != -1) {
            return dp[i][last];
        }
        int max = 0;
        int rowblockmask = 0;
        for (int j = 0; j < n; j++) {
            if (s[i][j] == '#') {
                rowblockmask |= 1 << j;
            }
        }
        for (int j = 0; j < (1 << n); j++) {
            boolean hasinvalid = (j & rowblockmask) != 0;
            boolean hasleftright = (j & (j << 1)) != 0;
            boolean hasupperleft = (j & (last >> 1)) != 0;
            boolean hasupperright = (j & (last << 1)) != 0;
            if (hasinvalid || hasleftright || hasupperleft || hasupperright) {
                continue;
            }
            int cur = Integer.bitCount(j) + domax(i + 1, j, s);
            max = Math.max(max, cur);
        }
        dp[i][last] = max;
        return max;
    }

}