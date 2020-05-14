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
    public List<Integer> topKFrequent(int[] a, int k) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        Map<Integer, Integer> cm = new HashMap<>();
        int max = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int nv = cm.getOrDefault(a[i], 0) + 1;
            cm.put(a[i], nv);
            max = Math.max(max, nv);
        }
        // can just do this after the first traverse....
        for (int i = 0; i < n; i++) {
            int count = cm.get(a[i]);
            List<Integer> cur = m.get(count);
            if (cur == null) {
                cur = new ArrayList<>();
                m.put(count, cur);
            }
            cur.add(a[i]);
        }
        int i = max;
        int rem = k;
        List<Integer> r = new ArrayList<>();
        while (rem > 0) {
            Iterator<Integer> it = m.getOrDefault(i, new ArrayList<>()).iterator();
            while (it.hasNext() && rem > 0) { // must add && rem>0
                r.add(it.next());
                rem--;
            }
            i--;
        }
        return r;
    }
}

class TopKFrequentPq {
    // similar to top k biggest
    public List<Integer> topKFrequent(int[] a, int k) {
        final Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        // min queue for top k biggest
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(m.get(x), m.get(y)));
        for (int ai : m.keySet()) {
            pq.offer(ai);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> r = new ArrayList<>();
        while (!pq.isEmpty()) {
            r.add(pq.poll());
        }
        Collections.reverse(r);
        return r;
    }
}
