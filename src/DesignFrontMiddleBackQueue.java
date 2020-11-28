import java.util.ArrayDeque;
import java.util.Deque;

public class DesignFrontMiddleBackQueue {
    // two deques, miao!
    // note the tricky logic in push middle
    class FrontMiddleBackQueue {
        private Deque<Integer> q1 = new ArrayDeque<>();
        private Deque<Integer> q2 = new ArrayDeque<>();

        public FrontMiddleBackQueue() {

        }

        private void balance() {
            if (q1.size() < q2.size()) {
                q1.offerLast(q2.pollFirst());
            }
            if (q1.size() > q2.size() + 1) {
                q2.offerFirst(q1.pollLast());
            }
        }

        public void pushFront(int val) {
            q1.offerFirst(val);
            balance();
        }

        public void pushMiddle(int val) {

            if (q1.size() == q2.size()) {
                q1.offerLast(val);
            } else {
                // note  [1,3], [2] => [1,4] [3,2]
                q2.offerFirst(q1.pollLast());
                q1.offerLast(val);
            }
        }

        public void pushBack(int val) {
            q2.offerLast(val);
            balance();
        }


        public int popFront() {
            if (isEmpty()) {
                return -1;
            }
            int rt = q1.pollFirst();
            balance();
            return rt;
        }

        public int popMiddle() {
            if (isEmpty()) {
                return -1;
            }
            int rt = q1.pollLast();
            balance();
            return rt;
        }

        public int popBack() {
            if (isEmpty()) {
                return -1;
            }
            int rt = q2.isEmpty() ? q1.pollLast() : q2.pollLast();
            balance();
            return rt;
        }

        private boolean isEmpty() {
            return size() == 0;
        }

        private int size() {
            return q1.size() + q2.size();
        }
    }
}
