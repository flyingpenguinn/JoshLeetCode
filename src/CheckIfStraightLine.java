public class CheckIfStraightLine {
    public boolean checkStraightLine(int[][] c) {
        if (c[0][0] == c[1][0]) {
            for (int i = 2; i < c.length; i++) {
                if (c[i][0] != c[0][0]) {
                    return false;
                }
            }
            return true;
        }
        int d1 = (c[1][1] - c[0][1]);
        int d2 = (c[1][0] - c[0][0]);
        for (int i = 2; i < c.length; i++) {
            int d3 = (c[i][1] - c[0][1]);
            int d4 = (c[i][0] - c[0][0]);
            if (d1 * d4 != d2 * d3) {
                return false;
            }
        }
        return true;
    }
}
