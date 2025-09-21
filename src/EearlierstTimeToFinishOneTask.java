public class EearlierstTimeToFinishOneTask {
    public int earliestTime(int[][] a) {
        int n = a.length;
        int res = (int) 1e9;
        for (int[] ai : a) {
            int end = ai[0] + ai[1];
            res = Math.min(res, end);
        }
        return res;
    }
}
