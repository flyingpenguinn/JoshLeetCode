import java.util.Arrays;

public class MaxNumberOfCoinsYouCanGet {
    public int maxCoins(int[] a) {
        Arrays.sort(a);
        int topick = a.length / 3;
        int res = 0;
        for (int i = a.length - 2; i >= 0 && topick > 0; i -= 2) {
            res += a[i];
            topick--;
        }
        return res;
    }
}
