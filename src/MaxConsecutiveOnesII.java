public class MaxConsecutiveOnesII {
    // maintain a window of at most k zeros. same as question "max substring with at most k zeros..."
    public int findMaxConsecutiveOnes(int[] a) {
        int n = a.length;
        int zeros = 0;
        int start = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            zeros += a[i] == 0 ? 1 : 0;
            while (zeros > 1) {
                zeros -= a[start] == 0 ? 1 : 0;
                start++;
            }
            // start....i has <=1 zeros
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}

