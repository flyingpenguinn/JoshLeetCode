public class AlternatingGroupsI {
    public int numberOfAlternatingGroups(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[(i + 1) % n] != a[i] && a[(i + 1) % n] != a[(i + 2) % n]) {
                ++res;
            }
        }
        return res;
    }
}
