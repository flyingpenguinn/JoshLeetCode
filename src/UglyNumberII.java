import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;


/*
LC#264
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:

1 is typically treated as an ugly number.
n does not exceed 1690.
 */
public class UglyNumberII {
    // Don't really need a heap! the numbers in queue are already ordered
    // use long in case overflow
    public int nthUglyNumber(int k) {
        if (k == 1) {
            return 1;
        }
        Deque<Long> q2 = new ArrayDeque<>();
        Deque<Long> q3 = new ArrayDeque<>();
        Deque<Long> q5 = new ArrayDeque<>();
        q2.offer(2L);
        q3.offer(3L);
        q5.offer(5L);
        long r = -1;
        while (k > 1) {
            long n2 = q2.peek();
            long n3 = q3.peek();
            long n5 = q5.peek();
            if (n2 < n3 && n2 < n5) {
                r = q2.poll();
                q2.offer(2 * r);
                q3.offer(3 * r);
                q5.offer(5 * r);

            } else if (n3 < n2 && n3 < n5) {
                r = q3.poll();
                q3.offer(3 * r);
                q5.offer(5 * r);

            } else {
                r = q5.poll();
                q5.offer(5 * r);

            }
            k--;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        System.out.println(new UglyNumberII().nthUglyNumber(1690));
    }

}
