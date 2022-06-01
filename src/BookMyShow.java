import java.util.Arrays;

public class BookMyShow {
    private int[] start;
    private int[] rem;
    private int lastrej = (int) 2e9;

    public BookMyShow(int n, int m) {
        start = new int[n];
        rem = new int[n];
        Arrays.fill(rem, m);
    }

    public int[] gather(int k, int maxRow) {
        for (int i = 0; i <= maxRow; ++i) {
            if (rem[i] >= k) {
                int st = start[i];
                start[i] += k;
                rem[i] -= k;
                return new int[]{i, st};
            }
        }
        return new int[0];
    }

    public boolean scatter(int k, int maxRow) {
        if (k >= lastrej) {
            return false;
        }
        int tk = k;
        for (int i = 0; i <= maxRow && tk > 0; ++i) {
            int eaten = Math.min(rem[i], tk);
            tk -= eaten;
        }
        if (tk > 0) {
            lastrej = k;
            return false;
        }
        tk = k;
        for (int i = 0; i <= maxRow && tk > 0; ++i) {
            int eaten = Math.min(rem[i], tk);
            tk -= eaten;
            rem[i] -= eaten;
            start[i] += eaten;
        }

        return true;
    }
}
