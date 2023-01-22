public class AlternatingDigitSum {
    public int alternateDigitSum(int n) {
        String str = String.valueOf(n);
        int sn = str.length();
        int sign = 1;
        int res = 0;
        for (int i = 0; i < sn; ++i) {
            int cind = str.charAt(i) - '0';
            res += cind * sign;
            sign = sign == 1 ? -1 : 1;
        }
        return res;
    }
}
