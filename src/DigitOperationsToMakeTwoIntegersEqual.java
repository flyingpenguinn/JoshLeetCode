import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DigitOperationsToMakeTwoIntegersEqual {
    private int Max = (int) 1e9;
    private int len = 0;

    private int[] todigs(int n) {
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            res[i] = n % 10;
            n /= 10;
        }
        return res;
    }

    private int tonum(int[] digs, int i, int d) {
        int res = 0;
        for (int j = 0; j < len; ++j) {
            int v = digs[j];
            if (i == j) {
                v += d;
            }
            res = res * 10 + v;
        }
        return res;
    }

    public int minOperations(int n, int m) {
        if (isprime(m) || isprime(n)) {
            return -1;
        }
        len = String.valueOf(n).length();
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        q.offer(new int[]{n, 0});
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(n, 0);
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int v = top[0];
            int cd = top[1];
            // System.out.println("v=" + v + " cd=" + cd);
            int nd = cd + v;
            if (v == m) {
                return nd;
            }
            int[] digs = todigs(v);
            for (int i = 0; i < digs.length; ++i) {
                if (digs[i] < 9) {
                    int newn = tonum(digs, i, 1);
                    if (!isprime(newn) && dist.getOrDefault(newn, Max) > nd) {
                        dist.put(newn, nd);
                        q.offer(new int[]{newn, nd});
                    }
                }
                if (digs[i] > 0 && !(digs[i] == 1 && i == 0)) {
                    int newn = tonum(digs, i, -1);
                    if (!isprime(newn) && dist.getOrDefault(newn, Max) > nd) {
                        dist.put(newn, nd);
                        q.offer(new int[]{newn, nd});
                    }
                }
            }
        }
        return -1;
    }

    private static Boolean[] prime = new Boolean[10001];

    private static boolean isprime(int n) {
        if (n == 1) {
            return false;
        }
        if (prime[n] != null) {
            return prime[n];
        }
        for (int t = 2; t * t <= n; ++t) {
            if (n % t == 0) {
                return false;
            }
        }
        return true;
    }
}
