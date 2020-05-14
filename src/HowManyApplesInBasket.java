import java.util.Arrays;

public class HowManyApplesInBasket {
    public int maxNumberOfApples(int[] a) {
        Arrays.sort(a);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum > 5000) {
                break;
            }
            count++;
        }
        return count;
    }
}
