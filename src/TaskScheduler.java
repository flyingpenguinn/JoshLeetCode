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
    public int leastInterval(char[] s, int k) {
        k++;// segment length = k+1
        int[][] count = new int[26][2];
        int n = s.length;
        for (int i = 0; i < n; i++) {
            int cind = s[i] - 'A';
            count[cind][0]++;
            count[cind][1] = cind;
        }
        int res = 0;
        while (true) {
            Arrays.sort(count, (x, y) -> Integer.compare(y[0], x[0]));
            int put = 0;
            for (int i = 0; i < 26 && count[i][0] != 0 && put < k; i++) {
                count[i][0]--;
                put++;
            }
            if (count[0][0] > 0) {
                // last segment won't need k
                res += k - put;
            } else {
                break;
            }
        }
        return res + n;
    }
}
