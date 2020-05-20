import base.ArrayUtils;

import java.util.*;

/*
LC#656
Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:

Input: [1,2,4,-1,2], 2
Output: [1,3,5]


Example 2:

Input: [1,2,4,-1,2], 1
Output: []


Note:

Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].
 */
public class CoinPath {
    // similar to #1425!  can use a sliding window to optimize, though we could have used another for loop
    int Max = 1000000;

    public List<Integer> cheapestJump(int[] a, int b) {

        int n = a.length;
        if (a[n - 1] == -1) {
            return new ArrayList<>();
        }
        int[] dp = new int[n + 1];
        int[] next = new int[n + 1];
        Arrays.fill(dp, -1);
        Arrays.fill(next, -1);
        dp[n] = 0;
        next[n] = n;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(n);
        for (int i = n - 1; i >= 1; i--) {

            // i+1...i+b in the dq
            if (!dq.isEmpty() && dq.peekLast() > i + b) {
                dq.pollLast();
            }
            if (a[i - 1] == -1) {
                continue;
            }
            if (!dq.isEmpty()) {
                int minindex = dq.peekLast();
                dp[i] = dp[minindex] + a[i - 1];
                next[i] = minindex;
            }
            if (dp[i] != -1) {
                while (!dq.isEmpty() && dp[dq.peekFirst()] >= dp[i]) {
                    dq.pollFirst();
                }
                dq.offerFirst(i);
            }
        }
        if (next[1] < 0) {
            return new ArrayList<>();
        }
        List<Integer> r = dfs(next, 1, n);
        Collections.reverse(r);
        return r;
    }

    List<Integer> dfs(int[] p, int i, int n) {
        if (i == n) {
            List<Integer> r = new ArrayList<>();
            r.add(i);
            return r;
        }

        List<Integer> l = dfs(p, p[i], n);
        l.add(i);
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new CoinPath().cheapestJump(ArrayUtils.read1d("[21,7,96,68,73,99,19,89,0,62,86,8,6,62,49,77,47,12,86,5,46,29,3,41,68,50,83,41,77,29,10,91,75,23,59,36,8,26,26,88,-1,41,45,32,3,51,83,75,12,48,99,38,21,98,83,46,42,48,64,92,70,6,96,30,65,7,90,95,5,97,25,7,99,2,26,42,38,95,26,11,86,24,16,87,77,58,30,31]"), 1));
    }
}

class CoinPathCleanerDp {
    // no fancy queue
    int[] dp;
    int[] choices;

    public List<Integer> cheapestJump(int[] a, int b) {
        int n = a.length;
        dp = new int[n];

        Arrays.fill(dp, -2);

        choices = new int[n];
        Arrays.fill(choices, -2);
        int r = doc(a, 0, b);
        if (r >= Max) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        solve(0, n, res);
        return res;
    }

    private void solve(int i, int n, List<Integer> res) {
        res.add(i + 1);
        if (i == n - 1) {
            return;
        } else {
            int j = choices[i];
            solve(j, n, res);
        }
    }

    int Max = 100000000;

    private int doc(int[] a, int i, int b) {
        int n = a.length;
        if (i == n - 1) {
            if (a[i] == -1) {
                dp[i] = Max;
            } else {
                dp[i] = a[i];
            }
            return dp[i];
        }
        if (dp[i] != -2) {
            return dp[i];
        }
        int min = Max;
        int minj = -1;
        for (int j = i + 1; j <= i + b && j < n; j++) {
            if (a[j] != -1) {
                int cur = doc(a, j, b);
                if (a[i] + cur < min) {
                    min = a[i] + cur;
                    minj = j;
                }
            }
        }
        dp[i] = min;
        choices[i] = minj;
        return min;
    }
}
