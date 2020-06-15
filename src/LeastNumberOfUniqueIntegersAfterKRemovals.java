import java.util.*;

/*
LC#
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.



Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    // greedy. we start from the least frequent, then to most frequent. we stop when we can't take more numbers with k removals
    public int findLeastNumOfUniqueInts(int[] a, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            freq.put(a[i], freq.getOrDefault(a[i], 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(freq.get(x), freq.get(y)));
        for (int key : freq.keySet()) {
            pq.offer(key);
        }
        int r = freq.keySet().size();
        while (!pq.isEmpty()) {
            int diff = freq.get(pq.poll());
            if (k >= diff) {
                r--;
                k -= diff;
            } else {
                break;
            }
        }
        return r;
    }
}
