import base.ArrayUtils;

import java.util.Arrays;

public class MinTimeToEatAllGrains {
    private int MAX = (int) 2e9;

    public int minimumTime(int[] hens, int[] grains) {
        Arrays.sort(hens);
        Arrays.sort(grains);
        int l = 0;
        int u = MAX;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(hens, grains, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean good(int[] a, int[] b, int t) {
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (b[j] < a[i] && a[i] - b[j] > t) {
                // can't go back and get it
                return false;
            }
            if (b[j] > a[i] && b[j] - a[i] > t) {
                ++i;
                continue;
            }
            if (b[j] >= a[i]) {
                while (j < b.length && b[j] - a[i] <= t) {
                    ++j;
                }
                ++i;
                continue;
            } else {
                int first = b[j];
                // b[j] < a[i]
                while (j < b.length && b[j] <= a[i]) {
                    ++j;
                }
                // now j==b.length or b[j]>a[i]
                if (j == b.length) {
                    return true;
                }
                while (j < b.length) {
                    // have to choose between go to right most (b[j]) first or go to leftmost first
                    int way1 = b[j] - a[i] + b[j] - first;
                    int way2 = 2 * (a[i] - first) + b[j] - a[i];
                    int min = Math.min(way1, way2);
                    if (min <= t) {
                        ++j;
                    } else {
                        ++i;
                        break;
                    }
                }
            }
        }
        return j == b.length;
    }


    public static void main(String[] args) {
        System.out.println(new MinTimeToEatAllGrains().minimumTime(ArrayUtils.read1d("[8]"), ArrayUtils.read1d("[9,0,0,7]")));
    }
}
