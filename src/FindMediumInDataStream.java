import java.util.Collections;
import java.util.PriorityQueue;

/*
LC#295
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.


Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2


Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class FindMediumInDataStream {

}

class MedianFinder {
    // sm.size >= bg.size but no bigger than 1 to simplify logics
    PriorityQueue<Integer> sm = new PriorityQueue<>(Collections.reverseOrder());
    // big heap
    PriorityQueue<Integer> bg = new PriorityQueue<>();

    public MedianFinder() {

    }

    // invariant: all in bg>=sm,sm-bg size ==0 or 1
    public void addNum(int n) {
        if (sm.isEmpty()) {
            sm.offer(n);
        } else if (n > sm.peek()) {
            bg.offer(n);
        } else {
            sm.offer(n);
        }
        if (bg.size() > sm.size()) {
            sm.offer(bg.poll());
        }
        if (sm.size() - bg.size() > 1) {
            bg.offer(sm.poll());
        }
    }

    public double findMedian() {
        if (sm.isEmpty()) {
            return -1.0;
        } else if (sm.size() == bg.size()) {
            return (sm.peek() + bg.peek()) / 2.0;
        } else {
            return sm.peek();
        }

    }
}
