public class AbbreviateProductOfRange {
    public String abbreviateProduct(int left, int right) {
        long suff = 1;
        int zeros = 0;
        long maxSuff = 10000000000L;
        double pref = 1.0;
        boolean over = false;
        for (int i = left; i <= right; ++i) {
            pref *= i;
            suff *= i;
            while (pref >= 100000) {
                pref /= 10;
            }
            while (suff % 10 == 0) {
                suff /= 10;
                ++zeros;
            }
            over |= suff >= maxSuff;
            suff %= maxSuff;
        }
        String s = String.valueOf(suff);
        return (!over ? s : (int)pref + "..." + s.substring(s.length() - 5)) + "e" + zeros;
    }
}
