public class MinOperationsToMakeAlternatingBinaryString {
    // cost of 10 is n-01
    public int minOperations(String s) {
        int r1 = change(s.toCharArray(), '0');
        return Math.min(r1, s.length() - r1);
    }

    private int change(char[] s, char v) {
        int res = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != v) {
                res++;
            }
            v ^= 1;
        }
        return res;
    }
}
