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
    // all valid - strobo = confusing numbers
    // all valid ~ 2^9*5, strobo is 5^(9/2)*5 because it only calculates half of the length
    private int[] m = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    // the strobo part is the same as strobo number III
    public int strobos(long n) {
        int len = String.valueOf(n).length();
        long res = 0;
        for (int l = 1; l <= len; l++) {
            res += dfs(0, l, n, "", "");
        }
        return (int) res;
    }

    private long dfs(int i, int len, long n, String p1, String p2) {
        int start = i > 0 || len == 1 ? 0 : 1;
        long res = 0;
        if (i == len / 2) {
            if (len % 2 == 0) {
                String str = p1 + p2;
                if (Long.valueOf(str) <= n) {
                    //     System.out.println(str);
                    res++;
                }
            } else {

                for (int j = start; j < 10; j++) {
                    if (m[j] == j) {
                        String str = p1 + j + p2;
                        if (Long.valueOf(str) <= n) {
                            //     System.out.println(str);
                            res++;
                        }
                    }
                }
            }
            return res;
        }
        for (int j = start; j < 10; j++) {
            if (m[j] != -1) {
                res += dfs(i + 1, len, n, p1 + j, m[j] + p2);
            }
        }
        return res;
    }

    // calculate all valid numbers
    private int valids(int n) {
        int len = String.valueOf(n).length();
        long res = 0;
        for (int l = 1; l < len; l++) {
            int cur = quickvalid(l, l == 1);
            //   System.out.println(cur);
            res += cur;
        }
        res += findvalid(0, len, false, n);
        return (int) res;
    }

    private int quickvalid(int l, boolean single) {
        if (l == 1) {
            return single ? 5 : 4;
        }
        return 5 * quickvalid(l - 1, false);
    }

    // can potentially dp to make it even faster
    private int findvalid(int i, int l, boolean sm, int n) {
        if (i == l) {
            return 1;
        }
        if (sm) {
            return 5 * findvalid(i + 1, l, true, n);
        }
        int start = i > 0 || l == 1 ? 0 : 1;
        int nd = String.valueOf(n).charAt(i) - '0';
        int res = 0;
        int small = 0;
        int eq = 0;
        for (int j = start; j <= nd; j++) {
            if (m[j] == -1) {
                continue;
            }
            if (j < nd) {
                small++;
            } else {
                eq++;
            }
        }

        int cursmall = small * findvalid(i + 1, l, true, n);
        //   System.out.println("cursmall = "+cursmall);
        res += cursmall;
        res += eq == 0 ? 0 : findvalid(i + 1, l, false, n);
        return res;
    }

    public int confusingNumberII(int n) {
        return valids(n) - strobos(n);
    }


    public static void main(String[] args) {
        System.out.println(new ConfusingNumberII().confusingNumberII(20));
        System.out.println(new ConfusingNumberII().confusingNumberII(999999999));

        System.out.println(new ConfusingNumberII().confusingNumberII(1000000000));

    }
}

class ConfusingNumberIIDirectDfs {
    // O(5^9), note we should only use int to avoid expensive string operations
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
            if (i == 0 && k == 0 && len > 1) {
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