import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#995
In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.



Example 1:

Input: A = [0,1,0], K = 1
Output: 2
Explanation: Flip A[0], then flip A[2].
Example 2:

Input: A = [1,1,0], K = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].
Example 3:

Input: A = [0,0,0,1,0,1,1,0], K = 3
Output: 3
Explanation:
Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]


Note:

1 <= A.length <= 30000
1 <= K <= A.length
 */
public class MinimumNumberOfKFlips {

    // if ith is 0 we will have to flip it and the next k-1 bits. we record how many flips we have done and when one will loose its effect
    // it's always wise to flip 0 to 1 and never turn it backward
    public int minKBitFlips(int[] a, int k) {
        int n = a.length;
        Deque<Integer> flips = new ArrayDeque<>();
        int r = 0;
        for (int i = 0; i < n; i++) {
            int fc = flips.size();
            int v = (a[i] + fc) % 2;
            if (v == 0) {
                r++;
                flips.offerLast(i + k - 1);
            }
            if (!flips.isEmpty() && flips.peekFirst() == i) {
                flips.pollFirst();
            }
        }
        return flips.isEmpty() ? r : -1;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 0, 1, 0, 1, 1, 0};
        System.out.println(new MinimumNumberOfKFlips().minKBitFlips(a, 3));
    }
}
