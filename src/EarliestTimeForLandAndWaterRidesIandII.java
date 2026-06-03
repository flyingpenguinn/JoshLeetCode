
import java.util.Arrays;

public class EarliestTimeForLandAndWaterRidesIandII {
    // sort by end!
    // we only need a good b ending. we filter all bad bs that starts too early (for these we need to take duration)
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
        int n = as.length;
        int m = bs.length;
        Event[] a = new Event[n];
        for (int i = 0; i < n; ++i) {
            a[i] = new Event(as[i], ad[i], as[i] + ad[i]);
        }

        Event[] b = new Event[m];
        for (int i = 0; i < m; ++i) {
            b[i] = new Event(bs[i], bd[i], bs[i] + bd[i]);
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x.end, y.end));
        Arrays.sort(b, (x, y) -> Integer.compare(x.end, y.end));

        int bj = 0;
        int Max = (int) 1e9;
        int res = Max;
        int minduration = Max;
        for (int i = 0; i < n; ++i) {
            int cend = a[i].end;
            while (bj < m && b[bj].start < cend) {
                minduration = Math.min(minduration, b[bj].duration);
                ++bj;
            }
            if (bj < m) {
                int cur1 = b[bj].end;
                res = Math.min(res, cur1);

            }
            int cur2 = cend + minduration;
            res = Math.min(res, cur2);
        }
        int aj = 0;
        minduration = Max;
        for (int i = 0; i < m; ++i) {
            int cend = b[i].end;
            while (aj < n && a[aj].start < cend) {
                minduration = Math.min(minduration, a[aj].duration);
                ++aj;
            }
            if (aj < n) {
                int cur1 = a[aj].end;
                res = Math.min(res, cur1);
            }
            int cur2 = cend + minduration;
            res = Math.min(res, cur2);
        }
        return res;
    }
}
