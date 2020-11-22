public class SmallestStringWithAGivenNumericValue {
    // try to make later as big as possible to get a number as small as possible
    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int laterdigits = n - 1 - i;
            int latermax = 26 * laterdigits;
            int diff = k - cur - latermax;
            int cind = Math.max(diff, 1);
            sb.append((char) ('a' + cind - 1));
            cur += cind;
        }
        return sb.toString();
    }
}
