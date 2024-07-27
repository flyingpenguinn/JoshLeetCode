public class MinOperationsToMoveOnesToEnd {
    public int maxOperations(String s) {
        s += "1";
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - '0';
        }
        int i = 0;
        int lastcount = 0;
        int res = 0;
        while (i < n) {
            if (a[i] == 0) {
                ++i;
                continue;
            }
            res += lastcount;
            int j = i;
            while (j < n && a[j] == 1) {
                ++j;
            }
            int oc = j - i;
            lastcount += oc;
            i = j;
        }
        return res;
    }
}
