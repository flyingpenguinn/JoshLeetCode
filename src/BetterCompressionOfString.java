public class BetterCompressionOfString {
    public String betterCompression(String s) {
        int n = s.length();
        int[] count = new int[26];
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = i + 1;
            int len = 0;
            while (j < n && Character.isDigit(s.charAt(j))) {
                len = len * 10 + (s.charAt(j) - '0');
                ++j;
            }
            count[c - 'a'] += len;
            i = j;
        }
        StringBuilder res = new StringBuilder();
        for (i = 0; i < 26; ++i) {
            if (count[i] == 0) {
                continue;
            }
            res.append((char)('a' + i));
            res.append(count[i]);
        }
        return res.toString();
    }
}
