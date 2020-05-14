import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/*
LC#313
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

Example:

Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
             super ugly numbers given primes = [2,7,13,19] of size 4.
Note:

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
public class SuperUglyNumber {
    // a quick n*k*log(n*k) way
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int k = primes.length;
        Deque<Long>[] queues = new ArrayDeque[k];
        for (int i = 0; i < k; i++) {
            queues[i] = new ArrayDeque<>();
            queues[i].add((long) primes[i]);
        }
        while (true) {
            long min = Integer.MAX_VALUE;
            int mini = -1;
            for (int i = 0; i < k; i++) {
                Long curmin = queues[i].peek();
                if (curmin < min) {
                    min = curmin;
                    mini = i;
                }
            }
            if (n == 2) {
                return (int) min;
            }
            queues[mini].poll();
            for (int j = mini; j < k; j++) {
                queues[j].add(primes[j] * min);
            }
            n--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SuperUglyNumber().nthSuperUglyNumber(13, ArrayUtils.read1d("[3,5,7,11,19,23,29,41,43,47]")));
    }
}

class SuperUglyNumberOnk {
    // O(n*k) way
    public int nthSuperUglyNumber(int n, int[] ps) {
        int k = ps.length;
        Deque<Integer>[] qs = new ArrayDeque[k];
        if (n == 1) {
            return 1;
        }
        for (int i = 0; i < k; i++) {
            qs[i] = new ArrayDeque<>();
            qs[i].offer(ps[i]);
        }
        int last = -1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;

            int mini = -1;
            for (int j = 0; j < k; j++) {
                if (mini == -1 || min > qs[j].peek()) {
                    min = qs[j].peek();
                    mini = j;
                }
            }

            last = min;
            qs[mini].poll();
            // only populate values >= ps[mini] into mini queue
            for (int j = mini; j < k; j++) {
                long nv = min * 1L * ps[j];
                if (nv <= Integer.MAX_VALUE) {
                    qs[j].offer((int) nv);
                    // must populate to queue j, not queueing up in mini
                }
            }
        }
        return last;
    }
}