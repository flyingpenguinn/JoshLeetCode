import java.util.Arrays;

public class ConstructStringWithMinimalCost {
    // should have TLE... weak test cases
    class TN {
        char c;
        TN[] ch;
        int cost;

        TN(char c) {
            this.c = c;
            ch = new TN[26];
            cost = Max;
        }
    }


    private void insert(String word, int cost) {
        TN cur = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.ch[index] == null) {
                cur.ch[index] = new TN(c);
            }
            cur = cur.ch[index];
        }
        cur.cost = Math.min(cur.cost, cost);
    }

    private TN root = new TN('*');

    public int minimumCost(String t, String[] ws, int[] cs) {
        int n = t.length();

        for (int i = 0; i < ws.length; i++) {
            insert(ws[i], cs[i]);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Max);

        for (int i = 0; i < n; i++) {
            if (i > 0 && dp[i - 1] == Max) {
                continue;
            }
            TN p = root;
            for (int j = i; j < n; j++) {
                int cind = t.charAt(j) - 'a';
                if (p.ch[cind] == null) {
                    break;
                }
                p = p.ch[cind];
                if (p.cost != Max) {
                    dp[j] = Math.min(dp[j], (i == 0 ? 0 : dp[i - 1]) + p.cost);
                }
            }
        }

        return dp[n - 1] == Max ? -1 : dp[n - 1];
    }

    private int Max = (int) 2e9;
}
