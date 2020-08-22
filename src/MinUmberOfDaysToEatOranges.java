import java.util.HashMap;
import java.util.Map;

public class MinUmberOfDaysToEatOranges {
    /*
    try to shoot for 2 or 3:
    In each step, choose between 2 options: minOranges = 1 + min( (n%2) + f(n/2), (n%3) + f(n/3) )
    where f(n) is the minimum number of days to eat n oranges.
     */
    Map<Integer, Integer> dp = new HashMap<>();

    public int minDays(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        Integer rt = null;
        if (n <= 2) {
            rt = n;
        } else {
            int way1 = 0;
            if (n % 2 == 0) {
                way1 = 1 + minDays(n / 2);
            } else {
                way1 = 1 + minDays(n - 1);
            }
            int way2 = 0;
            if (n % 3 == 0) {
                way2 = 1 + minDays((n - 2 * (n / 3)));
            } else if ((n - 1) % 3 == 0) {
                way2 = 1 + minDays((n - 1));
            } else {
                way2 = 2 + minDays((n - 2));
            }
            rt = Math.min(way1, way2);
        }
        dp.put(n, rt);
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new MinUmberOfDaysToEatOranges().minDays(2000000000));
        System.out.println(new MinUmberOfDaysToEatOranges().minDays(10));
        System.out.println(new MinUmberOfDaysToEatOranges().minDays(6));
        System.out.println(new MinUmberOfDaysToEatOranges().minDays(56));
    }
}
