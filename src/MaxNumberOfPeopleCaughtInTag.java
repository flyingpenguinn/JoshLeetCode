public class MaxNumberOfPeopleCaughtInTag {
    public int catchMaximumAmountofPeople(int[] a, int dist) {
        int n = a.length;
        int j = 0; // j will scan the candidates to be caught
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == 1) {
                while (j < i - dist) {
                    ++j;
                }
                // j now >=i-dist
                int maxMove = Math.min(i + dist, n - 1);
                while (j <= maxMove && a[j] != 0) {
                    ++j;
                }
                if (j <= maxMove) {
                    ++res;
                    ++j; // can't catch this guy any more
                }
            }
        }
        return res;
    }
}
