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
    public int leastInterval(char[] ts, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ts.length; i++) {
            map.put(ts[i], map.getOrDefault(ts[i], 0) + 1);
        }
        // must keep the sorting stable
        PriorityQueue<Character> pq = new PriorityQueue<>((x, y) -> !map.get(y).equals(map.get(x)) ?
                Integer.compare(map.get(y), map.get(x)) : Character.compare(x, y));
        for (char ck : map.keySet()) {
            pq.offer(ck);
        }
        // most frequent at the top
        int r = 0;
        int added = 0;
        while (added < ts.length) {
            int ingroup = 0;
            List<Character> polled = new ArrayList<>();
            while (ingroup < k + 1 && !pq.isEmpty()) {
                // n apart, so a group is of length n+1
                char c = pq.poll();
                r++;
                added++;
                ingroup++;
                int nv = map.get(c) - 1;
                if (nv == 0) {
                    map.remove(c);
                } else {
                    map.put(c, nv);
                    polled.add(c);
                }
            }
            if (ingroup < k + 1 && !polled.isEmpty()) {
                int diff = k + 1 - ingroup;
                r += diff;
            }
            for (int i = 0; i < polled.size(); i++) {
                pq.offer(polled.get(i));
            }
        }
        return r;
    }
}
