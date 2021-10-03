public class MinMoveToConvertString {

    public int minimumMoves(String s) {
        return solve(s, 0);
    }

    private int solve(String s, int i) {
        int n = s.length();
        if (i >= n) {
            return 0;
        }
        if (s.charAt(i) == 'X') {
            return 1 + solve(s, i + 3);
        } else {
            return solve(s, i + 1);
        }
    }
}
