import java.util.Arrays;

public class EatPizzas {
    public long maxWeight(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        // System.out.println(Arrays.toString(a));
        int days = n / 4;
        int odd = (days + 1) / 2;
        int even = days / 2;
        long res = 0;
        int i = n - 1;
        for (; i >= 0 && odd > 0; --i) {

            // System.out.println("i="+i+" odd "+a[i]);
            res += a[i];
            --odd;
        }
        for (; i - 1 >= 0 && even > 0; i -= 2) {
            //System.out.println("i-1="+(i-1) +" even "+a[i-1]);
            res += a[i - 1];
            --even;
        }
        return res;
    }
}
