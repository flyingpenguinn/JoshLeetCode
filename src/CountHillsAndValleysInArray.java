public class CountHillsAndValleysInArray {
    public int countHillValley(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i - 1 >= 0 && a[i] == a[i - 1]) {
                continue;
            }
            int j = i - 1;
            while (j >= 0 && a[j] == a[i]) {
                --j;
            }
            int k = i + 1;
            while (k < n && a[k] == a[i]) {
                ++k;
            }
            if (j >= 0 && k < n) {
                if (a[j] > a[i] && a[k] > a[i]) {
                    ++res;
                } else if (a[j] < a[i] && a[k] < a[i]) {
                    ++res;
                }
            }
        }
        return res;
    }
}
