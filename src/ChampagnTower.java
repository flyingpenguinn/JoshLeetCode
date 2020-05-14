public class ChampagnTower {
    class St {
        double hold;
        double ex;

        public St(double h, double e) {
            hold = h;
            ex = e;
        }
    }

    St[][] dp;

    public double champagneTower(int p, int r, int c) {
        dp = new St[r + 1][c + 1];
        return dodp(p, r, c).hold;
    }

    St dodp(int p, int r, int c) {
        // as if 2 neg row flows all and 0 catch their excess
        if (r < 0) {
            return new St(0.0, p);
        }
        if (c < 0 || c > r) {
            return new St(0.0, 0.0);
        }

        if (dp[r][c] != null) {
            return dp[r][c];
        }
        St s1 = dodp(p, r - 1, c - 1);
        St s2 = dodp(p, r - 1, c);
        double got = (s1.ex + s2.ex) / 2.0;
        // System.out.println(r+" "+c+" "+ s1.ex+ " "+s2.ex);
        St res = new St(Math.min(1.0, got), Math.max(got - 1.0, 0));
        dp[r][c] = res;
        return res;

    }
}
