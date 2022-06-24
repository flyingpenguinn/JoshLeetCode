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
        int n = a.length;
        if(n==1){
            return a[0]==1;
        }
        long sum = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            sum += a[i];
            pq.offer(Long.valueOf(a[i]));
        }
        while (!pq.isEmpty() && sum > n) {
            long top = pq.poll();
            long other = sum - top;
            if (top <= other ) {
                // if top <= other we had no way to come to this top
                return false;
            }
            // use % to get the remnant. for example 3 vs 19 we can get 19%3=1 in the end
            long orig = top % other;
            if (orig == 0) {
                // if it's 3 vs 9 we know we can get to 3
                orig = other;
            }
            pq.offer(orig);
            sum -= (top - orig);
        }
        return sum == n;
    }

    public static void main(String[] args) {
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[2,900000002]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[8,5]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1,2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1,1,2]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[1, 1000000000]")));
        System.out.println(new ConstructTargetArrayMultipleSums().isPossible(ArrayUtils.read1d("[5, 50]")));
    }
}
