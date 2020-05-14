public class SwapAdjacentLrString {
    // L in t are left to L in s, R are right to s. L/R splits the streak of each other
    public boolean canTransform(String s, String t) {
        // do l from the left and r from the right
        int n = s.length();

        int sl = 0;
        int tl = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                sl = 0;
            } else if (s.charAt(i) == 'L') {
                sl++;
            }
            if (t.charAt(i) == 'R') {
                tl = 0;
            } else if (t.charAt(i) == 'L') {
                tl++;
            }
            if (sl > tl) {
                return false;
            }
        }
        if (sl != tl) {
            return false;
        }

        int sr = 0;
        int tr = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'L') {
                sr=0;
            } else if (s.charAt(i) == 'R') {
                sr++;
            }
            if (t.charAt(i) == 'L') {
                tr=0;
            } else if (t.charAt(i) == 'R') {
                tr++;
            }
            if (tr < sr) {
                return false;
            }
        }
        if (sr != tr) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new SwapAdjacentLrString().canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
