public class ReverseDegreeOfString {
    public int reverseDegree(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            int rev = 26 - cind;
            res += rev * (i + 1);
        }
        return res;
    }
}
