public class CheckAdjacentDigitDiffs {
    public boolean isAdjacentDiffAtMostTwo(String s) {
        int n = s.length();
        for (int i = 0; i + 1 < n; ++i) {
            int cd = s.charAt(i) - '0';
            int nd = s.charAt(i + 1) - '0';
            if (Math.abs(cd - nd) > 2) {
                return false;
            }
        }
        return true;
    }
}
