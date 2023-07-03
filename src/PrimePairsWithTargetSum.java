import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimePairsWithTargetSum {
    private static Boolean[] prime;

    public List<List<Integer>> findPrimePairs(int n) {
        if (prime == null) {
            prime = new Boolean[1000001];
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 2; i <= n / 2; ++i) {
            if (isprime(i) && isprime(n - i)) {
                res.add(List.of(i, n - i));
            }
        }
        Collections.sort(res, (x, y) -> Integer.compare(x.get(0), y.get(0)));
        return res;
    }

    private boolean isprime(int n) {
        if (prime[n] != null) {
            return prime[n];
        }
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                prime[n] = false;
                return false;
            }
        }
        prime[n] = true;
        return true;
    }
}
