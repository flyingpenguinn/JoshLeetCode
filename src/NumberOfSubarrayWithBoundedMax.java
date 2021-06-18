public class NumberOfSubarrayWithBoundedMax {
    // think about each num's contribution when they act as the end of the subarray
    public int numSubarrayBoundedMax(int[] a, int l, int r) {
        int res = 0;
        int i = 0;
        int start = 0;
        int lastgood = -1;
        // after start all <=r
        int n = a.length;
        while (i < n) {
            if (a[i] < l) {
                //
                if (lastgood != -1) {
                    // if < it can be included in any sub as long as we have lastgood in it
                    int con = lastgood - start + 1;
                    res += con;
                }

            } else if (a[i] <= r) {
                // between all <=r>=l
                lastgood = i;
                res += i - start + 1;

            } else {
                start = i + 1;
                lastgood = -1;
            }
            i++;
        }
        return res;
    }
}
