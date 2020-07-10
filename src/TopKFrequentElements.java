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
    class Solution {
        public int[] topKFrequent(int[] a, int k) {
            // all valid, k elements unique
            Map<Integer, Integer> f = new HashMap<>();
            for (int i = 0; i < a.length; i++) {
                f.put(a[i], f.getOrDefault(a[i], 0) + 1);
            }
            Map<Integer, Set<Integer>> f2num = new HashMap<>();
            int maxFreq = 0;
            for (int num : f.keySet()) {
                int freq = f.get(num);
                f2num.computeIfAbsent(freq, key -> new HashSet<>()).add(num);
                maxFreq = Math.max(maxFreq, freq);
            }
            List<Integer> r = new ArrayList<>();
            while (r.size() < k) {
                Set<Integer> nums = f2num.get(maxFreq);
                if (nums != null) {
                    r.addAll(nums);
                }
                maxFreq--;
            }
            int[] res = new int[r.size()];
            for (int i = 0; i < r.size(); i++) {
                res[i] = r.get(i);
            }
            return res;
        }
    }
}