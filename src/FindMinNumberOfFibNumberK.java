import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#1414
Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.

The Fibonacci numbers are defined as:

F1 = 1
F2 = 1
Fn = Fn-1 + Fn-2 , for n > 2.
It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.


Example 1:

Input: k = 7
Output: 2
Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
For k = 7 we can use 2 + 5 = 7.
Example 2:

Input: k = 10
Output: 2
Explanation: For k = 10 we can use 2 + 8 = 10.
Example 3:

Input: k = 19
Output: 3
Explanation: For k = 19 we can use 1 + 5 + 13 = 19.


Constraints:

1 <= k <= 10^9
 */
public class FindMinNumberOfFibNumberK {
    // 1. we must take non duplicate ones because 2fn = fn+1+fn-1
    // 2. we must not take consecutive ones, because fn = fn-1+fn-1
    // 3. so if we dont have fn, then fn-1, fn-3...... adds to to smaller than fn. actually they add up to fn-1
    List<Integer> fibs = new ArrayList<>();

    public int findMinFibonacciNumbers(int k) {
        int t1 = 1;
        int t2 = 1;
        fibs.add(t1);
        fibs.add(t2);
        while (t1 + t2 <= k) {
            fibs.add(t1 + t2);
            int oldt2 = t2;
            t2 = t1 + t2;
            t1 = oldt2;
        }
        return dof(k);
    }

    private int dof(int k) {
        int pos = Collections.binarySearch(fibs, k);
        if (pos >= 0) {
            return 1;
        }
        pos = -(pos + 1) - 1;
        return 1 + dof(k - fibs.get(pos));
    }
}
