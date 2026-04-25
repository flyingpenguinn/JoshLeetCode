public class ValidDigitNumber {
    public boolean validDigit(int n, int x) {
        String str = String.valueOf(n);
        int sn = str.length();
        if (str.charAt(0) - '0' == x) {
            return false;
        }
        for (int i = 1; i < sn; ++i) {
            if (str.charAt(i) - '0' == x) {
                return true;
            }
        }
        return false;
    }
}
