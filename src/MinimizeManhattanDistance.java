import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MinimizeManhattanDistance {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private int minDist(List<int[]> a) {
        int n = a.size();
        TreeMap<Integer, Integer> m1 = new TreeMap<>();
        TreeMap<Integer, Integer> m2 = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int cv1 = a.get(i)[0] + a.get(i)[1];
            int cv2 = a.get(i)[0] - a.get(i)[1];
            update(m1, cv1, 1);
            update(m2, cv2, 1);
        }
        int res = (int) 1e9;
        for (int i = 0; i < n; ++i) {
            int cv1 = a.get(i)[0] + a.get(i)[1];
            int cv2 = a.get(i)[0] - a.get(i)[1];
            update(m1, cv1, -1);
            update(m2, cv2, -1);
            int v1 = m1.lastKey() - m1.firstKey();
            int v2 = m2.lastKey() - m2.firstKey();
            int cur = Math.max(v1, v2);
            res = Math.min(res, cur);
            update(m1, cv1, 1);
            update(m2, cv2, 1);
        }


        return res;
    }

    public int minimumDistance(int[][] a) {
        int n = a.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(new int[]{a[i][0], a[i][1]});
        }
        return minDist(list);
    }

}
