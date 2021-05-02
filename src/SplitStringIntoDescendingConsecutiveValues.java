public class SplitStringIntoDescendingConsecutiveValues {
    // O(N ^ 2). even though we use back-tracking here,
    // there is only one chance to go into next dfs function, so it is linear complexity O(N) for every iteration. so it is O(N ^ 2),
    public boolean splitString(String s) {
        return solve(s, 0, -1L, 0);
    }

    // must have splitted twice...
    private boolean solve(String s, int i, long pre, int sp) {
        int n = s.length();
        if (i == n) {
            return sp >= 2;
        }
        long cur = 0;
        for (int j = i; j < n; j++) {
            int cind = s.charAt(j) - '0';
            cur = cur * 10 + cind; // if it overflows then pre-cur ==1 won't be true anyway...
            if (pre == -1 || pre - cur == 1) {
                boolean later = solve(s, j + 1, cur, sp + 1);
                if (later) {
                    //   System.out.println(i+" "+cur+" "+pre);
                    return true;
                }
            }
        }
        return false;
    }
}
