import base.ArrayUtils;

public class MaxScoreFromGridOperations {
    // dp including or excluding prev column values
    // TODO DIY
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[] prevColW = new long[n + 1]; // [DP] prev col with prev col's score
        long[] prevColWo = new long[n + 1]; // [DP] prev col without prev col's score
        if (n == 1) {
            return 0;
        }

        for (int j = 1; j < n; j++) { // [LOOP] for each col
            long[] curColW = new long[n + 1]; // [DP] cur col with cur col's score
            long[] curColWo = new long[n + 1]; // [DP] cur col without cur col's score
            for (int i = 0; i < n + 1; i++) { // [LOOP] prev col (j-1) is black from row 0 to row i-1 (no black when i == 0)
                long prevColVal = 0; // prev col (j-1) score when prev black is i and cur black is k
                long curColVal = 0; // cur col (j) score when prev black is i and cur black is k
                for (int p = 0; p < i; p++) {
                    curColVal += grid[p][j];
                }
                for (int k = 0; k < n + 1; k++) { // [LOOP] cur col (j) black is from row 0 to row k-1 (no black when k == 0)
                    if (k > 0 && k <= i) {
                        curColVal -= grid[k - 1][j];
                    }
                    if (k > i) {
                        prevColVal += grid[k - 1][j - 1];
                    }
                    curColWo[k] = Math.max(curColWo[k], prevColVal + prevColWo[i]);
                    curColWo[k] = Math.max(curColWo[k], prevColW[i]);
                    curColW[k] = Math.max(curColW[k], curColVal + prevColW[i]);
                    curColW[k] = Math.max(curColW[k], curColVal + prevColVal + prevColWo[i]);
                }
            }
            prevColW = curColW;
            prevColWo = curColWo;
        }
        long maxScore = 0;
        for (long score : prevColW) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }


    public static void main(String[] args) {
        System.out.println(new MaxScoreFromGridOperations().maximumScore(ArrayUtils.read("[[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]")));
    }
}
