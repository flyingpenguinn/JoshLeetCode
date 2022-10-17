import java.util.HashSet;
import java.util.Set;

public class CountNumberOfDistinctIntegers {
    public int countDistinctIntegers(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        for (int ai : a) {
            int rev = reverse(ai);
            set.add(rev);
        }
        return set.size();
    }

    private int reverse(int n) {
        int res = 0;
        int on = n;

        while (n > 0) {
            int dig = n % 10;
            n /= 10;
            res = res * 10 + dig;

        }
        return res;
    }
}
