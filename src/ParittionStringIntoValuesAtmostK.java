public class ParittionStringIntoValuesAtmostK {
    public int minimumPartition(String s, int k) {
        int res = 0;
        int i = 0;
        int n = s.length();
        while (i < n) {
            int j = i;
            long cur = 0;
            while (j < n && (cur * 10 + (s.charAt(j) - '0')) <= k) {
                cur = cur * 10 + (s.charAt(j) - '0');
                ++j;
            }
            if (i == j) {
                return -1;
            }
            ++res;
            i = j;
        }
        return res;
    }
}
