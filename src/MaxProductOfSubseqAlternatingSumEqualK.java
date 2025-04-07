import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaxProductOfSubseqAlternatingSumEqualK {
    // sparse dp, use hashmap to store dp states
    private int lim;
    private int k;

    public int maxProduct(int[] a, int k, int l) {
        this.lim = l;
        this.k = k;
        dp.clear();
        dp1.clear();
        boolean has0 = false;
        int sum = 0;
        for (int ai : a) {
            sum += ai;
            if (ai == 0) {
                has0 = true;
            }
        }
        if(k>sum || k<-sum){
            return -1;
        }
        int rt = -1;
        if (has0) {
            int hasumdiff = solve1(a, 0, 0, 0, 0, 0);
            if (hasumdiff == 1) {
                rt = 0;
            }
        }

        int rt2 = solve(a, 0, 0, 0, 1, 0);
        return Math.max(rt, rt2);
    }


    private int Min = -10000;
    private Map<String, Integer> dp = new HashMap<>();
    private Map<String, Integer> dp1 = new HashMap<>();

    private int solve1(int[] a, int i, int sel, int parity, int has0, int sumdiff) {
        int n = a.length;

        if (i == n) {
            if (sel == 1 && sumdiff == k && has0 == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        String key = i + "_" + sel + "_" + parity + "_" + " " + "_" + sumdiff+"_"+has0;
        if (dp1.containsKey(key)) {

            return dp1.get(key);
        }
        int way1 = solve1(a, i + 1, sel, parity, has0, sumdiff);
        if (parity == 0) {
            sumdiff += a[i];
        } else {
            sumdiff -= a[i];
        }
        int newhas0 = a[i]==0?1: 0;
        int way2 = solve1(a, i + 1, 1, parity ^ 1, newhas0, sumdiff);
        int res = Math.max(way1, way2);
        dp1.put(key, res);

        return res;
    }

    private int solve(int[] a, int i, int sel, int parity, int product, int sumdiff) {
        int n = a.length;
        if (product > lim) {
            return Min;
        }
        if (i == n) {
            if (sel == 1 && sumdiff == k) {
                return product;
            } else {
                return Min;
            }
        }
        String key = i + "_" + sel + "_" + parity + "_" + " " + product + "_" + sumdiff;
        if (dp.containsKey(key)) {

            return dp.get(key);
        }
        int way1 = solve(a, i + 1, sel, parity, product, sumdiff);
        if (parity == 0) {
            sumdiff += a[i];
        } else {
            sumdiff -= a[i];
        }
        int newproduct = product * a[i];
        int way2 = solve(a, i + 1, 1, parity ^ 1, newproduct, sumdiff);
        int res = Math.max(way1, way2);
        dp.put(key, res);

        return res;
    }

}
