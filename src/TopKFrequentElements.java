import java.util.*;

/*
LC#347
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    // the loop in the end is On
    public int[] topKFrequent(int[] a, int k) {
        if (a == null || a.length == 0 || k <= 0 || k > a.length) {
            return new int[0];
        }
        // assuming no tie/ambiguity for top k
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        Map<Integer, Set<Integer>> fm = new HashMap<>();
        int maxFreq = 0;
        for (int key : m.keySet()) {
            int freq = m.get(key);
            maxFreq = Math.max(maxFreq, freq);
            fm.computeIfAbsent(freq, fk -> new HashSet<>()).add(key);
        }
        int[] r = new int[k];
        int ri = 0;
        for (int i = maxFreq; i >= 1 && ri < k; i--) {
            Set<Integer> nums = fm.getOrDefault(i, new HashSet<>());
            for (int num : nums) {
                if (ri >= k) {
                    break;  // shouldnt be here as there is no ambiguity
                }
                r[ri++] = num;
            }
        }
        return r;
    }
}