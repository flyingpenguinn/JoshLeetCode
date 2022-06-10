import java.util.Arrays;

public class PartitionArraySoThatMaxDifFk {
    public int partitionArray(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int i = 0;
        int res = 1;
        while (i < n) {
            int start = a[i];
            int end = start + k;
            int j = i + 1;
            while (j < n && a[j] <= end) {
                ++j;
            }
            if (j < n) {
                ++res;
            }
            i = j;
        }
        return res;
    }
}
