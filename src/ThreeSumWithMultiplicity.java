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

    private long MOD = 1000000007;

    public int threeSumMulti(int[] a, int target) {
        int maxv = 100;
        int[] count = new int[maxv + 1];
        for (int i = 0; i < a.length; i++) {
            count[a[i]]++;
        }
        long res = 0;
        for (int i = 0; i <= maxv; i++) {
            if (count[i] == 0) {
                continue;
            }
            for (int j = i; j <= maxv; j++) {
                if (count[j] == 0) {
                    continue;
                }
                int k = target - i - j;
                if (k < j || k > maxv || count[k] == 0) {
                    // must be within j and maxv just like j is within i and maxv
                    continue;
                }
                // the values i<=j<=k
                if (i == j && j == k) {
                    res += 1L * count[i] * (count[i] - 1) * (count[i] - 2) / 6; // cn3
                } else if (i == j) {
                    res += 1L * count[k] * count[i] * (count[i] - 1) / 2;// cn2
                } else if (j == k) {
                    res += 1L * count[i] * count[j] * (count[j] - 1) / 2;//cn2
                } else {
                    res += 1L * count[i] * count[j] * count[k];
                }
                res %= MOD;
            }
        }
        return (int) res;
    }
}

class ThreeSumWithMultiSol2 {
    // like traditional 3 sum but a twist on counting.
    // note it's just counting triples not reporting their position so we can sort
    // also, note when ==, we dont really know how to move if we need to count the occurrences
    long MOD = 1000000007;

    public int threeSumMulti(int[] a, int t) {
        Arrays.sort(a);
        int n = a.length;
        int i = 0;
        long res = 0;
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = a[i] + a[j] + a[k];
                if (sum == t) {
                    if (a[j] == a[k]) {
                        int count = k - j + 1;
                        res += count * (count - 1) / 2;
                        res %= MOD;
                        break;
                    } else {
                        int m = j;
                        while (j < k && a[m] == a[j]) {
                            j++;
                        }
                        // j is at the pos where it doesnt equal old aj(am)
                        int countj = j - m;
                        m = k;
                        while (k >= j && a[k] == a[m]) {
                            k--;
                        }
                        // k>=j because j is at the counted pos +1. k can still step on it
                        int countk = m - k;
                        res += countj * countk;
                        res %= MOD;
                    }
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