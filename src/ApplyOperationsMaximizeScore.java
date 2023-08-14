import java.util.*;

public class ApplyOperationsMaximizeScore {
    private static int MAX = 1000000;
    private static List<Integer> primeNumbers = new ArrayList<>();
    private static boolean[] primes = new boolean[MAX + 1];

    // get all primes
    private static void init() {
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i <= MAX; ++i) {
            if (i > 2 && i % 2 == 0) {
                primes[i] = false;
            }
            if (!primes[i]) {
                continue;
            }
            for (int j = 2 * i; j <= MAX; j += i) {
                primes[j] = false;
            }
        }
        for (int i = 0; i < primes.length; ++i) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }
    }

    private class Item {
        int i;
        int k;
        double v;

        public Item(int i, int k, double v) {
            this.i = i;
            this.k = k;
            this.v = v;
        }
    }

    public int maximumScore(List<Integer> a, int k) {
        if (primeNumbers.isEmpty()) {
            init();
        }
        int n = a.size();
        int[] ps = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));
        for (int i = 0; i < n; ++i) {
            int score = score(a.get(i));
            ps[i] = score;
            pq.offer(new int[]{i, a.get(i)});
        }
        Deque<Integer> st = new ArrayDeque<>();
        int[] lres = new int[n];
        Arrays.fill(lres, -1);
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && ps[st.peek()] < ps[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                lres[i] = st.peek();
            }
            st.push(i);
        }
        st.clear();
        int[] rres = new int[n];
        Arrays.fill(rres, n);
        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && ps[st.peek()] <= ps[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                rres[i] = st.peek();
            }
            st.push(i);
        }
        long[] times = new long[n];
        for (int i = 0; i < n; ++i) {
            long lt = i - lres[i];
            long rt = rres[i] - i;
            times[i] = lt * rt;
        }
        long res = 1;
        while (k > 0) {
            int[] top = pq.poll();
            int index = top[0];
            long v = a.get(index);
            long ct = Math.min(k, times[index]);
            res *= powMod(v, ct);
            res %= Mod;
            k -= ct;
        }

        return (int) res;
    }

    private long powMod(long n, long k) {
        if (k == 0) {
            return 1;
        }
        long half = powMod(n, k / 2);
        long res = half * half;
        res %= Mod;
        if (k % 2 == 1) {
            res *= n;
            res %= Mod;
        }
        return res;
    }

    private long Mod = (long) (1e9 + 7);

    private int score(int n) {
        int res = 0;
        for (int i = 0; i < primeNumbers.size(); ++i) {
            int pn = primeNumbers.get(i);
            if (pn * pn > n) {
                break;
            }
            if (n % pn != 0) {
                continue;
            }
            ++res;
            while (n % pn == 0) {
                n /= pn;
            }

        }
        if (primes[n]) {
            ++res;
        }
        return res;
    }
}
