import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#155
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */
public class MinStack {

    /**
     * initialize your data structure here.
     */
    Deque<Integer> st = new ArrayDeque<>();
    Deque<int[]> min = new ArrayDeque<>();

    public MinStack() {

    }

    public void push(int x) {
        // min first then stack to capture the counter before the push, i.e. after the pop
        if (min.isEmpty() || min.peek()[0] > x) {
            min.push(new int[]{x, st.size()});
        }
        st.push(x);
    }

    public void pop() {
        if (st.isEmpty()) {
            return;
        }
        int rt = st.pop();
        if (min.peek()[0] == rt && min.peek()[1] == st.size()) {
            min.pop();
        }
    }

    public int top() {
        if (st.isEmpty()) {
            return -1;
        }
        return st.peek();
    }

    public int getMin() {
        if (min.isEmpty()) {
            return -1;
        }
        return min.peek()[0];
    }
}


