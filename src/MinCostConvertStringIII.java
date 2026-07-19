import base.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class MinCostConvertStringIII {
    private int[] dp;
    int Max = (int) 1e9;
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
        int n = source.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        int rt= solve(source, target, rules, costs, 0);
        return rt>=Max? -1: rt;
    }

    private int solve(String source, String target, List<List<String>> rules, int[] costs, int i) {
        int n = source.length();
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }

        int res = Max;
        if(source.charAt(i) == target.charAt(i)){
            res = solve(source, target, rules, costs, i+1);
        }
        for (int j = 0; j < rules.size(); ++j) {
            String src = rules.get(j).get(0);
            String dst = rules.get(j).get(1);
            if (target.startsWith(dst, i)) {
                int addcost = canmatch(source, i, src);
                if (addcost == -1) {
                    continue;
                }
                int cur = costs[j] + addcost + solve(source, target, rules, costs, i + src.length());
                res = Math.min(res, cur);
            }
        }
        dp[i] = res;
        return res;
    }

    private int canmatch(String source, int i, String src) {
        int res = 0;
        for (int j = i; j < source.length() && j - i < src.length(); ++j) {
            if (src.charAt(j-i) == '*') {
                ++res;
                continue;
            }
            if (source.charAt(j) != src.charAt(j - i)) {
                return -1;
            }
        }
        return res;
    }

    static void main() {
        System.out.println(new MinCostConvertStringIII().minCost("cat", "dog", ArrayUtils.readAsListUnevenString("[[c*t,dog]]"), ArrayUtils.read1d("3,4")));
    }
}
