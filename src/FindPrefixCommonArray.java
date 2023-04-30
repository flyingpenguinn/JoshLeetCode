public class FindPrefixCommonArray {
    public int[] findThePrefixCommonArray(int[] a, int[] b) {
        int n = a.length;
        int[] counter = new int[n + 1];
        int[] c = new int[n];
        int common = 0;
        for (int i = 0; i < n; ++i) {
            ++counter[a[i]];
            ++counter[b[i]];
            if (counter[a[i]] == 2) {
                ++common;
            }
            if (a[i] != b[i] && counter[b[i]] == 2) {
                ++common;
            }
            c[i] = common;
        }
        return c;
    }
}
