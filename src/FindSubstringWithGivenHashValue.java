public class FindSubstringWithGivenHashValue {
    public String subStrHash(String s, int power, int mod, int k, int hashValue) {
        int n = s.length();
        long cur = 0;
        int start = -1;
        long base = 1;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            long cind = c - 'a' + 1;
            cur = cur * power + cind;
            cur %= mod;
            int tail = i + k - 1;
            if (tail < n) {
                if (cur == hashValue) {
                    start = i;
                }
                int tind = s.charAt(tail) - 'a' + 1;
                cur -= base * tind;
                cur %= mod;
                if (cur < 0) {
                    cur += mod;
                }
            } else {
                base *= power;
                base %= mod;
            }
        }
        return s.substring(start, start + k);
    }
}
