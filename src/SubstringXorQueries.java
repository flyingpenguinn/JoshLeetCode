import java.util.HashMap;
import java.util.Map;

public class SubstringXorQueries {
    // at most len = 32 substring in s. traverse them all
    public int[][] substringXorQueries(String s, int[][] queries) {
        int qn = queries.length;
        int[][] res = new int[qn][2];
        int sn = s.length();
        Map<Integer, int[]> m = new HashMap<>();
        for (int len = 1; len <= 31; ++len) {
            int cur = 0;
            for (int i = 0; i < sn; ++i) {
                int cind = s.charAt(i) - '0';
                cur = cur * 2 + cind;
                //   System.out.println("i="+i+" len="+len+" cur="+cur);
                int head = i - len + 1;
                if (head >= 0) {
                    m.putIfAbsent(cur, new int[]{head, i});
                    int headv = s.charAt(head) - '0';
                    if (headv == 1) {
                        cur -= (1 << (len - 1));
                    }
                }
            }
        }
        for (int i = 0; i < qn; ++i) {
            int[] q = queries[i];
            int v1 = q[0];
            int v2 = q[1];
            int t = v1 ^ v2;
            res[i] = m.getOrDefault(t, new int[]{-1, -1});
        }
        return res;
    }
}
