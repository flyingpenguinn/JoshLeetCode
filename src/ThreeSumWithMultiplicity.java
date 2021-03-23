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
// x sum usually has 3 ways: hashmap + pre counting, sum counting check pairs, and sort and count. below is sum counting
    public int threeSumMulti(int[] a, int t) {
        int n = a.length;
        long[] c = new long[101];
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }
        long res = 0;
        int mod = 1000000007;
        for (int i = 0; i < 100; i++) {
            int j = i; // from i, because in sum counting it's ok to have same number so that i+i = t
            int k = 100;
            while (j <= k) { // note j can == k when we pick sums
                int sum = i + j + k;
                if (sum < t) {
                    j++;
                } else if (sum > t) {
                    k--;
                } else {
                    if (i == j && j == k) {
                        res += c[i] * (c[i] - 1) * (c[i] - 2) / 6;
                    } else if (i == j) {
                        res += c[k] * c[i] * (c[i] - 1) / 2;
                    } else if (j == k) {
                        res += c[i] * c[j] * (c[j] - 1) / 2;
                    } else if (i == k) {
                        res += c[j] * c[i] * (c[i] - 1) / 2;
                    } else {
                        res += c[i] * c[j] * c[k];
                    }
                    j++;
                    k--;
                    res %= mod;
                }
            }
        }
        return (int) res;
    }
}

class ThreeSumWithMultiSol2 {
    // sort and count: like traditional 3 sum but a twist on counting.
    // note it's just counting triples not reporting their position so we can sort
    // also, note when ==, we dont really know how to move if we need to count the occurrences
    private int Mod = 1000000007;

    public int threeSumMulti(int[] a, int t) {
        Arrays.sort(a);
        int n = a.length;
        int i = 0;
        long res = 0;
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = a[j] + a[k] + a[i];
                if (sum == t) {
                    if (a[j] == a[k]) {
                        int cjk = k - j + 1;
                        res += cjk * (cjk - 1) / 2;
                        res %= Mod;
                        break;
                    }
                    int oj = j;
                    while (j < k && a[j] == a[oj]) {
                        j++;
                    }
                    // oj to j-1
                    int ok = k;
                    while (k >= j && a[k] == a[ok]) {
                        k--;
                    }
                    // ok to k+1
                    res += (j - oj) * (ok - k);
                    res %= Mod;
                } else if (sum < t) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return (int) res;
    }
}

class ThreeSumWithMultiSol3 {
    // fix j, loop i and count how many ks we have. note in 3 index problem we usually fix middle and loop the two sides
    // we can think of this as a tuned hash + pre solution
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