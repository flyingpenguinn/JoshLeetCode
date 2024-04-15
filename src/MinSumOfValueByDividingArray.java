import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class MinSumOfValueByDividingArray {
    // if i...j enumerattion dp doesnt work, try holding intermediate data as parameter and turn to knapsack!
    // the possbiel and values for each j is only 18 as each time we kill off zero or one digit
    private int allone = ((1 << 18) - 1);
    private int Max = (int) 1e9;
    private Map<Integer, Map<Integer, Map<Integer, Integer>>> dp = new HashMap<>();

    public int minimumValueSum(int[] a, int[] v) {
        int n = a.length;
        int vn = v.length;
        int res = solve(a, 0, 0, allone, v);
        return res >= Max ? -1 : res;
    }

    private int solve(int[] a, int i, int j, int cur, int[] v) {
        int n = a.length;
        int vn = v.length;

        if (i == n) {
            return j == vn ? 0 : Max;
        }
        if (j >= vn) {
            return Max;
        }
        if (cur < v[j]) {
            return Max;
        }

        cur &= a[i];
        if (dp.getOrDefault(i, new HashMap<>()).getOrDefault(j, new HashMap<>()).get(cur) != null) {
            return dp.getOrDefault(i, new HashMap<>()).getOrDefault(j, new HashMap<>()).get(cur);
        }
        int way1 = solve(a, i + 1, j, cur, v);
        int way2 = Max;
        if (cur == v[j]) {
            way2 = a[i] + solve(a, i + 1, j + 1, allone, v);
        }
        int res = Math.min(way1, way2);
        Map<Integer, Map<Integer, Integer>> inner1 = dp.getOrDefault(i, new HashMap<>());
        Map<Integer, Integer> inner2 = inner1.getOrDefault(j, new HashMap<>());
        inner2.put(cur, res);
        inner1.put(j, inner2);
        dp.put(i, inner1);
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MinSumOfValueByDividingArray().minimumValueSum(ArrayUtils.read1d("[10,1,10,6,4]"), ArrayUtils.read1d("[0,0]")));
    }
}
