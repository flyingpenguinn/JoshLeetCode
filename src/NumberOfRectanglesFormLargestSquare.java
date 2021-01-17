public class NumberOfRectanglesFormLargestSquare {
    public int countGoodRectangles(int[][] a) {
        int maxlen = 0;
        int maxcount = 0;
        for (int[] ai : a) {
            int len = Math.min(ai[0], ai[1]);
            if (len > maxlen) {
                maxlen = len;
                maxcount = 1;
            } else if (len == maxlen) {
                maxcount++;
            }
        }
        return maxcount;
    }

}
