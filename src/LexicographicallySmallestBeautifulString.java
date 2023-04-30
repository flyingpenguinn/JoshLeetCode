public class LexicographicallySmallestBeautifulString {
    // first find i where we can add 1, then put a,b,c... after it
    private boolean isvalid(char[] a, int i) {
        if (i >= 2 && a[i - 2] == a[i]) {
            return false;
        }
        if (i >= 1 && a[i - 1] == a[i]) {
            return false;
        }
        return true;
    }


    public String smallestBeautifulString(String s, int k) {
        char[] ch = s.toCharArray();
        int i = ch.length - 1;
        while (i >= 0) {
            ++ch[i];
            if (ch[i] - 'a' == k) {
                --i;
            } else if (isvalid(ch, i)) {
                break;
            } else {
                // if invalid we do nothing keep adding ch[i]
            }
        }
        if (i < 0) {
            return "";
        }
        // we have fixed i and anything before i is good. the remaining must be one of a,b,c
        for (int j = i + 1; j < ch.length; j++) {
            for (char c = 'a'; c < 'a' + k; ++c) {
                if (j - 2 >= 0 && ch[j - 2] == c) {
                    continue;
                }
                if (j - 1 >= 0 && ch[j - 1] == c) {
                    continue;
                }
                ch[j] = c;
                break;
            }
        }
        return new String(ch);
    }
}
