import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinTimeKVirusSpread {
// WA yet...

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private int Max = 1000000000;
    private double EPS = 0.01;

    public int minDayskVariants(int[][] a, int k) {
        int n = a.length;
        long sx = 0;
        long sy = 0;
        for (int[] ai : a) {
            sx += ai[0];
            sy += ai[1];
        }
        long px = sx / n;
        long py = sy / n;
        int l = 1;
        int u = Max;
        while (l <= u) {
            // days
            int mid = l + (u - l) / 2;
            int virus = getvirus(px, py, a, mid);
            if (virus >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // how many virus we can get at most after mid days
    private int getvirus(long px, long py, int[][] a, int mid) {
        int virus = count(px, py, a, mid);
        double len = Max;
        while (len > EPS) {
            boolean found = false;
            for (int dx = -30; dx <=30; dx++) {
                for (int dy = -30; dy <= 30; dy++) {
                    long nx = (long) (px + dx * len);
                    long ny = (long) (py + dy * len);
                    int nvirus = count(nx, ny, a, mid);
                    if (nvirus > virus) {
                        virus = nvirus;
                        px = nx;
                        py = ny;
                        found = true;
                    }
                }
            }
            if (!found) {
                len /= 2.0;
            }
        }
        return virus;
    }

    private int count(long px, long py, int[][] a, long t) {
        int res = 0;
        for (int[] ai : a) {
            long cur =  (Math.abs(px - ai[0]) + Math.abs(py - ai[1]));
            if (cur  <= t) {
                ++res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeKVirusSpread().minDayskVariants(ArrayUtils.read("[[45,78],[34,6],[94,59]]"), 2));
    }
}
