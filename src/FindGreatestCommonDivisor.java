import java.util.Arrays;

public class FindGreatestCommonDivisor {
    public int findGCD(int[] a) {
        int min = Arrays.stream(a).min().getAsInt();
        int max = Arrays.stream(a).max().getAsInt();
        return gcd(min, max);
    }

    int gcd(int a, int b){
        return b==0?a:gcd(b, a%b);
    }
}
