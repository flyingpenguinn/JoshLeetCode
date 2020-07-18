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
                double midx = (a[i][0] + a[j][0]) / 2.0;
                double midy = (a[i][1] + a[j][1]) / 2.0;
                double len = len(a[i], a[j]);
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

    private double len(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }

    public static void main(String[] args) {
        int[][] points = ArrayUtils.read("[[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]");
        System.out.println(new MinAreaRectII().minAreaFreeRect(points));
    }
}