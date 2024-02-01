interface InfiniteStream {
    public int next();
}

public class FindPatternInfiniteStreamI {
    public int findPattern(InfiniteStream stream, int[] a) {
        int[] lps = computeLPSArray(a);
        int j = 0; // index for a (pattern)
        int i = 0; // index for s (text)
        int cur = stream.next();
        boolean hold = false;
        while (true) {
            if (a[j] == cur) {
                ++j;
                hold = false;
                ++i;
                cur = stream.next();
            }
            if (j == a.length) {
                // found
                return i - a.length;
            } else {

                if (a[j] != cur) {
                    if (j != 0) {
                        j = lps[j - 1];
                    } else {
                        hold = false;
                        ++i;
                        cur = stream.next();
                    }
                }
            }
        }
    }


    private int[] computeLPSArray(int[] a) {
        int[] lps = new int[a.length];
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < a.length) {
            if (a[i] == a[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
