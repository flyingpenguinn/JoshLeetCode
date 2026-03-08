public class MinOperationsSortString {
    public int minOperations(String s) {
        int n = s.length();
        int mind = 30;
        int maxd = -1;

        char[] c = s.toCharArray();

        if (n == 2 && c[0] > c[1]) {
            return -1;
        }
        boolean sorted = isSorted(c, 0, n - 1);
        if (sorted) {
            return 0;
        }
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - 'a';
            if (cind < mind) {
                mind = cind;

            }
            if (cind > maxd) {
                maxd = cind;

            }
        }
        if (c[0] - 'a' == mind) {
            return 1;
        }
        if (c[n - 1] - 'a' == maxd) {
            return 1;
        }
        if (c[n - 1] - 'a' == mind && c[0] - 'a' == maxd) {

            for (int i = 1; i < n - 1; ++i) {
                if (c[i] - 'a' == mind || c[i] - 'a' == maxd) {
                    return 2;
                }

            }
            return 3;
        }
        return 2;
    }

    private static boolean isSorted(char[] c, int l, int u) {
        boolean sorted = true;
        for (int i = l; i + 1 <= u; ++i) {
            if (c[i] > c[i + 1]) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }
}
