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
    // 26log(26)*number of tasks: we have to output all of them
    public int leastInterval(char[] ts, int n) {
        int[] count = new int[26];
        int all = 0;
        for (char c : ts) {
            count[c - 'A']++;
            all++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(count[y], count[x]));
        // most frequent at the top
        int r = 0;
        int oldn = n;
        while (all > 0) {
            n = oldn;
            pq.clear();
            // 26log26 almost like a constant...
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    pq.offer(i);
                }
            }
            while (!pq.isEmpty() && n >= 0) {
                int c = pq.poll();
                count[c]--;
                n--;
                all--;
                r++;
            }
            if (n >= 0 && all > 0) {
                r += n + 1;
            }
        }
        return r;
    }
}
