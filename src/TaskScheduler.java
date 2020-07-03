import java.util.*;

/*
LC#621
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    // from high to low freq. arrange up to == n+1 then resort and repeat
    // run high freq as much as possible
    // similar to rearrange string k distance apart
    /*
    traps:
    1. last segment can be a stub
    2. num++: must be num apart, means segment is num+1 in length

     */
    public int leastInterval(char[] a, int num) {
        num++; // ++ first: the segments are of num+1 in size
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((x, y) -> Integer.compare(m.get(y), m.get(x)));
        for (char k : m.keySet()) {
            pq.offer(k);
        }
        int r = 0;
        while (!m.isEmpty()) {
            List<Character> polled = new ArrayList<>();
            int count = 0;
            while (!pq.isEmpty() && count < num) {
                Character c = pq.poll();
                count++;
                r++;
                int ncount = m.get(c) - 1;
                if (ncount != 0) {
                    m.put(c, ncount);
                    polled.add(c);
                } else {
                    m.remove(c);  // not putting 0 counts back
                }
            }
            if (!m.isEmpty() && count < num) {
                r += (num - count);   // mind the final segment that may not be as long as the whole segment
            }
            for (char c : polled) {
                pq.offer(c);
            }
        }
        return r;
    }
}
