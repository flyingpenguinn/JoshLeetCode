/*
LC#754
You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.
Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.
Note:
target will be a non-zero integer in the range [-10^9, 10^9].
 */
public class ReachANumber {
    public int reachNumber(int t) {
        t = Math.abs(t);
        int s = 1;
        int i = 0;
        int dist = 0;
        while (dist < t) {
            dist += s;
            s++;
            i++;
        }
        while ((dist - t) % 2 == 1) {
            // 1...n can form any number in 1...n*(n+1)/2 by sign chane: if(t<=n) directly pick. otherwise t-n will be in 1..n-1. t will be <= biggest eventually
            //so as long as diff/2==int we are good
            dist += s;
            s++;
            i++;
        }
        return i;
    }

    // if not convinced we can use this method to verify
    // t<=n*(n+1)/2, whether t can be combined by some numbers in 1...n
    boolean doc(int n, int t, int i) {
        if (t <= n) {
            return true;
        }
        return doc(n - 1, t - n, i);
    }
}
