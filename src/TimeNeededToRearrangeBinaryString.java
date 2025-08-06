public class TimeNeededToRearrangeBinaryString {
    // each 1 must wait for earlier 1 to finish
    public int secondsToRemoveOccurrences(String is) {
        char[] s = is.toCharArray();
        int n = s.length;
        int time = 0;
        int zeros = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '0') {
                ++zeros;
            } else if (zeros > 0) {
                time = Math.max(time + 1, zeros);
            }
        }
        return time;
    }
}
