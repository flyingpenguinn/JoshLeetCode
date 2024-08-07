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
    public int leastInterval(char[] a, int k) {
        ++k;
        int[] m = new int[26];
        int n = a.length;
        for (char ai : a) {
            ++m[ai - 'A'];
        }
        int rem = n;
        int res = 0;
        while (rem > 0) {
            Arrays.sort(m);
            int remk = k;
            for (int i = 25; i >= 0 && m[i] > 0 && remk > 0; --i) {
                --m[i];
                --rem;
                --remk;
            }
            if (rem > 0) {
                // if we have done, dont need to add remk idle rounds
                res += remk;
            }
        }
        return res + n;
    }
}
