import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SumOfLargestSumSubstrings {
    private PriorityQueue<Long> pq = new PriorityQueue<>();
    private Set<Long> seen = new HashSet<>();

    private boolean isprime(long n) {
        //  System.out.println("checking "+n);
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (long i = 3L; i * i <= n && i < n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long sumOfLargestPrimes(String s) {
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            long cur = 0;
            for (int j = i; j < n; ++j) {
                int jind = s.charAt(j) - '0';
                cur = cur * 10 + jind;
                if (!seen.contains(cur) && isprime(cur)) {
                    seen.add(cur);
                    pq.offer(cur);
                    if (pq.size() > 3) {
                        pq.poll();
                    }
                }
            }
        }
        long res = 0;
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        return res;
    }
}
