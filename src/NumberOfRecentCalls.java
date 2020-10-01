import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfRecentCalls {
    private Deque<Integer> dq = new ArrayDeque<>();

    public NumberOfRecentCalls() {

    }

    // amortized O(1)
    public int ping(int t) {
        while (!dq.isEmpty() && dq.peekFirst() < t - 3000) {
            dq.pollFirst();
        }
        dq.offerLast(t);
        return dq.size();
    }
}
