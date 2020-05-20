import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TallestBillBoard {

    // for every i, 3 possibilities: dont use, use in set1, use in set2
    // can use an array to accelerate.
    // split set problem, use diff to indicate the difference because that's all we care. no need to keep two counts!
    // O(20*5000*@)
    Map<Integer, Integer>[] dp;

    public int tallestBillboard(int[] a) {
        int n = a.length;
        dp = new HashMap[n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashMap<>();
        }
        return dot(a, 0, 0); // /2 because we will be counting the good numbers twice from both sets
    }

    // diff == set1-set2. so we only return size of set1
    private int dot(int[] a, int i, int diff) {
        int n = a.length;
        if (i == n) {
            return diff == 0 ? 0 : Integer.MIN_VALUE;
        }
        Integer ch = dp[i].get(diff);
        if (ch != null) {
            return ch;
        }
        int max = 0;
        int way1 = dot(a, i + 1, diff); // dont use this element
        int way2 = dot(a, i + 1, diff - a[i]);// use it in set2
        int way3 = a[i] + dot(a, i + 1, diff + a[i]);// use it in set1. note because we are putting to set1 we add a[i]
        max = Math.max(way1, Math.max(way2, way3));
        dp[i].put(diff, max);
        return max;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // int[] rods = {1,2};
        int[] rods = {1, 2, 3, 6};
        // int[] rods = {3, 4, 3, 3, 2};
        System.out.println(new TallestBillBoard().tallestBillboard(rods));

    }
}

class TallestBillboardSubsetEnumeration {
    // up to o(3^n) but may just work if diff is big and n is small
    int[] dp;

    public int tallestBillboard(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        dp = new int[1 << n + 1];
        int max = 0;
        int all = (1 << n) - 1;
        for (int st = all - 1; st >= 1; st--) {
            int len = getlen(st, a);
            if (len > max && len <= sum / 2) {
                int other = all ^ st;
                int nst = other;
                while (nst > 0) {
                    int olen = getlen(nst, a);
                    if (len == olen) {
                        max = Math.max(max, len);
                        break;
                    } else if (olen < len) {
                        break;
                    }
                    nst = ((nst - 1) & other);
                }
            }
        }
        return max;
    }

    private int getlen(int st, int[] a) {
        if (dp[st] != 0) {
            return dp[st];
        }
        int r = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (((st >> i) & 1) == 1) {
                r += a[i];
            }
        }
        dp[st] = r;
        return r;
    }
}
