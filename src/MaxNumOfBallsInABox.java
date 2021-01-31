import java.util.HashMap;
import java.util.Map;

public class MaxNumOfBallsInABox {
    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> m = new HashMap<>();
        int max = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = 0;
            int num = i;
            while (num > 0) {
                sum += (num % 10);
                num /= 10;
            }
            int nv = m.getOrDefault(sum, 0) + 1;
            m.put(sum, nv);
            max = Math.max(max, nv);
        }
        return max;
    }
}
