import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MaxWinFromTwoSegments {
    public int maximizeWin(int[] a, int k) {
        int n = a.length;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        //  System.out.println(m);
        int accu = 0;
        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer, Integer> am = new HashMap<>();
        int maxsingle = 0;
        int res = 0;
        for (int i : m.keySet()) {
            accu += m.get(i);
            am.put(i, accu);
            Integer last = m.lowerKey(i - k);
            int cur = 0;
            if (last == null) {
                cur = accu;
            } else {
                cur = accu - am.get(last);
            }
            maxsingle = Math.max(maxsingle, cur);
            dp.put(i, maxsingle);
            int before = last == null ? 0 : dp.get(last);
            int cmax = cur + before;
            //  System.out.println("i="+i+" cur="+cur+" cmax="+cmax+" before="+before);
            res = Math.max(cmax, res);
        }
        return res;
    }

    /*
    similar thinking process but we can simplify

    public int maximizeWin(int[] A, int k) {
        int res = 0, n = A.length, j = 0, dp[] = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            while (A[j] < A[i] - k)
                ++j;
            dp[i + 1] = Math.max(dp[i], i - j + 1);
            res = Math.max(res, i - j + 1 + dp[j]);
        }
        return res;
    }


     */
}
