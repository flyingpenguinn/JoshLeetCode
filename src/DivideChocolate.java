/*
You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.



Example 1:

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:

Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:

Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 */
public class DivideChocolate {

    // maximize minimum sum, almost same as split array largest sum. dp or binary search. here dp would TLE
    public int maximizeSweetness(int[] a, int k) {

        int n = a.length;
        int m = k + 1;
        long l = Integer.MAX_VALUE;
        long u = 0;
        for (int i = 0; i < n; i++) {
            u += a[i];
            l = Math.min(l, a[i]);
        }
        while (l <= u) {
            // mid is the min chunk
            long mid = l + (u - l) / 2;
            long sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (sum + a[i] >= mid) {
                    count++;
                    sum = 0;
                } else {
                    sum += a[i];
                }
            }
            if (count >= m) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return (int) u;
    }
}
