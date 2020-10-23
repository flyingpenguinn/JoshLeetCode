import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

/*
LC#456
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class OneThreeTwoPattern {
    // treeset based, Onlgn solution. for each number find the biggest smaller after it
    public boolean find132pattern(int[] a) {
        int n = a.length;
        if (n < 3) {
            return false;
        }
        int[] right = new int[n];
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = n - 1; i >= 0; i--) {
            Integer lower = ts.lower(a[i]);
            right[i] = lower == null ? Integer.MAX_VALUE : lower;
            ts.add(a[i]);
        }
        int min = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > min && min < right[i] && a[i] > right[i]) {
                return true;
            }
            min = Math.min(min, a[i]);
        }
        return false;
    }
}

class OneThreeTwoOn {
    public boolean find132pattern(int[] a) {
        // keep j and i candidates in stack. i is the min before j
        Deque<int[]> st = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > min) {
                while (!st.isEmpty() && st.peek()[0] <= a[i]) {
                    // smaller j and no smaller i (min) for sure, not good candidate
                    st.pop();
                }
                // each a[i] is k candidate and stack top has a good j check if it has a good i too
                if (!st.isEmpty() && st.peek()[1] < a[i]) {
                    return true;
                }
                st.push(new int[]{a[i], min});
            }
            min = Math.min(min, a[i]);
        }
        return false;
    }
}
