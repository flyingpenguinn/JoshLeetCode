import java.util.Arrays;
import java.util.Comparator;

public class SeparateSquaresII {
    // segment tree
    // TODO do it myself
    public double separateSquares(int[][] a) {
        int n = a.length;
        int m = 2 * n;
        Event[] events = new Event[m];
        double[] xsRaw = new double[m];
        int idx = 0, xIdx = 0;
        for (int[] sq : a) {
            // Each square gives a rectangle [x, x+l] x [y, y+l]
            double x = sq[0], y = sq[1], l = sq[2];
            double x2 = x + l, y2 = y + l;
            events[idx++] = new Event(y, x, x2, 1);
            events[idx++] = new Event(y2, x, x2, -1);
            xsRaw[xIdx++] = x;
            xsRaw[xIdx++] = x2;
        }

        // Sort events by their y-coordinate (they are exact integers in double format)
        Arrays.sort(events, Comparator.comparingDouble(e -> e.y));
        // Compress x-coordinates
        double[] xs = compress(xsRaw);

        // FIRST SWEEP: compute total union area.
        SegmentTree segTree = new SegmentTree(xs);
        double totalUnionArea = 0.0;
        double lastY = events[0].y;
        for (int i = 0; i < m; ) {
            double curY = events[i].y;
            if (curY > lastY) {
                double unionX = segTree.query();
                totalUnionArea += unionX * (curY - lastY);
                lastY = curY;
            }
            // Process all events at y == curY
            while (i < m && events[i].y == curY) {
                int lIdx = Arrays.binarySearch(xs, events[i].x1);
                if (lIdx < 0) lIdx = -lIdx - 1;
                int rIdx = Arrays.binarySearch(xs, events[i].x2);
                if (rIdx < 0) rIdx = -rIdx - 1;
                segTree.update(1, 0, xs.length - 1, lIdx, rIdx, events[i].type);
                i++;
            }
        }

        double target = totalUnionArea / 2.0;

        // SECOND SWEEP: find minimal y such that cumulative union area reaches target.
        segTree = new SegmentTree(xs);  // Reinitialize segment tree for a fresh sweep.
        lastY = events[0].y;
        double cumArea = 0.0;
        for (int i = 0; i < m; ) {
            double curY = events[i].y;
            if (curY > lastY) {
                double unionX = segTree.query();
                double dy = curY - lastY;
                if (cumArea + unionX * dy >= target - 1e-10) {
                    // The answer lies in this interval.
                    return lastY + (target - cumArea) / unionX;
                }
                cumArea += unionX * dy;
                lastY = curY;
            }
            while (i < m && events[i].y == curY) {
                int lIdx = Arrays.binarySearch(xs, events[i].x1);
                if (lIdx < 0) lIdx = -lIdx - 1;
                int rIdx = Arrays.binarySearch(xs, events[i].x2);
                if (rIdx < 0) rIdx = -rIdx - 1;
                segTree.update(1, 0, xs.length - 1, lIdx, rIdx, events[i].type);
                i++;
            }
        }
        return lastY;
    }

    // Compress an array of doubles into a sorted array of unique values.
    private double[] compress(double[] arr) {
        Arrays.sort(arr);
        int cnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) cnt++;
        }
        double[] res = new double[cnt];
        res[0] = arr[0];
        int j = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                res[j++] = arr[i];
            }
        }
        return res;
    }

    // Event class for the sweep-line.
    class Event {
        double y, x1, x2;
        int type;  // +1 for adding an interval; -1 for removing.

        Event(double y, double x1, double x2, int type) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.type = type;
        }
    }

    // Segment Tree for maintaining the union length over the x-axis.
    class SegmentTree {
        int n;
        double[] tree;  // Covered length of the segment.
        int[] count;    // Coverage count for the segment.
        double[] xs;    // The compressed x-coordinates.

        SegmentTree(double[] xs) {
            this.xs = xs;
            this.n = xs.length;
            // Allocate 4*n size arrays.
            tree = new double[4 * n];
            count = new int[4 * n];
        }

        // Update the range [ql, qr) with value 'val'.
        // The current node covers indices [l, r) in xs.
        void update(int idx, int l, int r, int ql, int qr, int val) {
            if (qr <= l || ql >= r) return;
            if (ql <= l && r <= qr) {
                count[idx] += val;
            } else {
                int mid = (l + r) >> 1;
                update(idx << 1, l, mid, ql, qr, val);
                update(idx << 1 | 1, mid, r, ql, qr, val);
            }
            if (count[idx] > 0) {
                tree[idx] = xs[r] - xs[l];
            } else if (r - l == 1) {
                tree[idx] = 0;
            } else {
                tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
            }
        }

        // Query the current total union length.
        double query() {
            return tree[1];
        }
    }
}
