import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SumOfTotalStrengthOfWizards {
    /*
    think about each i's contribution to the whole answer
    positive parts:
(prefix[i + 1] + prefix[i + 2] + ... + prefix[right]) * (i - left)
negative parts:
(prefix[left + 1] + prefix[left + 2] + ... + prefix[i]) * (right - i)
The range sum of prefix can be optimized by pre-compute prefix-sum of prefix.
     */
    public int totalStrength(int[] a) {
        long MOD = (int)(1e9+7);
        int n = a.length;
        // sum of first k elements
        long[] prefix = new long[n+1];
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = (prefix[i] + a[i]) % MOD;
        }
        // sum of first k prefix
        long[] prefix_sum = new long[n+2];
        for (int i = 0; i <= n; ++i) {
            prefix_sum[i + 1] = (prefix_sum[i] + prefix[i]) % MOD;
        }

        // first index on the left < current st
        int[] left = new int[n];
        Arrays.fill(left, -1);
        // mono increase
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // first index on the right <= current st
        int[] right = new int[n];
        Arrays.fill(right, n);
        stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && a[stack.peek()] > a[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i <n; ++i) {
            res += ((prefix_sum[right[i] + 1] - prefix_sum[i + 1]) * (i - left[i]) % MOD + MOD * 2 -
                    (prefix_sum[i + 1] - prefix_sum[left[i] + 1]) * (right[i] - i) % MOD) % MOD * a[i] % MOD;
            res %= MOD;
        }
        return (int) res;
    }
}
