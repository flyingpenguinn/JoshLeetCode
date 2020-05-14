import base.ArrayUtils;

import java.util.*;

/*
LC#632
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.



Example 1:

Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].


Note:

The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
 */
public class SmallestRangeCoveringElementsFromKLists {
    // offer the next in line for the smallest because that's the best choice
    // we can also use a priority queue, and take max of existing max with new comer. we won't pull out max one so this is safe
    public int[] smallestRange(List<List<Integer>> a) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int n = a.size();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE; // we wont kick max out anyway, so just need to keep it increasing when we add new element
        for (int i = 0; i < n; i++) {
            int v = a.get(i).get(0);
            min = Math.min(min, v);
            max = Math.max(max, v);
            pq.offer(new int[]{v, i, 0});
        }
        int[] r = new int[]{min, max};
        while (true) {
            int[] top = pq.poll();
            int array = top[1];
            int index = top[2];
            int v = top[0];
            if (max - v < r[1] - r[0]) {
                r[0] = v;
                r[1] = max;
            }
            if (index + 1 < a.get(array).size()) {
                int nv = a.get(array).get(index + 1);
                max = Math.max(nv, max);
                pq.offer(new int[]{nv, array, index + 1});
            } else {
                break;
            }
        }
        return r;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new SmallestRangeCoveringElementsFromKLists().smallestRange(ArrayUtils.toArrayList(ArrayUtils.read("[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]")))));
    }

}
