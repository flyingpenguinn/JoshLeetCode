import java.util.*;

/*
LC#358
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc"
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: ""
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart {
    // almost the same as task scheduler and similar idea to distant barcode. we need to know the char count and arrange the mos frequent first
    // note pay attention to the last group it may not be of length k
    public String rearrangeString(String s, int k) {
        if (k <= 1) {
            return s;
        }
        Map<Character, Integer> m = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0) + 1);
        }
        // bigger first. note we MUST make the sorting stable when two chars are of the same count: aabbcc if it can be unstable then it may become acbabc
        PriorityQueue<Character> pq = new PriorityQueue<>((x, y) -> !m.get(y).equals(m.get(x)) ? Integer.compare(m.get(y), m.get(x)) : Character.compare(x, y));
        for (char ck : m.keySet()) {
            pq.offer(ck);
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            int group = 0;
            List<Character> polled = new ArrayList<>();
            while (group < k && !pq.isEmpty()) {
                char c = pq.poll();
                sb.append(c);
                group++;
                int ncount = m.get(c) - 1;
                if (ncount == 0) {
                    m.remove(c);
                } else {
                    m.put(c, ncount);
                    polled.add(c);
                }
            }
            if (group < k && !polled.isEmpty()) {
                // pq already empty but we have more to work on. note if we dont have things to work on we are good the last group can be <k
                return "";
            }
            for (int i = 0; i < polled.size(); i++) {
                pq.offer(polled.get(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
