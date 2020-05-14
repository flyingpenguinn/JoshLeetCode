import base.ArrayUtils;

/*
LC#302
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:

Input:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2

Output: 6
 */
public class SmallestRectEnclosingBlack {
    // knowing x and y is significant otherwise we don't know where to go:
    // knowing x as u we can find the first occurence, and knowing x as l we can find the bigger end
    public int minArea(char[][] a, int x, int y) {
        int m = a.length;
        if (m == 0) {
            return 0;
        }
        int n = a[0].length;
        int l = 0;
        int u = x;
        // first row that has 1, or l == x+1
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (hasone(a, mid, true)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int minr = l;
        // last row that has 1, or u == -1
        l = x;
        u = m - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (hasone(a, mid, true)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        int maxr = u;
        // first col that has 1, or l == n
        l = 0;
        u = y;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (hasone(a, mid, false)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int minc = l;
        // last col that has 1, or u == -1
        l = y;
        u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (hasone(a, mid, false)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        int maxc = u;
        //  System.out.println(minr+" "+maxr+" "+minc+" "+maxc);
        return minr == m ? 0 : (maxc - minc + 1) * (maxr - minr + 1);
    }

    boolean hasone(char[][] a, int mid, boolean isrow) {
        if (isrow) {
            for (int i = 0; i < a[0].length; i++) {
                if (a[mid][i] == '1') {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                if (a[i][mid] == '1') {
                    return true;
                }
            }
        }
        return false;
    }
}
