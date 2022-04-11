
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxNumberAfterDigitalSwap {
    public int largestInteger(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        PriorityQueue<Integer> odds = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> evens = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            int cind = str.charAt(i) - '0';
            if (cind % 2 == 1) {
                odds.offer(cind);
            } else {
                evens.offer(cind);
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = str.charAt(i) - '0';
            int top = 0;
            if (cind % 2 == 1) {
                top = odds.poll();
            } else {
                top = evens.poll();
            }
            res = res * 10 + top;
        }
        return res;
    }
}
