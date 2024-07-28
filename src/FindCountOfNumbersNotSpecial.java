import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCountOfNumbersNotSpecial {
    private static int LIMIT = 100000;

    private static boolean[] isprime = new boolean[LIMIT + 1];
    private static List<Integer> primes = new ArrayList<>();


    private static void init() {
        if (primes.size() > 0) {
            return;
        }
        Arrays.fill(isprime, true);
        isprime[1] = false;
        for (int i = 2; i <= LIMIT; ++i) {
            if (!isprime[i]) {
                continue;
            }
            if (i > 2 && i % 2 == 0) {
                isprime[i] = false;
                continue;
            }
            for (int j = 2 * i; j <= LIMIT; j += i) {
                isprime[j] = false;
            }
        }
        for (int i = 2; i <= LIMIT; ++i) {
            if (isprime[i]) {
                primes.add(i);
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        init();
        int start = (int) Math.ceil(Math.sqrt(l * 1.0));
        int end = (int) Math.sqrt(r);
        int sum = r - l + 1;
        for (int i = start; i <= end; ++i) {
            if (isprime[i]) {
                --sum;
            }
        }
        return sum;
    }


    public int nonSpecialCountBrute(int l, int r) {
        init();
        int sum = 0;
        for (int i = l; i <= r; ++i) {
            int divs = countdiv(i);
            if (divs != 2) {
                ++sum;
            }
        }
        return sum;
    }

    private int countdiv(int i) {
        int res = 0;
        for (int j = 1; j < i; ++j) {
            if (i % j == 0) {
                ++res;
            }
        }
        return res;
    }
}
