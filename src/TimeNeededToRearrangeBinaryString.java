public class TimeNeededToRearrangeBinaryString {
    public int secondsToRemoveOccurrences(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int zeros = 0;
        int prev = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            zeros += sc[i] == '0' ? 1 : 0;
            if (sc[i] == '1' && zeros > 0) {
                int cur = Math.max(prev + 1, zeros);
                res = Math.max(res, cur);
                prev = cur;
            }
        }
        return res;
    }
}
