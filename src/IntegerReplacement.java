import java.util.HashMap;
import java.util.Map;

public class IntegerReplacement {
    Map<Long, Integer> dp = new HashMap<>();

    public int integerReplacement(int n) {

        return domin(n);
    }

    int domin(long n) {
        if (n == 1) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int rt = 0;
        if (n % 2 == 0) {
            rt = 1 + domin(n / 2);
        } else {
            rt = 1 + Math.min(domin(n + 1), domin(n - 1));
        }
        dp.put(n, rt);
        return rt;
    }
}
