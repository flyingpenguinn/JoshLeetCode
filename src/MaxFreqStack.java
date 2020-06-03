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
        // use the fact that whatever is coming later must be popped first. so we just need to record their arrival timing
        // keep freq at insertion time + insertion time into a pq
        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<int[]> ts = new PriorityQueue<>((x, y) -> x[1] == y[1] ? Integer.compare(y[2], x[2]) : Integer.compare(y[1], x[1]));
        // value, freq and last appearance time
        // dont need another stack here to keep track of each element: they are already in treeset


        public FreqStack() {

        }

        // late arrivers will trump early comers anyway
        int time = 0;

        public void push(int x) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            ts.add(new int[]{x, freq.get(x), time});
            time++;
        }

        public int pop() {
            int[] first = ts.poll();
            int rt = first[0];
            int nv = freq.get(rt) - 1;
            if (nv > 0) {
                freq.put(rt, nv);
            } else {
                freq.remove(rt);
            }
            return rt;
        }
    }

    static class FreqStackO1 {
        // for a given number that shows t times, it's in st map, with key from 1 to t
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Deque<Integer>> st = new HashMap<>();
        // freq-> all numbers that had shown this freq before

        int maxfreq = 0;

        public void push(int x) {
            int nv = freq.getOrDefault(x, 0) + 1;
            maxfreq = Math.max(maxfreq, nv);
            freq.put(x, nv);
            st.computeIfAbsent(nv, k -> new ArrayDeque<>()).push(x);
        }

        public int pop() {

            Deque<Integer> maxfreqstack = st.get(maxfreq);


            int rt = maxfreqstack.pop();
            if (maxfreqstack.isEmpty()) {
                st.remove(maxfreq);
                maxfreq--;
            }
            int nv = freq.getOrDefault(rt, 0) - 1;
            if (nv <= 0) {
                freq.remove(rt);
            } else {
                freq.put(rt, nv);
            }
            return rt;
        }
    }
}
