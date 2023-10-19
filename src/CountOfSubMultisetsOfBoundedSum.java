import java.util.*;

public class CountOfSubMultisetsOfBoundedSum {
    // cv = a[i]
    // dp[i][j] = dp[i+1][j+0*cv] + dp[i+1][j+1*cv...] + dp[i+1][j+k*cv]
    private List<Integer> na = new ArrayList<>();
    private Map<Integer, Integer> g = new HashMap<>();
    private long Mod = (long) (1e9 + 7);

    public int countSubMultisets(List<Integer> a, int l, int r) {
        int n = a.size();
        Collections.sort(a);
        int i = 0;
        int zeros = 0;
        while (i < n) {
            if (a.get(i).equals(0)) {
                ++zeros;
                ++i;
                continue;
            }
            int j = i;
            while (j < n && a.get(j).equals(a.get(i))) {
                ++j;
            }
            int c = j - i;
            g.put(a.get(i), c);
            na.add(a.get(i));
            i = j;
        }
        int nn = na.size();
        long[][] dp = new long[nn + 1][r + 1];
        long[] accu = new long[r + 1];

        for (i = nn; i >= 0; --i) {
            long[] naccu = new long[r + 1];
            int pv = i > 0 ? na.get(i - 1) : -1;
            int cpv = pv == -1 ? 0 : g.get(pv) + 1;
            // note we are +1 here
            for (int j = r; j >= 0; --j) {
                if (i == nn) {
                    dp[i][j] = j >= l ? 1 : 0;
                } else {
                    dp[i][j] = accu[j];
                }
                if (pv != -1) {
                    naccu[j] = dp[i][j];
                    naccu[j] += (j + pv <= r) ? naccu[j + pv] : 0;
                    naccu[j] %= Mod;
                }

            }
            if (pv == -1) {
                continue;
            }
            for (int j = r; j >= 0; --j) {
                // we need 0... cpv-1
                final long prev = (j + cpv * pv <= r) ? naccu[j + cpv * pv] : 0;
                accu[j] = naccu[j] - prev;
                accu[j] %= Mod;
                if (accu[j] < 0) {
                    accu[j] += Mod;
                }
            }
        }
        long rr = dp[0][0];
        // zeros will be handled separately
        rr *= (zeros + 1);
        rr %= Mod;
        return (int) rr;
    }

    public static void main(String[] args) {
        System.out.println(new CountOfSubMultisetsOfBoundedSum().countSubMultisets(new ArrayList<>(List.of(160, 160)), 0, 360));
    }
}
