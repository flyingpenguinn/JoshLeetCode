public class StringCompressionIII {
    public String compressedString(String word) {
        char[] c = word.toCharArray();
        int n = c.length;
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < n) {
            int j = i;
            char ci = c[i];
            while (j < n && c[j] == c[i] && j - i < 9) {
                ++j;
            }
            int len = j - i;
            res.append(len);
            res.append(ci);
            i = j;
        }
        return res.toString();
    }
}
