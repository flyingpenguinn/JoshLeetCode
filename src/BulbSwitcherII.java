/*
LC#672
There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.

Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

Flip all the lights.
Flip lights with even numbers.
Flip lights with odd numbers.
Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...


Example 1:

Input: n = 1, m = 1.
Output: 2
Explanation: Status can be: [on], [off]


Example 2:

Input: n = 2, m = 1.
Output: 3
Explanation: Status can be: [on, off], [off, on], [off, off]


Example 3:

Input: n = 3, m = 1.
Output: 4
Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].


Note: n and m both fit in range [0, 1000].
 */
public class BulbSwitcherII {
    /*

The four buttons:

Flip all the lights.
Flip lights with even numbers.
Flip lights with odd numbers.
Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
If we use button 1 and 2, it equals to use button 3.
Similarly...

1 + 2 --> 3, 1 + 3 --> 2, 2 + 3 --> 1
So, there are only 8 cases.

All_on, 1, 2, 3, 4, 1+4, 2+4, 3+4

and first 3 lights determine the rest

     */
    public int flipLights(int n, int m) {
        if (m == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            if (m == 1) {
                return 3;
            } else {
                return 4;
            }
        }
        if (n >= 3) {

            if (m == 1) {
                return 4;
            }
            if (m == 2) {
                return 7;
            }
            if (m == 3) {
                return 8;
            }

            return 8;
        }
        return -1;
    }
}
