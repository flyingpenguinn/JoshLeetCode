public class MaxCountOfPositiveNegativeInteger {
    public int maximumCount(int[] a) {
        int pc = 0;
        int nc = 0;
        for (int ai : a) {
            if (ai > 0) {
                ++pc;
            } else if (ai < 0) {
                ++nc;
            }
        }
        return Math.max(pc, nc);
    }
}
