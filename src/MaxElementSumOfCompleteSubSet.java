import java.util.HashMap;
import java.util.List;

public class MaxElementSumOfCompleteSubSet {
    private static boolean init = false;
    private static final int limit = 100005;
    private static int[] prime = new int[limit];
    private static int[] k = new int[limit];

    private static void doInit() {

        for (int i = 1; i < limit; i++) {
            k[i] = i;
        }

        for (int i = 2; i < limit; i++) {
            if (prime[i] == 0)
                for (int j = i; j < limit; j += i) {
                    prime[j] = 1;
                    while (k[j] % (i * i) == 0) {
                        k[j] /= (i * i);
                    }
                }
        }
    }


    public long maximumSum(List<Integer> a) {
        int n = a.size();
        if (!init) {
            init = true;
            doInit();
        }
        HashMap<Integer, Long> sum = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int key = k[i + 1];
            long cur = sum.getOrDefault(key, 0L);
            long nv = cur + a.get(i);
            res = Math.max(res, nv);
            sum.put(key, nv);
        }
        return res;
    }
}
