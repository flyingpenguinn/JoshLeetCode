import java.util.TreeMap;

public class BrightestPositionOnTheStreet {
    public int brightestPosition(int[][] lights) {

        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (var l : lights) {
            int start = l[0] - l[1];
            int end = l[0] + l[1];
            update(m, start, 1);
            update(m, end + 1, -1);
        }
        int csum = 0;
        int res = 0;
        int resp = -1;
        for (int key : m.keySet()) {
            int val = m.get(key);
            csum += val;
            if (csum > res) {
                res = csum;
                resp = key;
            }
        }
        return resp;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv == 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
