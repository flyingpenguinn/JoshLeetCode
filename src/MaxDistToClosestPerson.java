/*
LC#849
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
 */

public class MaxDistToClosestPerson {
    // can be o1 space: every time we pick in the middle of two ones
    /*

     int res = 0, n = seats.length, last = -1;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                res = last < 0 ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        res = Math.max(res, n - last - 1);
        return res;
     */
    public int maxDistToClosest(int[] a) {
        int n = a.length;
        int[] right = new int[n];
        int last = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == 1) {
                last = i;
            } else {
                right[i] = last;
            }
        }
        last = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                last = i;
            } else {
                int leftdist = last == -1 ? Integer.MAX_VALUE : i - last;
                int rightdist = right[i] == -1 ? Integer.MAX_VALUE : right[i] - i;
                int cur = Math.min(leftdist, rightdist);
                max = Math.max(max, cur);
            }
        }
        return max;
    }
}
