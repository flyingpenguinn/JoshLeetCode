public class MinNumOperationsToConvertTime {
    private int[] diffs = new int[]{60, 15, 5, 1};

    public int convertTime(String current, String correct) {
        int t1 = toint(current);
        int t2 = toint(correct);

        int res = 0;
        for (int i = 0; i < diffs.length; ++i) {
            int d = diffs[i];
            if (t2 - t1 >= d) {
                int delta = (t2 - t1) / d;
                res += delta;
                t1 += delta * d;
            }
        }
        return res;
    }

    private int toint(String s) {
        String[] ss = s.split(":");
        int p1 = Integer.valueOf(ss[0]);
        int p2 = Integer.valueOf(ss[1]);
        return p1 * 60 + p2;
    }
}
