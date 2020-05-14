import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeWithFactors {
    int Mod = 1000000007;

    public int numFactoredBinaryTrees(int[] a) {
        // like Lis
        Arrays.sort(a);
        int n = a.length;
        long r = 0;
        //num of trees with root==key
        Map<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long c = 1L;  // self is tree
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] % a[j] != 0) {
                    continue;
                }
                int left = a[j];
                int right = a[i] / left;
                long t1 = dp.getOrDefault(left, 0L);
                long t2 = dp.getOrDefault(right, 0L);
                c = (c + t1 * t2) % Mod;
            }
            dp.put(a[i], c);
            r = (r + c) % Mod;
        }
        return (int) r;
    }
}
