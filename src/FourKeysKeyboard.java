import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#651
Imagine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation:
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation:
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.
 */
public class FourKeysKeyboard {
    // must be vvv in the end. enumerate how many of them

    int[] dp;

    public int maxA(int n) {
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dom(n);
    }

    private int dom(int n) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int rt = 1 + dom(n - 1);
        for (int paste = 1; n - paste - 2 > 0; paste++) {
            int sub = n - paste - 2;
            int cur = dom(sub) * (paste + 1);
            rt = Math.max(rt, cur);
        }
        dp[n] = rt;
        return rt;
    }
}
