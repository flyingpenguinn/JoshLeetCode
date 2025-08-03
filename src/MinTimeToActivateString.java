import java.util.TreeSet;

public class MinTimeToActivateString {
    class Int {
        int s;
        int e;

        public Int(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    private int Max = (int) 1e9;

    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        TreeSet<Int> segs = new TreeSet<>((x, y) -> {
            if (x.s != y.s) {
                return Integer.compare(x.s, y.s);
            } else {
                return Integer.compare(x.e, y.e);
            }
        });
        segs.add(new Int(0, n - 1));
        long bads = 1L * n * (n + 1) / 2;
        long all = 1L * n * (n + 1) / 2;
        for (int i = 0; i < order.length; ++i) {
            int t = order[i];
            Int found = segs.floor(new Int(t, Max));

            int fs = found.s;
            int fe = found.e;
            Int s1 = new Int(fs, t - 1);
            Int s2 = new Int(t + 1, fe);
            addseg(segs, s1);
            addseg(segs, s2);
            long nlen1 = t - fs;
            long nlen2 = fe - t;
            long subs1 = nlen1 * (nlen1 + 1) / 2;
            long subs2 = nlen2 * (nlen2 + 1) / 2;
            long nsub = subs1 + subs2;
            long oldlen = fe - fs + 1;
            long oldsub = oldlen * (oldlen + 1) / 2;
            long lost = oldsub - nsub;
            bads -= lost;
            segs.remove(found);
            if (all - bads >= k) {
                return i;
            }
        }
        return -1;
    }

    private void addseg(TreeSet<Int> segs, Int t) {
        if (t.s <= t.e) {
            segs.add(t);
        }
    }
}
