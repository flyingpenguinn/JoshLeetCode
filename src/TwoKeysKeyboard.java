/*
LC#650
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.


Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:

Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.


Note:

The n will be in the range [1, 1000].

 */
public class TwoKeysKeyboard {
    // must be through a v,only factor can do that
    int[] dp;

    public int minSteps(int n) {
        dp = new int[n + 1];
        return dom(n);
    }

    int dom(int n) {
        if (n <= 1) {
            return 0;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int min = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int cur = dom(i) + 1 + n / i - 1;
                min = Math.min(cur, min);
                if (i != n / i) {
                    cur = dom(n / i) + 1 + i - 1;
                    min = Math.min(cur, min);
                }

            }
        }
        dp[n] = min;
        return min;
    }
}

class TwoKeysKeyboardOn {
    // get prime factors...all prime number needs n steps. x*y == y*x == x+y steps
    public int minSteps(int n) {
        int r = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                r += i;
                n /= i;
            }
        }
        return r;
    }
}