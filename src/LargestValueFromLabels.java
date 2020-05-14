import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/*
LC#1090
We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:

|S| <= num_wanted
For every label L, the number of items in S with label L is <= use_limit.
Return the largest possible sum of the subset S.



Example 1:

Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
Explanation: The subset chosen is the first, third, and fifth item.
Example 2:

Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
Output: 12
Explanation: The subset chosen is the first, second, and third item.
Example 3:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
Output: 16
Explanation: The subset chosen is the first and fourth item.
Example 4:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
Output: 24
Explanation: The subset chosen is the first, second, and fourth item.


Note:

1 <= values.length == labels.length <= 20000
0 <= values[i], labels[i] <= 20000
1 <= num_wanted, use_limit <= values.length
 */
public class LargestValueFromLabels {
    // greedily get the biggest value as long as we are allowed...
    int[] v;
    int[] lb;

    public int largestValsFromLabels(int[] v, int[] lb, int n, int limit) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(v[b], v[a]));
        this.v = v;
        this.lb = lb;
        int r = 0;
        Map<Integer, Integer> cm = new HashMap<>();
        for (int i = 0; i < v.length; i++) {
            pq.offer(i);
        }
        // !empty because we may get fewer than n numbers and q is drained first
        while (n > 0 && !pq.isEmpty()) {
            int id = pq.poll();
            int cc = cm.getOrDefault(lb[id], 0) + 1;
            if (cc > limit) {
                continue;
            }
            r += v[id];
            cm.put(lb[id], cc);
            n--;
        }
        return r;
    }
}
