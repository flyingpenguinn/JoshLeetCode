public class MinOperationsToMakeArrayPalindrome {
    public int minimumOperations(int[] a) {
        int n = a.length;
        int i = 0;
        int j = n - 1;
        int res = 0;
        while (i < j) {
            if (a[i] == a[j]) {
                ++i;
                --j;
            } else if (a[i] < a[j]) {
                a[i + 1] += a[i];
                ++i;
                ++res;
            } else {
                a[j - 1] += a[j];
                --j;
                ++res;
            }
        }
        return res;
    }
}
