public class CountSubarraysWithMajorityElementII {
    public int countMajoritySubarrays(int[] a, int t) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                if (a[j] == t) {
                    ++cnt;
                }
                int len = j - i + 1;
                if (cnt > len / 2) {
                    ++res;
                }

            }
        }
        return res;
    }
}
