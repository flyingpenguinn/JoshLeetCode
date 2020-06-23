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

    Deque<int[]> min = new ArrayDeque<>();
    Deque<Integer> st = new ArrayDeque<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        st.push(x);
        if (min.isEmpty() || min.peek()[0] > x) {
            min.push(new int[]{x, st.size() - 1});
        }
    }

    public void pop() {
        int rt = st.pop();
        if (min.peek()[0] == rt && min.peek()[1] == st.size()) {
            min.pop();
        }
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return min.peek()[0];
    }
}


