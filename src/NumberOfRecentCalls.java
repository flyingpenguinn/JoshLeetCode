import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfRecentCalls {
    Deque<Integer> dq= new ArrayDeque<>();
    public NumberOfRecentCalls() {

    }

    // amortized O(1)
    public int ping(int t) {
        dq.offerLast(t);
        while(!dq.isEmpty() && dq.peekFirst()<t-3000){
            dq.pollFirst();
        }
        return dq.size();
    }
}
