public class FindChildWithBallAfterK {
    public int numberOfChild(int n, int k) {
        int i = 0;
        int dir = 1;
        while (k > 0) {
            if (i == n - 1) {
                dir = -1;
            } else if (i == 0) {
                dir = 1;
            }
            i += dir;
            --k;
        }
        return i;
    }
}
