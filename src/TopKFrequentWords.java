import java.util.*;

/*
LC#692
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {
    // define small for heap
    PriorityQueue<String> pq = new PriorityQueue<>((x, y) -> f(x) != f(y) ? Integer.compare(f(x), f(y)) : y.compareTo(x));

    Map<String, Integer> m = new HashMap<>();

    public List<String> topKFrequent(String[] a, int k) {
        for (String s : a) {
            m.put(s, f(s) + 1);
        }
        for (String key : m.keySet()) {
            pq.offer(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> r = new ArrayList<>();
        while (!pq.isEmpty()) {
            r.add(pq.poll());
        }
        Collections.reverse(r);
        return r;
    }

    int f(String s) {
        return m.getOrDefault(s, 0);
    }
}
