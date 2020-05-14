import base.ArrayUtils;

import java.util.*;

/*
LC#335
You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.



Example 1:

┌───┐
│   │
└───┼──>
    │

Input: [2,1,1,2]
Output: true
Example 2:

┌──────┐
│      │
│
│
└────────────>

Input: [1,2,3,4]
Output: false
Example 3:

┌───┐
│   │
└───┼>

Input: [1,1,1,1]
Output: true
 */
public class SelfCrossing {
    /*               i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                    i-3
    case 2 : i-2 ┌────┐i-4
                 └─══>┘i
                 i-1       (i overlapped i-4)

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2

*/
    public boolean isSelfCrossing(int[] a) {
        int n = a.length;
        if (n <= 3) {
            return false;
        }
        LinkedList<int[]> q = new LinkedList<>();
        q.offerLast(new int[]{0, 0});
        q.offerLast(new int[]{0, a[0]});
        q.offerLast(new int[]{-a[1], a[0]});
        q.offerLast(new int[]{-a[1], a[0] - a[2]});
        int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int gone = 0;
        for (int i = 3; i < n; i++) {
            int[] d = dirs[i % 4];
            int[] top = q.peekLast();
            int nx = top[0] + d[0] * a[i];
            int ny = top[1] + d[1] * a[i];
            int[] np = new int[]{nx, ny};
            int[] h2 = q.get(i - gone - 2);
            int[] h3 = q.get(i - gone - 3);
            int[] h4 = i - gone >= 4 ? q.get(i - gone - 4) : null;
            int[] h5 = i - gone >= 5 ? q.get(i - gone - 5) : null;
            if (samedir(top, np, h4, h3, i)) {
                return true;
            }

            if (crossline(top, np, h2, h3, i)) {
                return true;
            }
            if (crossline(top, np, h4, h5, i)) {
                return true;
            }
            q.offerLast(np);
            if (q.size() > 6) {
                q.pollFirst();
                gone++;
            }
        }
        return false;
    }

    boolean samedir(int[] n1, int[] n2, int[] o1, int[] o2, int i) {
        if (o1 == null || o2 == null) {
            return false;
        }
        if (i % 2 == 0) {
            return same(n1, n2, o1, o2, 0);
        } else {
            return same(n1, n2, o1, o2, 1);
        }
    }

    boolean same(int[] n1, int[] n2, int[] o1, int[] o2, int b) {
        if (n1[b] != o1[b]) {
            return false;
        }
        int fb = b ^ 1;
        int maxn = Math.max(n1[fb], n2[fb]);
        int minn = Math.min(n1[fb], n2[fb]);
        int maxo = Math.max(o1[fb], o2[fb]);
        int mino = Math.min(o1[fb], o2[fb]);
        if (mino > maxn || minn > maxo) {
            return false;
        }
        return true;
    }

    boolean crossline(int[] n1, int[] n2, int[] o1, int[] o2, int i) {
        if (o1 == null || o2 == null) {
            return false;
        }
        if (i % 2 == 0) {
            return cross(n1, n2, o1, o2, 0);
        } else {
            return cross(n1, n2, o1, o2, 1);
        }
    }

    boolean cross(int[] n1, int[] n2, int[] o1, int[] o2, int b) {

        int fb = b ^ 1;
        int maxn = Math.max(n1[fb], n2[fb]);
        int minn = Math.min(n1[fb], n2[fb]);
        int olv = o1[fb];

        if (olv > maxn || olv < minn) {
            return false;
        }
        int maxo = Math.max(o1[b], o2[b]);
        int mino = Math.min(o1[b], o2[b]);
        int nlv = n1[b];
        if (nlv < mino || nlv > maxo) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SelfCrossingSimple().isSelfCrossing(ArrayUtils.read1d("[1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1]")));
        System.out.println(new SelfCrossingSimple().isSelfCrossing(ArrayUtils.read1d("[3,3,3,2,1,1]")));
        System.out.println(new SelfCrossingSimple().isSelfCrossing(ArrayUtils.read1d("[1,1,2,2,1,1]")));
        System.out.println(new SelfCrossingSimple().isSelfCrossing(ArrayUtils.read1d("[2,1,1,2]")));
        System.out.println(new SelfCrossingSimple().isSelfCrossing(ArrayUtils.read1d("[1,1,2,1,1]")));


    }

}

class SelfCrossingSimple {
    public boolean isSelfCrossing(int[] a) {
        for (int i = 3; i < a.length; i++) {
            if (a[i - 1] <= a[i - 3] && a[i] >= a[i - 2]) {
                return true;
            }
            if (i - 4 >= 0 && a[i - 1] == a[i - 3] && a[i] + a[i - 4] >= a[i - 2]) {
                return true;
            }
            if (i - 5 >= 0 && a[i - 4] <= a[i - 2] && a[i - 4] + a[i] >= a[i - 2] && a[i - 1] <= a[i - 3] && a[i - 1] + a[i - 5] >= a[i - 3]) {
                return true;
            }
        }
        return false;
    }
}