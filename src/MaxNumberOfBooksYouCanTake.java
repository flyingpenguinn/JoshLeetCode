import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxNumberOfBooksYouCanTake {
    /*
        Step 1: Create a dp array where dp[i] is the maximum number of books that we can take from bookshelves 0 to i and we must take all books from bookshelf i.
Step 2: Keep taking as many books as we can (i.e. starting from bookshelf i and going backwards, you take arr[i], arr[i] - 1, arr[i] - 2, …, books).
Step 3: Keep a stack of possible indices for j. If x is the number at the top of the stack, keep popping from the stack while arr[x] ≥ arr[i] - (i - x). This is because if the inequality mentioned before is true, x will never be an index j as index i will run out of items first.
for i, the best way is to pick a[i], a[i]-1.... up to j+1 then follow the way we took from 0 to j
dp[i] += dp[j]
     */
    public long maximumBooks(int[] a) {
        int n = a.length;
        long[] dp = new long[n];
        Deque<Integer> st = new ArrayDeque<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] >= a[i] - (i - st.peek())) {
                st.pop();
            }
            int j = st.isEmpty() ? -1 : st.peek();
            // we take a[i], a[i]-1,a[i]-(i-j-1) up to j+1
            long cur = 0;
            if (a[i] - (i - j - 1) < 0) {
                // from 0 to a[i] anyway
                cur = (0L + a[i]) * (a[i] + 1) / 2;
            } else {
                cur = (0L + a[i] - (i - j - 1) + a[i]) * (i - j) / 2;
            }
            dp[i] = cur;
            if (j >= 0) {
                dp[i] += dp[j];
            }
            res = Math.max(res, dp[i]);
            st.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxNumberOfBooksYouCanTake().maximumBooks(ArrayUtils.read1d("[8,5,2,7,9]")));
    }
}
