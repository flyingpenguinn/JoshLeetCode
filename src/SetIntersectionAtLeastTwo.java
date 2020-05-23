import base.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class SetIntersectionAtLeastTwo {

    // if it's one point solution is pick the last point
    // pick e1=end-1 and e2=end
    // if >end, add two more
    // if <=end-1, nothing
    // otherwise tricky part: we make the old e2 to e1, and current end becomes e2. this can make e1==e2, so in this case e1=e2-1
    public int intersectionSizeTwo(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]));
        int e1 = a[0][1] - 1;
        int e2 = a[0][1];
        int r = 2;
        for (int i = 1; i < a.length; i++) {
            if (a[i][0] > e2) {
                e1 = a[i][1] - 1;
                e2 = a[i][1];
                r += 2;
            } else if (a[i][0] <= e1) {
                // do nothing, included
            } else {
                //a[i][0] > e1 && a[i][0] <= e2
                e1 = e2;
                e2 = a[i][1];
                r++;
                if (e1 == e2) {
                    // same e2 appearing with a later starting time...
                    // for example, [[1, 5],[5,9],[6,9]]
                    e1 = e2 - 1;
                }
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
