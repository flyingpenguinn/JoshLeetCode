import java.util.*;

public class AvgHeightOfBuildingInSegment {
    // similar to meeting room 2. but here we need to output each segment
    public int[][] averageHeightOfBuildings(int[][] a) {
        Map<Integer, Integer> h = new HashMap<>();
        Map<Integer, Integer> c = new HashMap<>();
        for (int[] ai : a) {
            int s = ai[0];
            int e = ai[1];
            h.put(s, h.getOrDefault(s, 0) + ai[2]);
            h.put(e, h.getOrDefault(e, 0) - ai[2]);
            c.put(s, c.getOrDefault(s, 0) + 1);
            c.put(e, c.getOrDefault(e, 0) - 1);
        }
        int ch = 0;
        int cc = 0;
        int lastlen = 0;
        int lastx = -1;
        List<int[]> res = new ArrayList<>();
        for (int x : h.keySet()) {
            int hdelta = h.get(x);
            int itemdelta = c.getOrDefault(x, 0);
            ch += hdelta;
            cc += itemdelta;
            int cur = cc == 0 ? 0 : ch / cc;
            if (cur != lastlen) {
                if (lastlen > 0) {
                    res.add(new int[]{lastx, x, lastlen});
                }
                lastlen = cur;
                lastx = x;
            }
        }
        int[][] rr = new int[res.size()][3];
        for (int i = 0; i < res.size(); ++i) {
            for (int j = 0; j < 3; ++j) {
                rr[i][j] = res.get(i)[j];
            }
        }
        return rr;
    }
}
