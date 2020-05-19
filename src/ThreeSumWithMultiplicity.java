import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#923
Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.



Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.


Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300
 */
public class ThreeSumWithMultiplicity {
    // O(n+size*size)  in this one possible range of numbers is small
    // idea is to loop numbers themselves
    int[] cnt;

    public int threeSumMulti(int[] a, int t) {
        int size = 101;
        cnt = new int[size];
        for (int ai : a) {
            cnt[ai]++;
        }
        int n = a.length;
        long r = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                // in case target too large
                int k = t - i - j;
                // must enforce an order: i<=j<=k. otherwise we will double count the pairs
                if (k < 0 || k >= size || k < j) {
                    continue;
                }
                if (i == j && i == k) {
                    r += pick3(i);
                } else if (i == j) {
                    r += pick2(i) * cnt[k];
                } else if (j == k) {
                    r += pick2(j) * cnt[i];
                } else {
                    r += cnt[i] * cnt[j] * cnt[k];
                }
            }
        }
        return (int) (r % 1000000007);
    }

    long pick3(int i) {
        long ci = cnt[i];
        if (ci < 3) {
            return 0;
        }
        return ci * (ci - 1L) * (ci - 2) / 6;
    }

    long pick2(int i) {
        long ci = cnt[i];
        if (ci < 2) {
            return 0;
        }
        return ci * (ci - 1L) / 2;
    }
}

class ThreeSumWithMultiSol2 {
    // like traditional 3 sum but a twist on counting. note it's just counting triples not reporting their position so we can sort

    private int MOD = 1000000007;

    public int threeSumMulti(int[] a, int target) {
        Arrays.sort(a);
        long r = 0;
        for (int i = 0; i < a.length; i++) {
            int ti = target - a[i];
            int j = i + 1;
            int k = a.length - 1;
            while (j < k) {
                int sumjk = a[j] + a[k];
                if (sumjk > ti) {
                    k--;
                } else if (sumjk < ti) {
                    j++;

                } else {
                    // note this is different from 3sum smaller when == this is no longer sizej*sizek
                    if (a[j] == a[k]) {
                        int gap = k - j + 1;
                        int delta = gap * (gap - 1) / 2;
                        r += delta;
                        r %= MOD;
                        break;
                    }
                    int oj = j;
                    while (a[j] == a[oj]) {
                        j++;
                    }
                    int ok = k;
                    // here j can == k because the j earlier is not sth we would land on
                    while (a[k] == a[ok]) {
                        k--;
                    }
                    int delta = (j - oj) * (ok - k);
                    r += delta;
                    r %= MOD;
                }
            }
        }
        return (int) r;
    }
}

class ThreeSumWithMultiSol3 {
    // fix j, loop i and count how many ks we have. note in 3 index problem we usually fix middle and loop the two sides

    long MOD = 1000000007;

    public int threeSumMulti(int[] a, int t) {

        Arrays.sort(a);
        int n = a.length;
        long r = 0;
        int[] m = new int[101];
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                int ti = t - a[j] - a[k];
                if (ti >= 0 && ti <= 100) {
                    r += m[ti];
                    r %= MOD;
                }
            }
            m[a[j]]++;
        }
        return (int) r;
    }
}