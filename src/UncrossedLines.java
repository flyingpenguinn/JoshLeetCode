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
    // dpij means the best we can get UP TO ij. so we need to pass i-1j and ij-1 onto ij
    public int maxUncrossedLines(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int[][] dp = new int[an + 1][bn + 1];
        // when i==0 or j==0 nothing to pair with, hence 0
        for (int i = 1; i <= an; i++) {
            for (int j = 1; j <= bn; j++) {
                int way1 = a[i - 1] == b[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                int way2 = dp[i][j - 1];
                int way3 = dp[i - 1][j];
                dp[i][j] = Math.max(way1, Math.max(way2, way3));
            }
        }
        return dp[an][bn];
    }
}
