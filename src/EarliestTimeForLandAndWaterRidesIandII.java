import java.util.Arrays;

public class EarliestTimeForLandAndWaterRidesIandII {
    class Event {
        int start;
        int duration;
        int end;

        public Event(int start, int duration, int end) {
            this.start = start;
            this.duration = duration;
            this.end = end;
        }
    }

    public int earliestFinishTime(int[] as, int[] ad, int[] bs, int[] bd) {
        int an = as.length;
        int bn = bs.length;
        Event[] a = new Event[an];
        for (int i = 0; i < an; ++i) {
            a[i] = new Event(as[i], ad[i], as[i] + ad[i]);
        }
        Event[] b = new Event[bn];
        for (int i = 0; i < bn; ++i) {
            b[i] = new Event(bs[i], bd[i], bs[i] + bd[i]);
        }
        int res = Integer.MAX_VALUE;
        Arrays.sort(a, (x, y) -> Integer.compare(x.start, y.start));
        Arrays.sort(b, (x, y) -> Integer.compare(x.start, y.start));
        int[] minda1 = new int[an];
        minda1[0] = a[0].duration;
        for (int i = 1; i < an; ++i) {
            minda1[i] = Math.min(minda1[i - 1], a[i].duration);
        }

        int[] minda2 = new int[an];
        minda2[an - 1] = a[an - 1].end;
        for (int i = an - 2; i >= 0; --i) {
            minda2[i] = Math.min(minda2[i + 1], a[i].end);
        }

        int[] mindb1 = new int[bn];
        mindb1[0] = b[0].duration;
        for (int i = 1; i < bn; ++i) {
            mindb1[i] = Math.min(mindb1[i - 1], b[i].duration);
        }

        int[] mindb2 = new int[bn];
        mindb2[bn - 1] = b[bn - 1].end;
        for (int i = bn - 2; i >= 0; --i) {
            mindb2[i] = Math.min(mindb2[i + 1], b[i].end);
        }

        for (int i = 0; i < an; ++i) {
            int aend = a[i].end;
            int pos = binary(b, aend);
            // pos is first >=
            if (pos < bn) {
                res = Math.min(res, mindb2[pos]);
            }
            if (pos - 1 >= 0) {
                res = Math.min(res, aend + mindb1[pos - 1]);
            }
        }
        for (int i = 0; i < bn; ++i) {
            int bend = b[i].end;
            int pos = binary(a, bend);
            if (pos < an) {
                res = Math.min(res,minda2[pos]);
            }
            if (pos - 1 >= 0) {
                res = Math.min(res, bend + minda1[pos - 1]);
            }
        }
        return res;
    }

    private int binary(Event[] x, int t) {
        int n = x.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (x[mid].start < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }
}
