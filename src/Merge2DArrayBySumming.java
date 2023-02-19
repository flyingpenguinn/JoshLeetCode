import java.util.ArrayList;
import java.util.List;

public class Merge2DArrayBySumming {
    private int MAX = (int) 1e9;

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> a3 = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length || j < nums2.length) {
            int index1 = i == nums1.length ? MAX : nums1[i][0];
            int index2 = j == nums2.length ? MAX : nums2[j][0];
            if (index1 < index2) {
                a3.add(new int[]{index1, nums1[i][1]});
                ++i;
            } else if (index2 < index1) {
                a3.add(new int[]{index2, nums2[j][1]});
                ++j;
            } else {
                a3.add(new int[]{index1, nums1[i][1] + nums2[j][1]});
                ++i;
                ++j;
            }
        }

        int[][] res = new int[a3.size()][2];
        for (i = 0; i < res.length; ++i) {
            res[i][0] = a3.get(i)[0];
            res[i][1] = a3.get(i)[1];
        }
        return res;
    }
}
