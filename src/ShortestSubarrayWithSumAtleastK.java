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
 if ai1>ai2, we have no reason to keep i1. so mono increase data structure. note we keep is in the queue and scan j
 fix i, for js, if both aj1 and aj2 can win, then we want j1. so scan from the front
 comparison: we compare with the front of the data structure and remove it if it's already good. so add at tail, get from front, it's a queue

 if shortest <=k, mono decrease queue
 if longest >=k, mono decrease queue and we scan from the back and compare with the tail of the queue
 if longest <=k, mono increase but scan from the back
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

class ShortestSubarrayWithSumAtleastKBinarySearch {
    // similar to longest well performing interval
    public int shortestSubarray(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        int min = n + 1;
        List<Integer> l = new ArrayList<>();
        List<Integer> inds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (sum >= k) {
                min = Math.min(min, i + 1);
            }
            int index = binarySearchLastNoBigger(l, sum - k);
            if (index != -1) {
                int len = i - inds.get(index);
                min = Math.min(min, len);
            }
            // as candidate i, if big and appear early, no reason to keep it. so l is increasing
            while (!l.isEmpty() && l.get(l.size() - 1) >= sum) {
                l.remove(l.size() - 1);
                inds.remove(inds.size() - 1);
            }
            l.add(sum);
            inds.add(i);
        }
        return min > n ? -1 : min;
    }

    private int binarySearchLastNoBigger(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
