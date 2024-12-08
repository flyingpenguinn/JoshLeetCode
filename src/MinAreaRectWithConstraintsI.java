import java.util.HashMap;
import java.util.Map;

public class MinAreaRectWithConstraintsI {
    public int maxRectangleArea(int[][] a) {
        int n = a.length;
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int x = a[i][0];
            int y = a[i][1];
            m.put(x + "," + y, i);
        }
        int res = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                int x = a[i][0];
                int y = a[i][1];
                int c = a[j][0];
                int d = a[j][1];
                if (x >= c || y >= d) {
                    continue;
                }
                String other1 = x + "," + d;
                Integer oi1 = m.get(other1);
                String other2 = c + "," + y;
                Integer oi2 = m.get(other2);
                if (oi1 != null && oi2 != null) {
                    boolean bad = false;
                    for (int k = 0; k < n; ++k) {
                        if (k == i || k == j || k == oi1 || k == oi2) {
                            continue;
                        }
                        int kx = a[k][0];
                        int ky = a[k][1];
                        if (kx >= x && kx <= c && ky >= y && ky <= d) {
                            bad = true;
                            break;
                        }
                    }

                    if (!bad) {
                        //  System.out.println(i+" "+j+" "+oi1+" "+oi2);
                        int area = (c - x) * (d - y);
                        res = Math.max(res, area);
                    }
                }
            }
        }
        return res;
    }
}
