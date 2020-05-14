import base.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class SetIntersectionAtLeastTwo {

    // if it's one point solution is pick the last point
    // if it's this problem of two points we pick end and end-1.first when new end > old start <old end we add new end to the picture
    // note we should process intervals with bigger starting points first!
    public int intersectionSizeTwo(int[][] a) {
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return Integer.compare(o1[1], o2[1]);
                } else {
                    return Integer.compare(o2[0], o1[0]);
                    // reverse for starting point! because we can use a smaller set to cover bigger ones that contains it
                }
            }
        });
        int s1 = a[0][1] - 1;
        int s2 = a[0][1];
        int n = a.length;

        int r = 2;
        for (int i = 1; i < n; i++) {
            int start = a[i][0];
            int end = a[i][1];
            if (start <= s1) {
                // covered by earlier one
                continue;
            } else if (start > s2) {
                // need a new set anyway
                s1 = end - 1;
                s2 = end;
                r += 2;
            } else {
                // start>s1, start<=s2
                // like 5,9 vs 8,10. we normally set to 9,10
                s1 = s2;
                s2 = end;
                r += 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        //  System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[16,18],[11,18],[15,23],[1,16],[10,16],[6,19],[18,20],[7,19],[10,11],[11,23],[6,7],[23,25],[1,3],[7,12],[1,13],[23,25],[10,22],[23,25],[0,19],[0,13],[7,12],[14,19],[8,17],[7,23],[4,24]]")));
        //System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[1, 3], [1, 4], [2, 5], [3, 5]]")));
        //System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[1, 2], [2, 3], [2, 4], [4, 5]]")));
        System.out.println(new SetIntersectionAtLeastTwo().intersectionSizeTwo(ArrayUtils.read("[[8,9],[4,21],[3,19],[5,9],[1,5]]")));
    }
}
