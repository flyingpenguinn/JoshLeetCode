import java.util.TreeSet;

/*
LC#716
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */
public class MaxStack {
    // using two sets. can use a linked list to make peek and pop O(1). can store linked list node in sets

    // not really a stack anymore...more like a heap, later ones first
    TreeSet<int[]> q1 = new TreeSet<>((a, b) -> a[1] != b[1] ? Integer.compare(b[1], a[1]) : Integer.compare(b[0], a[0]));

    //bigger first,the later one if eq
    TreeSet<int[]> q2 = new TreeSet<>((a, b) -> a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(b[1], a[1]));

    public MaxStack() {

    }

    int time = 0;

    public void push(int x) {
        q1.add(new int[]{x, time});
        q2.add(new int[]{x, time});
        time++;
    }

    public int pop() {
        int[] top = q1.pollFirst();
        q2.remove(top);
        return top[0];
    }

    public int top() {
        return q1.first()[0];
    }

    public int peekMax() {
        return q2.first()[0];
    }

    public int popMax() {
        int[] top = q2.pollFirst();
        q1.remove(top);
        return top[0];
    }
}