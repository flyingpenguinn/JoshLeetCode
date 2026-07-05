import java.util.*;

public class DivisibleGame {
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();

    public static void init(int maxNum) {

        if (isprime == null) {
            isprime = new boolean[maxNum + 1];
            getprimes();
        }
    }

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

    private List<Long> distinctPrimeFactors(long x) {
        List<Long> res = new ArrayList<>();
        long t = x;

        for (int i = 0; i < primelist.size(); ++i) {
            long p = primelist.get(i);
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

    private long Mod = (long) (1e9 + 7);

    public int divisibleGame(int[] a) {
        int n = a.length;
        init(1000000);
        long maxdiff = Long.MIN_VALUE;
        long chosen = 2;
        for (int l = 0; l < n; ++l) {
            Map<Long, Long> fm = new HashMap<>();
            long allsum = 0;
            for (int r = l; r < n; ++r) {
                long v = a[r];
                List<Long> cv = distinctPrimeFactors(v);
                long maxsum = 0;
                long cchosen = 2;
                allsum += v;
                for (long vi : cv) {
                    long nsum = fm.getOrDefault(vi, 0L) + v;
                    fm.put(vi, nsum);
                    if (nsum > maxsum) {
                        maxsum = nsum;
                        cchosen = vi;
                    }
                }

                long bob = allsum - maxsum;
                long diff = maxsum - bob;
                if (diff > maxdiff) {
                    maxdiff = diff;
                    chosen = cchosen;
                } else if (diff == maxdiff) {
                    chosen = Math.min(chosen, cchosen);
                }
            }

        }
        long res = maxdiff * chosen;
        res %= Mod;
        if (res < 0) {
            res += Mod;
        }
        return (int) res;
    }
}
