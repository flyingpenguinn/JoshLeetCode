import java.util.*;

/*
LC#895
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


Example 1:

Input:
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].


Note:

Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class MaxFreqStack {

    public static void main(String[] args) {
        FreqStack stack = new FreqStack();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

    static class FreqStack {
        // we know the next freq in line: must be current ones -1
        // keep the elements in the old freq's stack map!
        // freq-> stack positions
        private Map<Integer, Deque<Integer>> m = new HashMap<>();
        private Map<Integer, Integer> f = new HashMap<>();
        private int maxfreq = 0;

        public FreqStack() {

        }

        public void push(int x) {
            int nv = update(f, x, 1);
            maxfreq = Math.max(maxfreq, nv);
            m.computeIfAbsent(nv, k -> new ArrayDeque<>()).push(x);
            //   System.out.println("after push "+m);
        }

        public int pop() {
            if (maxfreq == 0) {
                return -1;
            }
            int rt = m.get(maxfreq).pop();
            update(f, rt, -1);
            if (m.get(maxfreq).isEmpty()) {
                m.remove(maxfreq);
                maxfreq--;
            }
            //   System.out.println("after pop "+m);
            return rt;
        }

        private int update(Map<Integer, Integer> m, int k, int d) {
            int nv = m.getOrDefault(k, 0) + d;
            if (nv <= 0) {
                m.remove(k);
            } else {
                m.put(k, nv);
            }
            return nv;
        }
    }
}
