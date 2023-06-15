import base.ArrayUtils;

import java.util.*;

public class MaxSumQueries {

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums1[i];
            arr[i][1] = nums2[i];
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));
        int qn = queries.length;
        int[][] qs = new int[qn][3];

        for(int i = 0; i< qn; ++i){
            qs[i][0] = queries[i][0];
            qs[i][1] = queries[i][1];
            qs[i][2] = i;
        }
        Arrays.sort(qs, (a, b) -> Integer.compare(b[0], a[0]));

        TreeMap<Integer, Integer> ysum = new TreeMap<>();
        int p = 0;
        int[] res = new int[qn]; // p for arr
        Arrays.fill(res, -1);
        for (int i = 0; i < qn; i++) {
            int[] q = qs[i];

            // Only add pair that x >= q[0]
            while (p < n && arr[p][0] >= q[0]) {
                int x = arr[p][0];
                int y = arr[p][1];
                int sum = x + y;
                update(ysum, y, sum);
                ++p;
            }

            // query
            // All Xs are good, find proper y.
            Integer key = ysum.ceilingKey(q[1]);
            if (key == null) {
                // no >= y
                continue;
            }
            int cur = ysum.get(key);
            res[q[2]] = cur;
        }
        return res;
    }

    // remove all entry that y is smaller and sum is less
    private void update(TreeMap<Integer, Integer> ysum, int y, int sum) {
        Integer key = ysum.ceilingKey(y);

        // Check if a y' that y' >= y has bigger value.
        // do not insert if current answer is good enough
        if (key != null && ysum.get(key) >= sum) {
            return;
        }

        key = ysum.floorKey(y);
        while (key != null && ysum.get(key) <= sum) {
            ysum.remove(key);
            key = ysum.floorKey(y); // find next key
        }

        ysum.put(y, sum);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSumQueries().maximumSumQueries(ArrayUtils.read1d("[3,2,5]"), ArrayUtils.read1d("[2,3,4]"), ArrayUtils.read("[[4,4],[3,2],[1,1]]"))));
    }

}
