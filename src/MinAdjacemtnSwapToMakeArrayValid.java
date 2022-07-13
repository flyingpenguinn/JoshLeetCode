public class MinAdjacemtnSwapToMakeArrayValid {
    public int minimumSwaps(int[] a) {
        int n = a.length;
        int min = Integer.MAX_VALUE;
        int max = 0;
        int leftMin = -1;
        int rightMax = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] < min) {
                min = a[i];
                leftMin = i;
            }
            if (a[i] >= max) {
                max = a[i];
                rightMax = i;
            }
        }
        int rightMove = n - 1 - rightMax;
        int leftMove = rightMax < leftMin ? leftMin - 1 : leftMin;
        return rightMove + leftMove;
    }
}
