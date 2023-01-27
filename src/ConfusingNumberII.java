import java.util.*;

/*
LC#1088
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.



Example 1:

Input: 20
Output: 6
Explanation:
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation:
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].


Note:

1 <= N <= 10^9
 */
public class ConfusingNumberII {
    // O(5^9), note we should only use int to avoid expensive string operations
    // note this is different from strobo number because here we only have 9 digits so can just dfs ALL possibilities and find out good ones
    // strobo is O(5^n/2). here if we do all-strobo, we still need to traverse all, not better than current solution
    public int confusingNumberII(int n) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        dfs(0, String.valueOf(n).length(), 0, n);
        return res;
    }


    private Map<Integer, Integer> map = new HashMap<>();
    private int res = 0;


    private void dfs(int i, int len, long cur, long n) {
        if (cur > n) {
            return;
        }
        if (!strobo(cur)) {
            //System.out.println(cur);
            res++;
        }
        if (i == len) {
            return;
        }
        for (int k : map.keySet()) {
            if (i == 0 && k == 0 ) {
                continue;
            }
            dfs(i + 1, len, cur * 10 + k, n);
        }
    }

    private boolean strobo(long n) {
        long reverse = 0;
        long oldn = n;
        // 168=> 891
        while (n > 0) {
            int mod = (int) n % 10;
            reverse = reverse * 10 + map.get(mod);
            n /= 10;
        }
        return reverse == oldn;
    }
}