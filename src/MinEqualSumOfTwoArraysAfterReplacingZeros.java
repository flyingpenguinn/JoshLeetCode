public class MinEqualSumOfTwoArraysAfterReplacingZeros {
    public long minSum(int[] a, int[] b) {

        long sa = 0;
        long ea = 0;
        for (int ai : a) {
            if (ai == 0) {
                ++ea;
            }
            sa += ai;
        }
        long eb = 0;
        long sb = 0;
        for (int bi : b) {
            if (bi == 0) {
                ++eb;
            }
            sb += bi;
        }
        long esum = Math.max(sa + ea, sb + eb);
        long da = esum - sa;
        long db = esum - sb;
        if (da < ea) {
            return -1;
        }
        if (da > 0 && ea == 0) {
            return -1;
        }
        if (db < eb) {
            return -1;
        }
        if (db > 0 && eb == 0) {
            return -1;
        }
        return esum;
    }
}
