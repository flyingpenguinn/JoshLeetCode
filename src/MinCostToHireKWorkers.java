/*
LC#857
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.



Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.


Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
 */

import base.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinCostToHireKWorkers {

    // 1. must have one person getting the exact expected ratio
    // 2. sort their rations, k-1 th can allow 0...k-1 to work
    // 3. iterate through k-1...n, each time a new worker joins, he enables more quality options. we use a pq to maintain k smallest quality
    // because result = ration * (sum of smallest qualities)
    // 4. must sort because we need kth to enable 0 to k-1 definitely
    // note we can't sort then scan for k consecutive wokers because we need k smallest quality workers, not k consecutive workers
    class Person {
        double unit;
        int q;
        int w;

        public Person(double unit, int q, int w) {
            this.unit = unit;
            this.q = q;
            this.w = w;
        }
    }

    public double mincostToHireWorkers(int[] q, int[] w, int k) {
        int n = w.length;
        Person[] persons = new Person[n];
        for (int i = 0; i < n; i++) {
            persons[i] = new Person(w[i] * 1.0 / q[i], q[i], w[i]);
        }
        Arrays.sort(persons, (x, y) -> Double.compare(x.unit, y.unit));
        PriorityQueue<Person> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.q, x.q));
        int sum = 0;
        for (int i = 0; i < k - 1; i++) {
            pq.offer(persons[i]);
            sum += persons[i].q;
        }
        double min = Integer.MAX_VALUE;
        // enumerate the "enabler": others follow its unit value
        for (int i = k - 1; i < n; i++) {
            sum += persons[i].q;
            double cur = sum * persons[i].unit;
            min = Math.min(min, cur);
            pq.offer(persons[i]);
            sum -= pq.poll().q;
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostToHireKWorkers().mincostToHireWorkers(ArrayUtils.read1d("1,2"), ArrayUtils.read1d("14,16"), 1));
    }
}
