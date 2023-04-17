public class FindMinDivisibilityScore {
    public int maxDivScore(int[] a, int[] b) {
        int res = -1;
        int rnum = -1;
        for (int bi : b) {
            int bc = 0;
            for (int ai : a) {
                if (ai % bi == 0) {
                    ++bc;
                }
            }
            if (bc > res) {
                res = bc;
                rnum = bi;
            } else if (bc == res) {
                rnum = Math.min(rnum, bi);
            }
        }
        return rnum;
    }
}
