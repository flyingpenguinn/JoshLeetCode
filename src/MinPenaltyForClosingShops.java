public class MinPenaltyForClosingShops {
    public int bestClosingTime(String customers) {
        char[] s = customers.toCharArray();
        int n = s.length;
        int[] right = new int[n + 1]; // ys on the right
        for (int i = n - 1; i >= 0; --i) {
            right[i] = right[i + 1] + (s[i] == 'Y' ? 1 : 0);
        }
        int left = 0; // ns on the strict left
        int minp = (int) 2e9;
        int mini = -1;
        for (int i = 0; i <= n; ++i) {
            int fine = left + right[i];
            if (minp > fine) {
                minp = fine;
                mini = i;
            }
            if (i < n) {
                left += s[i] == 'N' ? 1 : 0;
            }
            //  System.out.println(fine);
        }
        return mini;
    }
}
