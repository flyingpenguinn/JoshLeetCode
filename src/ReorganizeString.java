import java.util.*;

/*
LC#767
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    // almost same as task scheduler, interval is 1 here while in that problem it's k
    public String reorganizeString(String s) {
        int n = s.length();
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>((x, y) -> Integer.compare(m.get(y), m.get(x)));  // big freq first
        for (char ck : m.keySet()) {
            pq.offer(ck);
        }
        char pre = '*';
        while (sb.length() < n) {
            char top = pq.poll();
            if (top == pre) {
                if (pq.isEmpty()) {
                    return "";
                }
                char top2 = pq.poll();
                pq.offer(top);
                top = top2;
            }
            pre = top;
            sb.append(top);
            int nv = m.get(top) - 1;
            if (nv == 0) {
                m.remove(top);
            } else {
                m.put(top, nv);
                pq.offer(top);
            }
        }
        return sb.toString();
    }
}