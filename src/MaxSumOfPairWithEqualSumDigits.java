import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaxSumOfPairWithEqualSumDigits {
    public int maximumSum(int[] a) {
        Map<Integer, PriorityQueue<Integer>> m = new HashMap<>();
        for (int ai : a) {
            int digSum = sumDigits(ai);
            PriorityQueue<Integer> pq = m.getOrDefault(digSum, new PriorityQueue<Integer>());
            pq.offer(ai);
            if (pq.size() > 2) {
                pq.poll();
            }
            m.put(digSum, pq);
        }
        int res = -1;
        for (int k : m.keySet()) {
            PriorityQueue<Integer> pq = m.get(k);
            if (pq.size() == 2) {
                int v1 = pq.poll();
                int v2 = pq.poll();
                res = Math.max(res, v1 + v2);
            }
        }
        return res;
    }

    private int sumDigits(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}
