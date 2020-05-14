import java.util.Arrays;

/*
LC#1035
We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.



Example 1:


Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2


Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
 */
public class UncrossedLines {
    // lcs in disguise
    int[][] dp;

    public int maxUncrossedLines(int[] a, int[] b) {
        dp = new int[a.length][b.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(0, 0, a, b);
    }

    int dom(int i, int j, int[] a, int[] b) {
        if (i >= a.length || j >= b.length) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int w1 = dom(i + 1, j, a, b);
        int w2 = dom(i, j + 1, a, b);
        int w3 = 0;
        if (a[i] == b[j]) {
            // no need to loop.ccover all i j eventually anyway via w1 and w2
            w3 = 1 + dom(i + 1, j + 1, a, b);
        }
        int rt = Math.max(w1, Math.max(w2, w3));
        dp[i][j] = rt;
        return rt;
    }
}
