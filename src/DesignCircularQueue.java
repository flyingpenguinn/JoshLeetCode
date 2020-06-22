public class DesignCircularQueue {

    public static void main(String[] args) {

    }
}

class MyCircularQueue {
    int[] a = null;
    int size = 0;
    int cap = 0;
    int tail = 0; // where to put if we enqueue from last
    int head = -1; // where to put if we enqueue from the head
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int cap) {
        this.a = new int[cap];
        this.cap = cap;
        this.head = cap-1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        a[tail] = value;
        tail = (tail+1) % cap;
        size++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        head = (head+1)%cap;
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return a[(head+1) % cap];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return a[(tail-1+cap) % cap];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size==cap;
    }
}
