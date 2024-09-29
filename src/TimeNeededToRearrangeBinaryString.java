public class TimeNeededToRearrangeBinaryString {
    // check last 1. every 2 consecutive 1s make waiting +1, every 2 consecutive 0s make waiting -1
    public int secondsToRemoveOccurrences(String is) {
        char[] s = is.toCharArray();
        int n = s.length;
        int zeros = 0;
        int lastone = -1;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '1') {
                lastone = i;
                break;
            }
        }
        if (lastone == -1) {
            return 0;
        }
        int waiting = 0;
        for (int i = 0; i <= lastone; ++i) {
            if (s[i] == '0') {
                ++zeros;
            }
            if (i > 0 && s[i] == '1' && s[i - 1] == '1' && zeros > 0) {
                ++waiting;
            }
            if (i > 0 && s[i] == '0' && s[i - 1] == '0' && waiting > 0) {
                --waiting;
            }
        }
        return zeros + waiting;
    }
}
