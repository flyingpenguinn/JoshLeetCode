import java.util.PriorityQueue;

public class MakeThePrefixSumNonNegative {
    // similar to leetcode 871 min number of refueling stops. we keep going then add the get the min number out of pq
    public int makePrefSumNonNegative(int[] a) {
        int n = a.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long prefix = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            prefix += a[i];
            if (a[i] < 0) {
                pq.offer(a[i]);
            }
            while (prefix < 0 && !pq.isEmpty()) {
                prefix -= pq.poll();
                ++res;
            }
        }
        return res;
    }
}
