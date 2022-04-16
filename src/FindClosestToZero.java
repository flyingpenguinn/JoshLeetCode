public class FindClosestToZero {
    public int findClosestNumber(int[] nums) {
        int minabs = (int) (1e9);
        int res = (int) (-1e9);
        for (int ai : nums) {
            int abs = Math.abs(ai);
            if (abs < minabs) {
                minabs = abs;
                res = ai;
            } else if (abs == minabs) {
                res = Math.max(res, ai);
            }
        }
        return res;
    }
}
