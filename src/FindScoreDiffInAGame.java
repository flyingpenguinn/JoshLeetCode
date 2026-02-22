public class FindScoreDiffInAGame {
    public int scoreDifference(int[] a) {
        int n = a.length;
        int[] cnt = new int[2];
        int cp = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v % 2 == 1) {
                cp ^= 1;
            }
            if ((i + 1) % 6 == 0) {
                cp ^= 1;
            }
            //   System.out.println("i="+i+" cp="+cp);
            cnt[cp] += v;
        }
        return cnt[0] - cnt[1];
    }
}
