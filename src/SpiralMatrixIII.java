public class SpiralMatrixIII {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // each step we use twice: 1,1,2,2,3,3,....
    public int[][] spiralMatrixIII(int rows, int cols, int r0, int c0) {
        int[][] res = new int[rows * cols][2];
        int i = r0;
        int j = c0;
        int steps = 1; // how long to walk before a turn
        int stepsc = 2;// how many times remaining to walk the same len
        int steped = 0; // how many steps we went from last turn
        int dindex = 0;
        int val = 1;
        int rp = 0;
        while (true) {
            // System.out.println(i+","+j+" "+dindex+" "+steps+" "+steped);
            if (i >= 0 && i < rows && j >= 0 && j < cols) {
                res[rp][0] = i;
                res[rp][1] = j;
                rp++;
                val++;
                if (val == rows * cols + 1) {
                    break;
                }
            }
            if (steped == steps) {
                steped = 0;
                dindex = (dindex + 1) % 4;
                stepsc--;
                if (stepsc == 0) {
                    steps++;
                    stepsc = 2;
                }
            }
            i += dirs[dindex][0];
            j += dirs[dindex][1];
            steped++;
        }


        return res;
    }
}
