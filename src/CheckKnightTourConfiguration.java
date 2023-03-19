public class CheckKnightTourConfiguration {
    public boolean checkValidGrid(int[][] a) {
        int n = a.length;
        if (a[0][0] != 0) {
            return false;
        }
        int[][] moves = new int[n * n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int index = a[i][j];
                moves[index] = new int[]{i, j};
            }
        }
        for (int i = 0; i + 1 < moves.length; ++i) {
            int pr = moves[i][0];
            int pc = moves[i][1];
            int cr = moves[i + 1][0];
            int cc = moves[i + 1][1];
            if (Math.abs(pr - cr) == 1 && Math.abs(pc - cc) == 2) {
                continue;
            } else if (Math.abs(pr - cr) == 2 && Math.abs(pc - cc) == 1) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
