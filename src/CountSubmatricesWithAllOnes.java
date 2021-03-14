import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1504
Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.



Example 1:

Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2.
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:

Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3.
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2.
There are 2 rectangles of side 3x1.
There is 1 rectangle of side 3x2.
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
Example 3:

Input: mat = [[1,1,1,1,1,1]]
Output: 21
Example 4:

Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5


Constraints:

1 <= rows <= 150
1 <= columns <= 150
0 <= mat[i][j] <= 1
 */
public class CountSubmatricesWithAllOnes {
    // note this is submatrice, not square. can be rectangular
    // Om^2*n. we convert to sth like 2d subarray problem
    // find the one streaks in each i-j segment. each full column would contribute to k-start+1 matrices ending at that column
    public int numSubmat(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                sum[i][j] = (i == 0 ? 0 : sum[i - 1][j]) + a[i][j];
            }
        }
        // looking at each column, how many matrices are ending with this column?
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int start = -1; // the first full column in this streak
                for (int k = 0; k < n; k++) {
                    int cur = sum[j][k] - (i == 0 ? 0 : sum[i - 1][k]);
                    if (cur == j - i + 1) {
                        if (start == -1) {
                            res++;
                            start = k;
                        } else {
                            res += k - start + 1;
                        }
                    } else {
                        start = -1;
                    }
                }
            }
        }
        return res;
    }
}

class CountSubmatricesWithAllOnesOn2 {
    // find the first < on the left. note the rectangles is using current one + all < ones can do because we can just extend beyond it
    public int numSubmat(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] h = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                h[j] = a[i][j] == 0 ? 0 : h[j] + 1;
            }
            res += count(h);
        }
        return res;
    }

    private int count(int[] h) {
        int n = h.length;
        int[] dp = new int[n];
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && h[st.peek()] >= h[i]) {
                st.pop();
            }
            //pre is the first <
            int pre = st.isEmpty() ? -1 : st.peek();
            // pre+1...i
            int cur = (pre == -1 ? 0 : dp[pre]) + h[i] * (i - pre);
            dp[i] = cur;
            res += cur;
            st.push(i);
        }
        return res;
    }
}