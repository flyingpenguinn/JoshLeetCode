public class MinTotalOperations {
    public int minOperations(int[] a) {
        int n = a.length;
        int last = a[n - 1];
        int res = 0;
        int i = n - 2;
        while (i >= 0 && a[i] == last) {
            --i;
        }
        while (i >= 0) {
            int j = i;
            while (j >= 0 && a[j] == a[i]) {
                --j;
            }
            ++res;
            i = j;
        }
        return res;
    }
}
