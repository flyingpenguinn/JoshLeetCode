import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxNumberOfVisiblePoints {
    // use two pointers to get the max range
    // to handle circular case, note atan2 returns from -pi to pi. so if it's <0, we add 2*pi to allow pi to 2*pi be connected together...
    private double eps = 0.000001;

    public int visiblePoints(List<List<Integer>> ps, int angle, List<Integer> loc) {
        double rang = angle * Math.PI / 180.0;
        int n = ps.size();
        int res = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> li = ps.get(i);
            if (li.equals(loc)) {
                res++;
            }
        }
        List<Double> a = gen(ps, loc, true);
        Collections.sort(a);
        //  System.out.println(a);
        int sol1 = res + maxlen(a, rang);
        a = gen(ps, loc, false);
        Collections.sort(a);
        //    System.out.println(a);
        int sol2 = res + maxlen(a, rang);
        return Math.max(sol1, sol2);
    }

    private List<Double> gen(List<List<Integer>> ps, List<Integer> loc, boolean adj) {
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < ps.size(); i++) {
            if (!ps.get(i).equals(loc)) {
                res.add(angle(loc, ps.get(i), adj));
            }
        }
        return res;
    }

    private int maxlen(List<Double> a, double ang) {
        int low = 0;
        int high = -1;
        int max = 0;
        while (true) {
            if (low > high || a.get(high) - a.get(low) <= ang + eps) {
                max = Math.max(high - low + 1, max);
                high++;
                if (high == a.size()) {
                    break;
                }
            } else {
                low++;
            }
        }
        return max;
    }

    private double angle(List<Integer> a, List<Integer> b, boolean adj) {
        double ang = Math.atan2((b.get(1) - a.get(1) + 0.0), (b.get(0) - a.get(0) + 0.0));
        if (ang < 0 && adj) {
            double res = ang + 2 * Math.PI;
            return res;
        } else {
            return ang;
        }
    }
}
