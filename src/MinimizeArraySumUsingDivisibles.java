import java.util.*;

public class MinimizeArraySumUsingDivisibles {
    private static List<Integer>[] div;

    public long minArraySum(int[] a) {
        if (div == null) {
            div = new ArrayList[100001];
            for (int i = 1; i <= 100000; ++i) {
                div[i] = new ArrayList<>();
                for (int j = 1; j * j <= i; ++j) {
                    if (i % j == 0) {
                        div[i].add(j);
                        int other = i / j;
                        if (other != j) {
                            div[i].add(other);
                        }
                    }
                }
                Collections.sort(div[i]);
            }
        }
        int n = a.length;
        Arrays.sort(a);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            for (int di : div[v]) {
                if (seen.contains(di)) {
                    a[i] = di;
                    break;
                }
            }
            seen.add(a[i]);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            res += a[i];
        }
        return res;
    }
}
