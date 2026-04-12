public class CountDigitAppearance {
    public int countDigitOccurrences(int[] a, int digit) {
        int res = 0;
        for (int ai : a) {
            String si = String.valueOf(ai);
            for (char c : si.toCharArray()) {
                int cind = c - '0';
                if (cind == digit) {
                    ++res;
                }
            }
        }
        return res;
    }
}
