import base.ArrayUtils;

public class MaxSubarraySumWithOneDeletion {

    // just like buysell stock 2...loop the point of the end of the first half. second half must start with this end +2
    public int maximumSum(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        // max of the sum from the right later to - sum[i]
        int[] maxright = new int[n];
        maxright[n - 1] = sum[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxright[i] = Math.max(maxright[i + 1], sum[i]);
        }
        int max = a[0];
        int min = 0;
        for (int i = 0; i < n; i++) {
            int first = sum[i] - min;
            int second = 0;
            if (i + 2 < n) {
                // the segment start from i+2
                second = maxright[i + 2] - sum[i + 1];
            }
            max = Math.max(max, first);
            max = Math.max(max, first + second);
            min = Math.min(min, sum[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new MaxSubarraySumWithOneDeletion().maximumSum(ArrayUtils.read1d("1,-2,0,3")));
    }

}
