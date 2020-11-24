import java.math.BigDecimal;
import java.util.*;

public class ValidSquare {
    // 1234, 1243, 1324, 1342, 1423, 1432
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return issquare(p1, p2, p3, p4) || issquare(p1, p2, p4, p3) || issquare(p1, p3, p2, p4) || issquare(p1, p3, p4, p2) || issquare(p1, p4, p2, p3) || issquare(p1, p4, p3, p2);
    }

    boolean issquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d1 = dist(p1, p2);
        int d2 = dist(p2, p3);
        int d3 = dist(p3, p4);
        int d4 = dist(p4, p1);
        if (d1 == d2 && d2 == d3 && d3 == d4 && d1 != 0) {
            // !=0 due to edge >0
            if (rightangle(p1, p2, p3, p4)) {
                // only need to check one right angle
                return true;
            }
        }
        return false;
    }

    int dist(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }

    boolean rightangle(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] v1 = new int[]{p2[0] - p1[0], p2[1] - p1[1]};
        int[] v2 = new int[]{p3[0] - p2[0], p3[1] - p2[1]};
        return v1[0] * v2[0] + v1[1] * v2[1] == 0;
    }


    public static void main(String[] args) {
        int[] p1 = {0, 0};
        int[] p2 = {0, 1};
        int[] p3 = {1, 0};
        int[] p4 = {1, 1};
        System.out.println(new ValidSquare().validSquare(p1, p2, p3, p4));
    }
}

class ValidSquareFormulaic {
    // similar to min area rectangle II. unlike that one, we need to take care of area ==0 here...
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] ps = {p1, p2, p3, p4};
        Map<String, List<int[]>> m = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int mx = ps[i][0] + ps[j][0];
                int my = ps[i][1] + ps[j][1];
                int bilen = dist(ps[i], ps[j]);
                if (bilen == 0) {
                    continue;
                }
                String key = mx + "," + my + "," + bilen;
                m.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        for (String k : m.keySet()) {
            List<int[]> points = m.get(k);
            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    int[] pair1 = points.get(i);
                    int[] pair2 = points.get(j);
                    int dist1 = dist(ps[pair1[0]], ps[pair2[0]]);
                    int dist2 = dist(ps[pair1[0]], ps[pair2[1]]);
                    if (dist1 == dist2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int dist(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }
}
