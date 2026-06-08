import java.util.*;

public class MaxScoreWithCoprimeElement {
    // templates for coprime and inclusion exclusion
    // also how to count numbers divisible by x
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();

    private static void getprimes() {
        Arrays.fill(isprime, true);
        isprime[0] = false;
        isprime[1] = false;
        int lim = isprime.length;
        for (long i = 2; i < lim; i++) {
            if (!isprime[(int) i]) {
                continue;
            }
            for (long j = i * i; j < lim; j += i) {
                isprime[(int) j] = false;
            }
        }
        for (int i = 0; i < isprime.length; i++) {
            if (isprime[i]) {
                primelist.add(i);
                primeset.add(i);
            }
        }
    }

    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    public static void init(int maxNum) {

        if (isprime == null) {
            isprime = new boolean[maxNum + 1];
            getprimes();
        }
    }

    private List<Integer> factor(int x) {
        List<Integer> res = new ArrayList<>();
        int t = x;

        for (int i = 0; i < primelist.size(); ++i) {
            int p = primelist.get(i);
            if ((long) p * p > t) {
                break;
            }
            if (t % p == 0) {
                res.add(p);
                while (t % p == 0) {
                    t /= p;
                }
            }
        }

        if (t > 1) {
            res.add(t);
        }

        return res;
    }

    public int maxScore(int[] a, int maxVal) {
        init((int) (1e5 + 100));
        int n = a.length;
        int maxv = 0;
        for (int ai : a) {
            maxv = Math.max(maxv, ai);
        }
        int lim = Math.max(maxv, maxVal) + 10;
        // cntDiv[d] = count of numbers divisible by d
        int[] freq = new int[lim];
        for (int ai : a) {
            ++freq[ai];
        }

        int[] cntDiv = new int[lim];
        for (int i = 1; i < lim; ++i) {
            for (int j = i; j < lim; j += i) {
                cntDiv[i] += freq[j];
            }
        }
        int res = 0;
        for (int cand = 1; cand < lim; ++cand) {
            if (cand > maxVal && freq[cand] == 0) {
                continue;
            }
            int cost;
            List<Integer> ps = factor(cand);
            int bad = bad(ps, cntDiv); // get count of numbers NOT co primes with x
            if (bad == 0) {
                cost = freq[cand] > 0 ? 0 : 1;  // this covers 1 or any other case where all num co prime already
            } else {
                cost = bad - (freq[cand] > 0 ? 1 : 0);   // can just turn a bad to good
            }
            res = Math.max(res, cand - cost);
        }
        return res;
    }

    private int bad(List<Integer> ps, int[] cntDiv) {
        int m = ps.size();
        int res = 0;
        // count a or b or c = a+b+c -ab-bc-ac+abc so all odd + all even -
        for (int sub = 1; sub < (1 << m); ++sub) {
            int cur = 1;
            int parts = 0;
            for (int j = 0; j < m; ++j) {
                if (((sub >> j) & 1) == 1) {
                    cur *= ps.get(j);
                    ++parts;
                }
            }
            if (parts % 2 == 1) {
                res += cntDiv[cur];
            } else {
                res -= cntDiv[cur];
            }
        }
        return res;
    }
}
