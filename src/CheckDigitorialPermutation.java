public class CheckDigitorialPermutation {
    private static int[] facts;
    private boolean found = false;

    public boolean isDigitorialPermutation(int n) {
        if (facts == null) {
            facts = new int[10];
            facts[0] = 1;
            for (int i = 1; i <= 9; ++i) {
                facts[i] = facts[i - 1] * i;
            }
        }

        String str = String.valueOf(n);
        int[] digs = new int[10];
        int target = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            int cind = c - '0';
            ++digs[cind];
            target += facts[cind];
        }
        found = false;
        dfs(digs, 0, str.length(), target, 0);
        return found;
    }

    private void dfs(int[] dig, int cur, int rem, int target, int sel) {
        if (rem == 0) {
            if (cur == target) {
                found = true;
            }
            return;
        }
        int start = sel == 0 ? 1 : 0;
        for (int j = start; j < 10; ++j) {
            if (dig[j] > 0) {
                --dig[j];
                int ncur = cur * 10 + j;
                dfs(dig, ncur, rem - 1, target, sel + 1);
                if (found) {
                    return;
                }
                ++dig[j];
            }
        }
    }
}
