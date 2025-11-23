public class ConcatenateNonzeroDigisAndMultiply {
    public long sumAndMultiply(int n) {
        String sn = String.valueOf(n);
        long nnum = 0;
        long sumdig = 0;
        for (char c : sn.toCharArray()) {
            int cind = c - '0';
            if (cind == 0) {
                continue;
            }
            nnum = nnum * 10 + cind;
            sumdig += cind;
        }
        return nnum * sumdig;
    }
}
