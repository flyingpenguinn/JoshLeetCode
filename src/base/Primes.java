package base;

import java.util.*;

public class Primes {
    /*
    to factor, keep dividing with primes <= sqrt(num). any remaining number of num must be prime itself
     */
    static class Prime {
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

        // for num <= 1e5, at most 6!
        private List<Integer> distinctPrimeFactors(int x) {
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


        public static void init(int maxNum) {

            if (isprime == null) {
                isprime = new boolean[maxNum + 1];
                getprimes();
            }
        }
    }
}
