public class CheckGoodInteger {
    public boolean checkGoodInteger(int n) {
        String str = String.valueOf(n);
        int dsum = 0;
        int sqsum = 0;
        for (char c : str.toCharArray()) {
            int cind = c - '0';
            dsum += cind;
            sqsum += (cind * cind);
        }
        return sqsum - dsum >= 50;
    }
}
