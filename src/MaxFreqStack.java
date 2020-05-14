import java.util.*;

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
}


class FreqStack {

    Map<Integer, Integer> freqMap = new HashMap<>();
    // if there are 3 5's and 2 7's the stack may look like
    /*
    3   5
    2   5-->7
    1   xxx

    when we pop 5 we pop its position in freq stack 3
     */
    Map<Integer, Deque<Integer>> freqStack = new HashMap<>();
    int maxFreq = 0;

    public FreqStack() {

    }

    public void push(int x) {

        int xfreq = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, xfreq);
        Deque<Integer> stack = freqStack.getOrDefault(xfreq, new ArrayDeque<>());
        stack.push(x);
        freqStack.put(xfreq, stack);
        maxFreq = Math.max(maxFreq, xfreq);
    }

    public int pop() {
        if (maxFreq == 0) {
            throw new IllegalArgumentException();
        }
        Deque<Integer> maxStack = freqStack.get(maxFreq);
        int rt = maxStack.pop();
        int rtFreq = freqMap.getOrDefault(rt, 0) - 1;
        freqMap.put(rt, rtFreq);
        if (maxStack.isEmpty()) {
            maxFreq--; // rt goes from freq = maxFreq to maxFreq-1 and it was the only element with maxFreq
        }
        return rt;
    }
}


class FreqStackPriorityQueue {

    class QueueItem {
        int x;
        int freq;
        int position;

        public QueueItem(int x, int freq, int position) {
            this.x = x;
            this.freq = freq;
            this.position = position;
        }
    }

    int size = 0;
    Map<Integer, Integer> freqMap = new HashMap<>();
    PriorityQueue<QueueItem> pq = new PriorityQueue<>(new QueueItemComparator());

    class QueueItemComparator implements Comparator<QueueItem> {

        @Override
        public int compare(QueueItem o1, QueueItem o2) {
            if (o1.freq != o2.freq) {
                return Integer.compare(o2.freq, o1.freq);
            } else {
                return Integer.compare(o2.position, o1.position);
            }
        }
    }

    public void push(int x) {
        size++;
        int xfreq = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, xfreq);
        pq.offer(new QueueItem(x, xfreq, size));

    }

    public int pop() {
        if (!pq.isEmpty()) {
            QueueItem top = pq.poll();
            freqMap.put(top.x, freqMap.get(top.x) - 1);
            return top.x;
        }
        throw new IllegalArgumentException();
    }
}
