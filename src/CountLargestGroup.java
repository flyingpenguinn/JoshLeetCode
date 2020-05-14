/*
LC#1399
Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.

Return how many groups have the largest size.



Example 1:

Input: n = 13
Output: 4
Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.
Example 2:

Input: n = 2
Output: 2
Explanation: There are 2 groups [1], [2] of size 1.
Example 3:

Input: n = 15
Output: 6
Example 4:

Input: n = 24
Output: 5


Constraints:

1 <= n <= 10^4
 */

import java.util.HashMap;
import java.util.Map;

public class CountLargestGroup {
    public int countLargestGroup(int n) {
        Map<Integer, Integer> m = new HashMap<>();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int sum = sum(i);
            int nv = m.getOrDefault(sum, 0) + 1;
            m.put(sum, nv);
            max = Math.max(max, nv);
        }
        //System.out.println(m);
        int r = 0;
        for (int k : m.keySet()) {
            if (m.get(k) == max) {
                r++;
            }
        }
        return r;
    }

    private int sum(int n) {
        int r = 0;
        while (n > 0) {
            r += n % 10;
            n /= 10;
        }
        return r;
    }
}
