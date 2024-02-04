public class AntOnTheBoundary {
    public int returnToBoundaryCount(int[] a) {
        int pos = 0;
        int res = 0;
        for (int ai : a) {
            pos += ai;
            if (pos == 0) {
                ++res;
            }
        }
        return res;
    }
}
