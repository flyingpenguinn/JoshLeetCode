import base.ArrayUtils;

import java.util.Collections;
import java.util.PriorityQueue;

/*
LC#1354
Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
You may repeat this procedure as many times as needed.
Return True if it is possible to construct the target array from A otherwise return False.



Example 1:

Input: target = [9,3,5]
Output: true
Explanation: Start with [1, 1, 1]
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
Example 2:

Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].
Example 3:

Input: target = [8,5]
Output: true


Constraints:

N == target.length
1 <= target.length <= 5 * 10^4
1 <= target[i] <= 10^9
 */
public class ConstructTargetArrayMultipleSums {
    // the last operation must be on the max number. so use a pq to trace it
    public boolean isPossible(int[] a) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0L;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (a[i] != 1) {
                pq.offer(Long.valueOf(a[i]));
            }
        }
        while (!pq.isEmpty()) {
            long max = pq.poll();
            long other = sum - max;
            if (max <= other || other == 0) {
                // other == 0 will give us the same max can't reduce
                return false;
            }
            if (other == 1) {
                // 1 vs xx, must be true
                return true;
            }
            long nmax = max % other;
            // use % to accelerate! for example 1000 vs 3, we know it yields 1 in the end by %
            if (nmax == 0) {
                // 2 vs 2, can't get to 1
                return false;
            }
            if (nmax != 1) {
                pq.offer(nmax);
            }
            sum -= (max - nmax);
        }
        return sum == a.length;
    }

    public static void main(String[] args) {

        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[8,5]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1,2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1,1,2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1, 1000000000]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[5, 50]")));
    }
}
