import base.ArrayUtils;

import java.util.*;

public class MinAreaRectII {
    // to be a rectangle: diagonal must be of the same len, and biset each other
    // put their mid point and length into a map as key. any two pairs in the value list can form rectangle. check their area
    // all use double because int may overflow in squares
    public double minAreaFreeRect(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int n = a.length;
        Map<String, List<int[]>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int midx = (a[i][0] + a[j][0]);
                int midy = (a[i][1] + a[j][1]);
                long len = len(a[i], a[j]);
                String key = "" + midx + "," + midy + "," + len;
                m.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        double min = Integer.MAX_VALUE;
        for (String k : m.keySet()) {
            // any pairs that share the same key can form a rectangle
            List<int[]> list = m.get(k);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    int[] pair1 = list.get(i);
                    int[] pair2 = list.get(j);
                    double len1 = len(a[pair1[0]], a[pair2[0]]); // from one pair to one of the other pair
                    double len2 = len(a[pair1[0]], a[pair2[1]]); // and to the other one in the other pair
                    double area = Math.sqrt(len1 * len2);
                    min = Math.min(min, area);
                }
            }
        }
        return min >= Integer.MAX_VALUE ? 0.0 : min;
    }

    private long len(int[] x, int[] y) {
        long dx = x[0] - y[0];
        long dy = x[1] - y[1];
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) {
        int[][] points = ArrayUtils.read("[[21096,38753],[2304,26303],[3969,29792],[33983,32544],[15376,38207],[15512,38241],[1232,20799],[37696,13697],[30465,4400],[20799,38768],[8100,34535],[38785,20000],[1241,20988],[38207,24624],[1300,21785],[38768,20799],[38241,24488],[18215,38700],[1793,24624],[28127,3064],[36575,28840],[33983,7456],[4340,30375],[37340,12775],[26201,2268],[27225,37340],[3816,29537],[1759,24488],[11873,36936],[37017,12044],[28840,36575],[12044,37017],[4972,31271],[38753,21096],[35028,31271],[7456,33983],[38700,21785],[36936,11873],[36031,29792],[35028,8729],[20000,38785],[1247,21096],[30375,35660],[20799,1232],[36184,29537],[1575,23660],[20000,1215],[18904,38753],[34535,31900],[29792,36031]]");
        System.out.println(new MinAreaRectIIBruteForce().minAreaFreeRect(points));
    }
}

class MinAreaRectIIBruteForce {
    // not enough to only have 1 right angle!
    public double minAreaFreeRect(int[][] a) {
        int n = a.length;
        double res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!rightangle(a, i, j, j, k)) {
                        continue;
                    }
                    for (int l = 0; l < n; l++) {
                        if (i == j || i == k || i == l || j == k || j == l || k == l) {
                            continue;
                        }
                        // i, j
                        // l, k
                        if (dist(a, i, j) == dist(a, k, l) && dist(a, j, k) == dist(a, i, l)) {
                            long d1 = dist(a, i, j);
                            long d2 = dist(a, j, k);
                            double cur = Math.sqrt(1.0 * d1 * d2);
                            res = Math.min(res, cur);
                        }
                    }
                }
            }
        }
        return res >= Integer.MAX_VALUE ? 0 : res;
    }

    private long dist(int[][] a, int i, int j) {
        long dy = a[j][1] - a[i][1];
        long dx = a[j][0] - a[i][0];
        return dx * dx + dy * dy;
    }

    private boolean rightangle(int[][] a, int i, int j, int k, int l) {
        long v1x = a[j][0] - a[i][0];
        long v1y = a[j][1] - a[i][1];
        long v2x = a[l][0] - a[k][0];
        long v2y = a[l][1] - a[k][1];
        return v1x * v2x + v1y * v2y == 0;
    }
}