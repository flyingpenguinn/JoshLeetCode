public class NumberOfStudentReplacements {
    public int totalReplacements(int[] a) {
        int n = a.length;
        int cur = a[0];
        int res = 0;
        for (int i = 1; i < n; ++i) {
            if (a[i] < cur) {
                cur = a[i];
                ++res;
            }
        }
        return res;
    }
}
