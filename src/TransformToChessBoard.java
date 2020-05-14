import base.ArrayUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class TransformToChessBoard {
    int MaxValue = 10000;

    // row and column are independently checked
    // after validation, only need to check first column of each row and first row of each col. instead of searching we argue that
    // it requires 1 move to put sth in position
    int rows = 0;
    int cols = 0;


    public int movesToChessboard(int[][] b) {
        boolean fail = checkRows(b);
        if (fail) {
            return -1;
        }
        fail = checkCols(b);
        if (fail) {
            return -1;
        }
        int rr = Math.min(swaps(b, rows, 0, true), swaps(b, rows, 1, true));
        int cr = Math.min(swaps(b, cols, 0, false), swaps(b, cols, 1, false));
        int rt = rr + cr;
        //  System.out.println(count);
        // /2 as we double counted
        return rt >= MaxValue ? -1 : rt / 2;
    }

    // need one swap to correct value
    private int swaps(int[][] b, int ones, int should, boolean isRow) {
        // System.out.println("trying..."+should);
        int len = isRow ? b.length : b[0].length;
        int zeros = len - ones;
        // no hope...only check twice when one==zero
        if (zeros < ones && should == 0) {
            return MaxValue;
        }
        if (zeros > ones && should == 1) {
            return MaxValue;
        }

        int r = 0;
        if (isRow) {
            for (int i = 0; i < b.length; i++) {
                if (b[i][0] != should) {
                    //System.out.println("wrong row "+i+" "+b[i][0]+" vs "+should);
                    r++;
                }
                should = should == 1 ? 0 : 1;
            }
        } else {
            for (int i = 0; i < b[0].length; i++) {
                if (b[0][i] != should) {
                    //System.out.println("wrong col "+i+" "+b[0][i]+" vs "+should);

                    r++;
                }
                should = should == 1 ? 0 : 1;
            }
        }
        return r;
    }


    private boolean checkRows(int[][] b) {
        int[] r1 = null;
        int[] r2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < b.length; i++) {
            int[] row = b[i];
            if (r1 == null || Arrays.equals(row, r1)) {
                r1 = row;
                count1++;
            } else if (r2 == null || Arrays.equals(row, r2)) {
                r2 = row;
                count2++;
            } else {
                return true;
            }
            if (row[0] == 1) {
                rows++;
            }
        }
        if (Math.abs(count1 - count2) > 1) {
            return true;
        }
        return false;
    }


    private boolean checkCols(int[][] b) {
        int[] c1 = null;
        int[] c2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int j = 0; j < b[0].length; j++) {
            int[] col = new int[b.length];
            for (int i = 0; i < b.length; i++) {
                col[i] = b[i][j];
            }
            if (c1 == null || Arrays.equals(c1, col)) {
                c1 = col;
                count1++;
            } else if (c2 == null || Arrays.equals(c2, col)) {
                c2 = col;
                count2++;
            } else {
                return true;
            }
            if (col[0] == 1) {
                cols++;
            }
        }
        if (Math.abs(count1 - count2) > 1) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new TransformToChessBoard().movesToChessboard(ArrayUtils.read("[[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1],[0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1]]")));

    }
}

class TransformDpBitSet {
    int MaxValue = 10000;

    // rows and cols starting with 1
    int rows = 0;
    int cols = 0;
    Map<Integer, Integer>[] dp;


    public int movesToChessboard(int[][] b) {
        boolean fail = checkRows(b);
        if (fail) {
            return -1;
        }
        fail = checkCols(b);
        if (fail) {
            return -1;
        }
        dp = new HashMap[b.length];
        Arrays.fill(dp, new HashMap<>());
        int rr = swaps(b.length, 0, rows);
        dp = new HashMap[b[0].length];
        Arrays.fill(dp, new HashMap<>());
        int cr = swaps(b[0].length, 0, cols);
        int rt = rr + cr;
        //  System.out.println(count);
        return rt >= MaxValue ? -1 : rt;
    }

    int count = 0;

    private int swapAndGetMin(int set, int i, int len, int di) {
        int min = MaxValue;
        for (int j = i + 1; j < len; j++) {
            int ns = set;
            int dj = (set >> j) & 1;
            if (dj != di) {
                if (di == 0) {
                    ns += 1 << i;
                    ns -= 1 << j;
                } else {
                    ns += 1 << j;
                    ns -= 1 << i;
                }
                int cur = 1 + swaps(len, i + 1, ns);
                min = Math.min(min, cur);
            }
        }
        return min;
    }

    private int swaps(int len, int i, int set) {
        if (i == len) {
            return 0;
        }

        Map<Integer, Integer> cm = dp[i];
        Integer cached = cm.get(set);
        if (cached != null) {
            return cached;
        }
        count++;
        int min = MaxValue;
        int di = (set >> i) & 1;
        if (i == 0) {
            // keep the first, or shift with any of the below
            min = swaps(len, i + 1, set);
            if (min != 0) {
                min = Math.min(min, swapAndGetMin(set, i, len, di));
            }
        } else {
            int dim1 = (set >> (i - 1)) & 1;
            if (dim1 == di) {
                min = swapAndGetMin(set, i, len, di);
            } else {
                min = swaps(len, i + 1, set);
            }
        }
        cm.put(set, min);
        dp[i] = cm;
        return min;
    }


    private boolean checkRows(int[][] b) {
        int[] r1 = null;
        int[] r2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < b.length; i++) {
            int[] row = b[i];
            if (r1 == null || Arrays.equals(row, r1)) {
                r1 = row;
                count1++;
            } else if (r2 == null || Arrays.equals(row, r2)) {
                r2 = row;
                count2++;
            } else {
                return true;
            }
            if (row[0] == 1) {
                rows += 1 << i;
            }
        }
        if (Math.abs(count1 - count2) > 1) {
            return true;
        }
        return false;
    }


    private boolean checkCols(int[][] b) {
        int[] c1 = null;
        int[] c2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int j = 0; j < b[0].length; j++) {
            int[] col = new int[b.length];
            for (int i = 0; i < b.length; i++) {
                col[i] = b[i][j];
            }
            if (c1 == null || Arrays.equals(c1, col)) {
                c1 = col;
                count1++;
            } else if (c2 == null || Arrays.equals(c2, col)) {
                c2 = col;
                count2++;
            } else {
                return true;
            }
            if (col[0] == 1) {
                cols += 1 << j;
            }
        }
        if (Math.abs(count1 - count2) > 1) {
            return true;
        }
        return false;
    }

}