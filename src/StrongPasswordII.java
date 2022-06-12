public class StrongPasswordII {
    private String specials = "!@#$%^&*()-+";

    public boolean strongPasswordCheckerII(String s) {
        int n = s.length();
        if (n < 8) {
            return false;
        }
        int lower = 0;
        int upper = 0;
        int dig = 0;
        int sp = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                ++lower;
            }
            if (Character.isUpperCase(c)) {
                ++upper;
            }
            if (Character.isDigit(c)) {
                ++dig;
            }
            if (specials.indexOf(c) != -1) {
                ++sp;
            }
            if (i - 1 >= 0) {
                char lc = s.charAt(i - 1);
                if (lc == c) {
                    return false;
                }
            }
        }

        return lower >= 1 && upper >= 1 && dig >= 1 && sp >= 1;

    }
}
