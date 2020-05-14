import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import static java.lang.Math.*;

public class MinimizeRoundingError {
    // first all floor
    // then find the diff.
    // sort by distance to ceiling, and get them through via a priority queue
    public String minimizeError(String[] pr, int t) {

        int n = pr.length;
        double floorsum = 0.0;
        double delta = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Double pi = Double.valueOf(pr[i]);
            floorsum += floor(pi);
            delta += pi - floor(pi);
            pq.offer(ceil(pi) - pi);
        }
        if (floorsum > t) {
            return "-1";
        }

        while (t - floorsum > 0.000001) {
            if (pq.isEmpty()) {
                return "-1";
            }
            double next = pq.poll();
            if (next > 0.0) {
                // in case floor == ceil
                floorsum += 1;
                delta -= 1.0 - next; // to ceiling== 0.4 means to floor = 0.6
                delta += next;
            }
        }
        return String.format("%.3f", delta);
    }

    public static void main(String[] args) {
        String[] pr = {"2.000", "2.000", "2.000", "2.000", "2.000"};
        System.out.println(new MinimizeRoundingError().minimizeError(pr, 11));
    }
}

// floor all then convert to 0,1 subset sum problem
class MinimizeRoundingErrorDp {

    double[] p;

    public String minimizeError(String[] pr, int t) {

        int n = pr.length;
        p = new double[n];
        int floorsum = 0;
        int ceilsum = 0;
        for (int i = 0; i < n; i++) {
            Double pi = Double.valueOf(pr[i]);
            p[i] = pi;
            floorsum += (int) floor(pi);
            ceilsum += (int) ceil(pi);
        }
        if (floorsum > t) {
            return "-1";
        }
        if (ceilsum < t) {
            return "-1";
        }
        dp = new double[n][n+1];
        // t- floor within n
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1.0);
        }
        double rt = domin(p, t - floorsum, 0);
        if (rt >= Max) {
            return "-1";
        } else {
            return String.format("%.3f", rt);
        }

    }

    double Max = 10000.0;
    double[][] dp;

    double domin(double[] p, int t, int i) {
        // System.out.println("i="+i+" t="+t);
        if (i == p.length) {
            if (t == 0) {
                return 0.0;
            } else {
                return Max;
            }
        }
        if(t<0){
            // over shoot, we dont have ways to get back. as p is actually 0101 array
            return Max;
        }
        if (dp[i][t] >= 0) {
            return dp[i][t];
        }
        double nopick = p[i] - floor(p[i]) + domin(p, t, i + 1);
        double pick = Max;
        if (ceil(p[i]) > p[i]) {
            pick = ceil(p[i]) - p[i] + domin(p, t - 1, i + 1);
        }
        dp[i][t] = Math.min(nopick, pick);
        return dp[i][t];
    }
}
