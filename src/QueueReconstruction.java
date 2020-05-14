import base.ArrayUtils;
import crap.Crap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
LC#406
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstruction {
    // big numbers first if number the same, smaller sequence first
    public int[][] reconstructQueue(int[][] p) {
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(a[1], b[1]);
            }
        });
        List<int[]> r = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            // dont need to spend time to search for insertion point: pi[1] is the insertion point as all bigger numbers have showed up
            int[] pi = p[i];
            // at jth
            r.add(pi[1], pi);
        }
        int[][] rr = new int[r.size()][2];
        for (int i = 0; i < rr.length; i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new QueueReconstruction().reconstructQueue(ArrayUtils.read("[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]"))));
    }
}
