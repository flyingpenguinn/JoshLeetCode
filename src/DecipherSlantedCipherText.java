public class DecipherSlantedCipherText {
    // drop spaces on the right, but not on the left
    public String decodeCiphertext(String s, int rows) {
        int i = 0;
        int sj = 0;
        int j = sj;
        int cols = s.length() / rows;
        StringBuilder res = new StringBuilder();
        while (j < cols) {
            int index = i * cols + j;
            res.append(s.charAt(index));
            ++i;
            ++j;
            if (i == rows || j == cols) {
                i = 0;
                j = sj + 1;
                ++sj;
            }
        }
        int len = res.length();
        while (len > 0 && res.charAt(len - 1) == ' ') {
            --len;
        }
        return res.substring(0, len).toString();
    }
}
