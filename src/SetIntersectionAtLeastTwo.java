import base.ArrayUtils;

import java.util.Arrays;

public class SetIntersectionAtLeastTwo {

    // if it's one point solution is pick the last point
    // pick e1=end-1 and e2=end
    // note sort by end first then start desc
    public int intersectionSizeTwo(int[][] a) {
        int n = a.length;
        int p1 = -1;
        int p2 = -1;
        int res = 0;
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            } else {
                return Integer.compare(y[0], x[0]);
            }
        });
        for (int i = 0; i < n; ++i) {
            int v1 = a[i][0];
            int v2 = a[i][1];
            if (v1 <= p1 && v2 >= p2) {
                continue;
            } else if (v1 > p2) {
                p1 = v2 - 1;
                p2 = v2;
                res += 2;
            } else {
                res += 1;
                p1 = p2;
                p2 = v2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //  System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[16,18],[11,18],[15,23],[1,16],[10,16],[6,19],[18,20],[7,19],[10,11],[11,23],[6,7],[23,25],[1,3],[7,12],[1,13],[23,25],[10,22],[23,25],[0,19],[0,13],[7,12],[14,19],[8,17],[7,23],[4,24]]")));
        //System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[1, 3], [1, 4], [2, 5], [3, 5]]")));
        //System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[1, 2], [2, 3], [2, 4], [4, 5]]")));
        System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[8,9],[4,21],[3,19],[5,9],[1,5]]")));
    }
}
