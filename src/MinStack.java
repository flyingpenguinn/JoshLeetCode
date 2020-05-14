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
    Deque<Integer> stack = new ArrayDeque<>();
    // min value and the stack size at that time. in this way if min value is duplicated we only save once
    Deque<int[]> min = new ArrayDeque<int[]>();

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || x < min.peek()[0]) {
            min.push(new int[]{x, stack.size()});
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.peek() == min.peek()[0] && stack.size() == min.peek()[1]) {
            min.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek()[0];
    }
}

