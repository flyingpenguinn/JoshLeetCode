import java.util.Arrays;

public class MaxNumOfKSumPairs {
    public int maxOperations(int[] a, int k) {
        Arrays.sort(a);
        int i = 0;
        int j = a.length - 1;
        int res = 0;
        while (i < j) {
            int sum = a[i] + a[j];
            if (sum == k) {
                res++;
                i++;
                j--;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
