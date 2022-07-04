import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfPeopleAwareOfSecret {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        Deque<long[]> delayq = new ArrayDeque<>();
        Deque<long[]> activeq = new ArrayDeque<>();
        long allActive = 0;
        long res = 1;
        long mod = (long) (1e9 + 7);
        delayq.offerLast(new long[]{1, 1});
        for (long i = 2; i <= n; ++i) {
            long[] dtop = delayq.peekFirst();
            if (dtop[0] + delay <= i) {
                allActive += dtop[1];
                allActive %= mod;
                activeq.offerLast(delayq.pollFirst());
            }
            if (activeq.isEmpty()) {
                continue;
            }
            long[] atop = activeq.peekFirst();
            if (atop[0] + forget <= i) {
                allActive -= atop[1];
                allActive %= mod;
                if (allActive < 0) {
                    allActive += mod;
                }
                res -= atop[1];
                res %= mod;
                if (res < 0) {
                    res += mod;
                }
                activeq.pollFirst();
            }
            delayq.offerLast(new long[]{i, allActive});
            res += allActive;
            res %= mod;
        }
        return (int) (res % mod);
    }
}
