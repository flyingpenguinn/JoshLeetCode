public class ConstructLexiLargestSeq {
    // plain dfs, could be n!, but in reality converges fast
    private int[] res;

    public int[] constructDistancedSequence(int n) {
        res = new int[2 * n - 1];
        dfs(res, 0, 0, n);
        return res;
    }

    private boolean dfs(int[] res, int st, int i, int n) {
        //  System.out.println(Arrays.toString(res)+" "+st+" "+i);
        if (st + 2 == (1 << (n + 1))) {
            // 1110 is the number we are shooting for when n = 3. note we start from 1th positon 0th is emptied
            return true;
        }
        if (i == res.length) {
            return false;
        }

        if (res[i] != 0) {
            return dfs(res, st, i + 1, n);
        }
        for (int j = n; j >= 1; j--) {
            if (j == 1 && ((st >> j) & 1) == 0) {
                res[i] = 1;
                int nst = st | (1 << j);
                boolean later = dfs(res, nst, i + 1, n);
                if (later) {
                    return true;
                } else {
                    res[i] = 0;
                }
            } else if (j > 1 && ((st >> j) & 1) == 0 && i + j < res.length && res[i + j] == 0) {
                res[i] = j;
                res[i + j] = j;
                int nst = st | (1 << j);
                boolean later = dfs(res, nst, i + 1, n);
                if (later) {
                    return true;
                } else {
                    res[i] = 0;
                    res[i + j] = 0;
                }
            }
        }
        return false;
    }
}
