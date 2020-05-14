import java.util.*;

/*
LC#862
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.



Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3


Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */
public class ShortestSubarrayWithSumAtleastK {
/*
 if all numbers are non negative, it's LC#209 we can use plain two pointer
 keep an increasing mono queue.
 as for the front, when value j meets requirement it's of no use since later i-j will be better

 reasoning: for i...j
 if ai1>ai2, we have no reason to keep i1. so mono increase data structure
 fix i, for js, if both aj1 and aj2 can win, then we want j1. so scan from the front
 comparison: we compare with the front of the data structure and remove it if it's already good. so add at tail, get from front, it's a queue

 if shortest <=k, mono decrease queue
 */

    public int shortestSubarray(int[] a, int k) {
        int n = a.length;
        int[] sum = new int[n];
        int min = n + 1;
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
            if (sum[i] >= k) {
                //  System.out.println(i);
                min = Math.min(min, i + 1);
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && sum[q.peekLast()] >= sum[i]) {
                // bigger earlier ones at tail no better than i
                q.pollLast();
            }
            while (!q.isEmpty() && sum[i] - sum[q.peekFirst()] >= k) {
                // those at head wont do better with bigger i
                int j = q.pollFirst();
                //  System.out.println(i+" " +j);
                // j+1 to i
                min = Math.min(min, i - j);
            }
            q.offerLast(i);
        }
        return min >= n + 1 ? -1 : min;
    }


    public static void main(String[] args) {
        ShortestSubarrayWithSumAtleastK ssw = new ShortestSubarrayWithSumAtleastK();
        int[] array = {2, -1, 2};
        System.out.println(ssw.shortestSubarray(array, 3));
    }
}
