public class CheckIfRectangleCornerIsReacahble {
    // union find all circles make sure they are not touching left + bottom or bottm + upper or something else
    // note all circle centers are within the rectangle
    private static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int size) {
            parent = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            if (parent[p] == p) {
                return p;
            }
            int rt = find(parent[p]);
            parent[p] = rt;
            return rt;
        }

        public void union(int p, int q) {
            int ap = find(p);
            int aq = find(q);
            if (ap == aq) {
                return;
            }
            if (size[ap] >= size[aq]) {
                parent[aq] = ap;
                size[ap] += size[aq];
            } else if (size[ap] < size[aq]) {
                parent[ap] = aq;
                size[aq] += size[ap];
            }
        }
    }

    public boolean canReachCorner(int x, int y, int[][] circles) {
        int n = circles.length;
        UnionFind uf = new UnionFind(n + 4);

        int LEFT_EDGE = n;
        int RIGHT_EDGE = n + 1;
        int BOTTOM_EDGE = n + 2;
        int TOP_EDGE = n + 3;

        for (int i = 0; i < n; i++) {
            int cx = circles[i][0];
            int cy = circles[i][1];
            int r = circles[i][2];

            boolean touchesTop = cy + r >= y;
            boolean touchesBottom = cy - r <= 0;
            boolean touchesLeft = cx - r <= 0;
            boolean touchesRight = cx + r >= x;

            if (touchesTop && touchesRight) return false;
            if (touchesBottom && touchesLeft) return false;

            if (touchesTop) uf.union(i, TOP_EDGE);
            if (touchesBottom) uf.union(i, BOTTOM_EDGE);
            if (touchesLeft) uf.union(i, LEFT_EDGE);
            if (touchesRight) uf.union(i, RIGHT_EDGE);

            for (int j = i + 1; j < n; j++) {
                int[] circle2 = circles[j];
                int cx2 = circle2[0];
                int cy2 = circle2[1];
                int r2 = circle2[2];

                int dx = cx - cx2;
                int dy = cy - cy2;
                int distanceSquared = dx * dx + dy * dy;
                int radiusSum = r + r2;

                if (distanceSquared <= radiusSum * radiusSum) {
                    uf.union(i, j);
                }
            }
        }

        return !(uf.find(TOP_EDGE) == uf.find(RIGHT_EDGE) || uf.find(BOTTOM_EDGE) == uf.find(LEFT_EDGE));
    }

    public static void main(String[] args) {
        CheckIfRectangleCornerIsReacahble sol = new CheckIfRectangleCornerIsReacahble();

        int x1 = 3, y1 = 4;
        int[][] circles1 = {{2, 1, 1}};
        System.out.println(sol.canReachCorner(x1, y1, circles1)); // Output: true

        int x2 = 3, y2 = 3;
        int[][] circles2 = {{1, 1, 2}};
        System.out.println(sol.canReachCorner(x2, y2, circles2)); // Output: false

        int x3 = 3, y3 = 3;
        int[][] circles3 = {{2, 1, 1}, {1, 2, 1}};
        System.out.println(sol.canReachCorner(x3, y3, circles3)); // Output: false
    }
}

