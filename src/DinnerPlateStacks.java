import java.util.*;

/*
LC#1172
You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.

Implement the DinnerPlates class:

DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks are empty.
int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that stack, and returns -1 if the stack with that given index is empty.
Example:

Input:
["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
Output:
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

Explanation:
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:     4
                                                       1  3  5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.pop()            // Returns 5.  The stacks are now:      4
                                                        1  3
                                                        ﹈ ﹈
D.pop()            // Returns 4.  The stacks are now:   1  3
                                                        ﹈ ﹈
D.pop()            // Returns 3.  The stacks are now:   1
                                                        ﹈
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.


Constraints:

1 <= capacity <= 20000
1 <= val <= 20000
0 <= index <= 100000
At most 200000 calls will be made to push, pop, and popAtStack.
 */
public class DinnerPlateStacks {
    // no need to remove removed ones: middle ones keep their id and tail ones will be reused via pq.peek()>=size
    public static void main(String[] args) {

        DinnerPlates d = new DinnerPlates(2);  // Initialize with capacity = 2
        String ins = "push,push,push,push,push,push,push,push,popAtStack,popAtStack,popAtStack,popAtStack,push,push,push,push,push,push,push,push,pop,pop,pop,pop";
        String item = "[471],[177],[1],[29],[333],[154],[130],[333],[1],[0],[2],[0],[165],[383],[267],[367],[53],[373],[388],[249],[],[],[],[]";
        String[] inssplit = ins.split(",");
        String[] itemsplit = item.split(",");
        for (int i = 0; i < inssplit.length; i++) {

            if (inssplit[i].equals("push")) {
                d.push(toInt(itemsplit[i]));
            } else if (inssplit[i].equals("pop")) {
                System.out.println(d.pop());
            } else if (inssplit[i].equals("popAtStack")) {
                System.out.println(d.popAtStack(toInt(itemsplit[i])));
            }
        }
    }

    private static Integer toInt(String s) {
        return Integer.valueOf(s.substring(1, s.length() - 1));
    }
}

class DinnerPlates {
    List<Deque<Integer>> list = new ArrayList<>();
    int cap;
    TreeSet<Integer> free = new TreeSet<>();


    public DinnerPlates(int capacity) {
        this.cap = capacity;
    }

    public void push(int v) {
        Integer leftmost = free.isEmpty() ? null : free.first();
        Deque<Integer> dq = null;
        int index = -1;
        if (leftmost == null || leftmost >= list.size()) {
            dq = new ArrayDeque<>();
            dq.push(v);
            list.add(dq);
            index = list.size() - 1;
        } else {
            dq = list.get(leftmost);
            dq.push(v);
            index = leftmost;
        }
        if (dq.size() < cap) {
            free.add(index);
        } else if (dq.size() == cap) {
            free.remove(index);
        }
    }

    public int pop() {
        if (list.isEmpty()) {
            return -1;
        }
        int n = list.size();
        return popAtStack(n - 1);
    }

    public int popAtStack(int index) {
        if (index >= list.size()) {
            return -1;
        }
        Deque<Integer> dq = list.get(index);
        if (dq.isEmpty()) {
            return -1;
        }
        free.add(index);
        int rt = dq.pop();
        // if we pop  from right side, it's amortized O(1): each element is inserted and popped once
        while (!list.isEmpty() && index == list.size() - 1 && list.get(index).isEmpty()) {
            list.remove(index);
            index--;
        }

        return rt;
    }
}