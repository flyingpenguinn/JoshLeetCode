public class DetectPatternLenMRepeatedKTimes {
    public boolean containsPattern(int[] a, int len, int count) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // i is the starting point of the segment that is good
            boolean bad = false;
            for (int k = 0; k < len; k++) {
                // if len ==k then we have k numbers to compare
                int value = a[i + k];
                for (int j = i; j < count * len + i; j += len) {
                    // their starting points are i, i+len, i+2*len, and so forth
                    if (j + k >= n || value != a[j + k]) {
                        bad = true;
                        break;
                    }
                }
                if (bad) {
                    break;
                }
            }
            if (!bad) {
                return true;
            }
        }
        return false;
    }
}
