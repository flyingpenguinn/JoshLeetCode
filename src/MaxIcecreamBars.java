import java.util.Arrays;

public class MaxIcecreamBars {
    public int maxIceCream(int[] a, int coins) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int res = 0;
        while (coins > 0 && i < n) {
            if (coins >= a[i]) {
                coins -= a[i];
                i++;
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}
