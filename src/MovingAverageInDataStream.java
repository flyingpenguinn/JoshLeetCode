import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#346
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageInDataStream {
    /**
     * Initialize your data structure here.
     */
    int sum = 0;
    Deque<Integer> q = new ArrayDeque<>();
    int size = 0;

    public MovingAverageInDataStream(int size) {
        this.size = size;
    }

    public double next(int val) {
        q.offer(val);
        sum += val;
        if (q.size() > size) {
            sum -= q.poll();
        }
        return sum * 1.0 / q.size();
    }
}
