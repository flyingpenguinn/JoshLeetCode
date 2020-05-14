import java.util.*;

import java.util.Arrays;
import java.util.Collections;

import static base.ArrayUtils.read1d;

/*
LC#502
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.

Example 1:
Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].

Output: 4

Explanation: Since your initial capital is 0, you can only start the project indexed 0.
             After finishing it you will obtain profit 1 and your capital becomes 1.
             With capital 1, you can either start the project indexed 1 or the project indexed 2.
             Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
             Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Note:
You may assume all numbers in the input are non-negative integers.
The length of Profits array and Capital array will not exceed 50,000.
The answer is guaranteed to fit in a 32-bit signed integer.
 */
public class IPO {
    // loosening algo with heap: every time we make a choice, the list of avail increases. we choose on profit, but allows based on capital
    public int findMaximizedCapital(int k, int w, int[] p, int[] c) {
        int n = p.length;
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; i++) {
            ps[i][0] = p[i];
            ps[i][1] = c[i];
        }
        // by c first as we will add it
        Arrays.sort(ps, (x, y) -> Integer.compare(x[1], y[1]));
        // but we pq on profit so that we get the best profit out of qualifying cs every time
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        int i = 0;
        while (k > 0) {
            while (i < n && ps[i][1] <= w) {
                pq.offer(ps[i++]);
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] top = pq.poll();
            w += top[0];
            k--;
        }
        return w;
    }

    public static void main(String[] args) {
        System.out.println(new IPO().findMaximizedCapital(2, 0, read1d("[1,2,3]"), read1d("[0,1,1]")));
    }
}
