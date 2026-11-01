public class MinOperationsToSortWithLockedIndices {
    public int minOperations(int[] a, int[] locked, int k) {
        int n = a.length;
        int inv = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] > a[j]) {
                    ++inv;
                }
            }
        }

        int[] minright = new int[n];
        minright[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            minright[i] = Math.min(minright[i + 1], a[i]);
        }
        int maxleft = -1;
        int unlocks = 0;
        for (int i = 0; i + 1 < n; ++i) {
            maxleft = Math.max(maxleft, a[i]);
            if (maxleft > minright[i + 1]) {
                if (locked[i] == 1) {
                    locked[i] = 0;
                    ++unlocks;
                    --k;
                }
                if(locked[i+1]==1){
                    locked[i+1] = 0;
                    ++unlocks;
                    --k;
                }
                if(k<0){
                    return -1;
                }

            }
        }
        return unlocks + inv;
    }
}
