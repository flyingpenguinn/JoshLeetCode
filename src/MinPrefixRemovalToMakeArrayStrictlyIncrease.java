public class MinPrefixRemovalToMakeArrayStrictlyIncrease {
    public int minimumPrefixLength(int[] a) {
        int n = a.length;
        int i = n - 1;
        while (i - 1 >= 0 && a[i] > a[i - 1]) {
            --i;
        }
        return i;
    }
}
