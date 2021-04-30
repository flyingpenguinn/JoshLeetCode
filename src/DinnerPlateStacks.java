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
    class DinnerPlates {

        private TreeSet<Integer> nonfull = new TreeSet<>();  // has cap but not full
        private TreeSet<Integer> nonempty = new TreeSet<>(); // has cap not 0
        private Map<Integer, Deque<Integer>> sm = new HashMap<>();
        private int cap = 0;
        private int cur = 0; // next stack to push in
        // in the beginning it's as if all left to 0 are full so we start with 0

        public DinnerPlates(int capacity) {
            this.cap = capacity;
        }

        public void push(int val) {
            if (nonfull.isEmpty()) {
                Deque<Integer> pushed = new ArrayDeque<>();
                pushed.add(val);
                sm.put(cur, pushed);
                nonempty.add(cur);
                if (cap > 1) {
                    nonfull.add(cur);
                }
                cur++;
            } else {
                int leftmost = nonfull.first();
                Deque<Integer> st = sm.get(leftmost);
                st.push(val);
                if (st.size() == cap) {
                    nonfull.remove(leftmost);
                }
                nonempty.add(leftmost);
            }
        }

        public int pop() {
            if (nonempty.isEmpty()) {
                return -1;
            }
            int rightmost = nonempty.last();
            return popAtStack(rightmost);
        }

        public int popAtStack(int index) {
            Deque<Integer> st = sm.get(index);
            if (st==null || st.isEmpty()) {
                return -1;
            }
            int rt = st.pop();
            if (st.isEmpty()) {
                nonempty.remove(index);
            }
            nonfull.add(index);
            return rt;
        }
    }
}
