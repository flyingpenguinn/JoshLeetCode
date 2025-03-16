import java.util.HashMap;
import java.util.Map;

public class CountBeautifulNumbers {

    private Map<String, Integer> dp;

    private int dp(String s, int i, boolean tight, int product, int sum, boolean lead) {
        int sn = s.length();
        if (i == sn) {
            if (lead) {
                return 0;
            }
            return product % sum == 0 ? 1 : 0;
        }
        String key = i + "_" + tight + "_" + product + "_" + sum + "_" + lead;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int lim = tight ? s.charAt(i) - '0' : 9;
        int res = 0;
        for (int d = 0; d <= lim; ++d) {
            boolean ntight = tight;
            if (d < lim) {
                ntight = false;
            }
            int nproduct = product;
            if (!lead) {
                nproduct *= d;
            } else {
                nproduct = d;
            }
            int nsum = sum + d;
            boolean nlead = lead;
            if (d != 0) {
                nlead = false;
            }
            res += dp(s, i + 1, ntight, nproduct, nsum, nlead);
        }
        dp.put(key, res);
        return res;
    }

    private int countBeautiful(int n) {
        if (n <= 0) return 0;
        dp = new HashMap<>();
        return dp(String.valueOf(n), 0, true, 1, 0, true);
    }

    public int beautifulNumbers(int l, int r) {
        return countBeautiful(r) - countBeautiful(l - 1);
    }


    public static void main(String[] args) {
        final CountBeautifulNumbers sol = new CountBeautifulNumbers();
        System.out.println(sol.beautifulNumbers(1, 100));
        System.out.println(sol.dp.size());
    }
}
