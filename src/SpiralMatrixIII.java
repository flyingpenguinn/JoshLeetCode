public class SpiralMatrixIII {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // each step we use twice: 1,1,2,2,3,3,....


    public int[][] spiralMatrixIII(int rows, int cols, int r0, int c0) {
        int[][] res = new int[rows * cols][2];
        int step = 1;
        int cr = r0;
        int cc = c0;
        int cd = 0;
        int er = r0 + 1;
        int ec = c0 + 1;

        res[0] = new int[]{cr, cc};
        int ri = 1;
        while (ri < res.length) {
            int used = 0;
            while (used < 2) {
                int md = cd % 4;
                int remstep = step;
                while (remstep > 0) {

                    cr += dirs[md][0];
                    cc += dirs[md][1];
                    if (cr >= 0 && cr < rows && cc >= 0 && cc < cols) {
                        res[ri++] = new int[]{cr, cc};
                    }
                    --remstep;
                }
                ++cd;
                remstep = step;
                ++used;
                if (cr == er && cc == ec) {
                    break;
                }
            }
            ++step;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SpiralMatrixIII().spiralMatrixIII(1, 4, 0, 0));
    }
}
