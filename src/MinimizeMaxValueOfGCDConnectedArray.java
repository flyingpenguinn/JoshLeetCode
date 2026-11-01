import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinimizeMaxValueOfGCDConnectedArray {
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

    public long minimumMaxValue(int n, int index, int v) {
        if (v == 1) {
            return -1;
        }
        Prime.init(100000);
        long minp = -1;
        for (int i = 0; i < Prime.primelist.size(); ++i) {
            int cp = Prime.primelist.get(i);
            if (cp * cp > v) {
                break;
            }
            if (v % cp == 0) {
                minp = cp;
                break;
            }
        }
        if (minp == -1) {
            if (index > 0) {
                return -1;
            } else {
                if(index==n-1){
                    return v;
                }
                long next = v + v;
                if (n - 1 == index + 1) {
                    return next;
                }
                long inc = n-1-(index+2)+1;
                long incv = inc*2;
                return next + incv;
            }
        } else {
            long prev = v-minp;
            long dec = (index-1)*2L;
            long first = prev - dec;
            if(first<=1){
                return -1;
            }
            if(index == n-1){
                return v;
            }
            long next = v + minp;
            if (n - 1 == index + 1) {
                return next;
            }
            long inc = n-1-(index+2)+1;
            long incv = inc*2;
            return next + incv;
        }
    }
}
