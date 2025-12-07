import java.util.*;

public class LargestPrimeFromConsecutivePrimeSum {
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();

    private void getprimes() {
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

    public int largestPrime(int n) {
        if (isprime == null) {
            isprime = new boolean[500001];
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            getprimes();
        }
        int sum = 0;
        int res = 0;
        for (int i = 0; i <= primelist.size(); ++i) {
            sum += primelist.get(i);
            if (sum > n) {
                break;
            }
            //    System.out.println(sum);
            if (isprime[sum]) {
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}
