import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/*
LC#798
 Given an array A, we may rotate it by a non-negative integer K so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ..., A[K-1].  Afterward, any entries that are less than or equal to their index are worth 1 point.

For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it becomes [1, 3, 0, 2, 4].  This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].

Over all possible rotations, return the rotation index K that corresponds to the highest score we could receive.  If there are multiple answers, return the smallest such index K.

Example 1:
Input: [2, 3, 1, 4, 0]
Output: 3
Explanation:
Scores for each K are listed below:
K = 0,  A = [2,3,1,4,0],    score 2
K = 1,  A = [3,1,4,0,2],    score 3
K = 2,  A = [1,4,0,2,3],    score 3
K = 3,  A = [4,0,2,3,1],    score 4
K = 4,  A = [0,2,3,1,4],    score 3
So we should choose K = 3, which has the highest score.



Example 2:
Input: [1, 3, 0, 2, 4]
Output: 0
Explanation:  A will always have 3 points no matter how it shifts.
So we will choose the smallest K, which is 0.
Note:

A will have length at most 20000.
A[i] will be in the range [0, A.length].
 */
public class SmallestRotationHighestScore {
    // because when we move left all number's score-1 except for the head one. we can think of as score is increasing...
    // we compensate the head by adding round number to its diff so that it can "live" longer
    public int bestRotation(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int diff = i - a[i];
            m.put(diff, m.getOrDefault(diff, 0) + 1);
        }
        int base = 0;
        for (int k : m.keySet()) {
            if (k >= 0) {
                base += m.get(k);
            }
        }
        // at first we just need to be >=0
        int maxbase = base;
        int maxi = 0;
        for (int i = 1; i < n; i++) { // n-1 rounds
            // because now the water level is increasing and i-1 counts as good
            int minus = m.getOrDefault(i - 1, 0);
            base = base - minus + ((n - 1 + i) >= a[i - 1] ? 1 : 0); // with this we can handle a[i] at any range
            if (base > maxbase) {
                maxbase = base;
                maxi = i;
            }
            int ndiff = n - 1 - a[i - 1] + i;  //+i to make it survive rest of the rounds.
            m.put(ndiff, m.getOrDefault(ndiff, 0) + 1);
        }
        return maxi;
    }

    public static void main(String[] args) {
        System.out.println(new SmallestRotationHighestScore().bestRotation(ArrayUtils.read1d("[2, 3, 1, 4, 0]")));
    }
}
