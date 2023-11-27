import java.util.Arrays;

public class MinimizeAreaOfSquareHoles {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int mh = calcmax(hBars);
        int mv = calcmax(vBars);
        int minv = Math.min(mh, mv);
        return (minv + 1) * (minv + 1);
    }

    private int calcmax(int[] hBars) {
        int maxh = 0;
        int i = 0;
        while (i < hBars.length) {
            int j = i + 1;
            while (j < hBars.length && hBars[j] == hBars[j - 1] + 1) {
                ++j;
            }
            // i... j-1
            int clen = j - i;
            maxh = Math.max(maxh, clen);
            i = j;
        }
        return maxh;
    }
}
