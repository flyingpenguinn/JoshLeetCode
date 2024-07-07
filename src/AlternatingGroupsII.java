public class AlternatingGroupsII {
    public int numberOfAlternatingGroups(int[] a, int k) {
        int n = a.length;
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n + k - 1 && a[j % n] != a[(j - 1) % n]) {
                ++j;
            }
            // from i to j-1 good
            // System.out.println(i+"..."+(j-1));
            int len = j - i;
            if (len >= k) {
                int count = len - k + 1;
                res += count;
            }
            i = j;
        }
        return res;
    }
}
