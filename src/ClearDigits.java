public class ClearDigits {
    public String clearDigits(String s) {
        return solve(s);
    }

    private String solve(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }
        int pos = -1;
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return s;
        }
        String rem = s.substring(0, pos - 1) + s.substring(pos + 1);
        return solve(rem);
    }
}
