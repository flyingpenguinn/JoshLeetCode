import java.util.ArrayDeque;
import java.util.Deque;

public class ImplementStackUsingQueues {
}


class MyStack {
    // bad, On^2 solutions... could have made push on and top o1

    /**
     * Initialize your data structure here.
     */
    Deque<Integer> q = new ArrayDeque<>();

    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        q.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return getlast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        int last = getlast();
        q.offer(last);
        return last;
    }

    int getlast() {
        int n = q.size();
        int last = -1;
        while (n > 0) {
            last = q.poll();
            n--;
            if (n > 0) {
                q.offer(last);
            }
        }
        return last;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q.isEmpty();
    }
}