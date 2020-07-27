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
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // big queue for smaller nums
    private PriorityQueue<Integer> big = new PriorityQueue<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        if(small.isEmpty()){
            small.offer(num);
        }else if (small.peek() >=num){
            small.offer(num);
        }else{
            big.offer(num);
        }
        // small size == big.size or big.size+1
        if(small.size()>big.size()+1){
            big.offer(small.poll());
        }if(big.size()>small.size()){
            small.offer(big.poll());
        }
    }

    public double findMedian() {
        if(small.isEmpty()){
            throw new IllegalStateException();
        }else if ((small.size()+big.size()) % 2==0){
            return (small.peek() + big.peek())/2.0;
        }else{
            return small.peek();
        }
    }
}
