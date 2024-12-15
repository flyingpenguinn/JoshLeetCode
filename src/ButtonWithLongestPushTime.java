public class ButtonWithLongestPushTime {
    public int buttonWithLongestTime(int[][] a) {
        int n = a.length;
        int cmax = -1;
        int cindex = -1;
        int last = 0;
        for (int i = 0; i < n; ++i) {
            int idx = a[i][0];
            int time = a[i][1];
            int cur = time - last;
            if (cur > cmax) {
                cmax = cur;
                cindex = idx;
            } else if (cur == cmax) {
                if (idx < cindex) {
                    cindex = idx;
                }
            }
            last = time;
        }
        return cindex;
    }
}
