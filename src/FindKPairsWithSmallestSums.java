import base.ArrayUtils;

import java.util.*;

/*
LC#373
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {
    // only move index in b when it's 0 in a. otherwise only move a
    // we can convert this to LC#378 find kth smallest in sorted matrix
    // can't do binary search as this one asks for all k elements
    // note here a and b don't have bigger/smaller relationship. Similar to #1439
    public List<List<Integer>> kSmallestPairs(int[] a, int[] b, int k) {
        // treat 2nd array as the column in kth in sorted array problem!
        List<List<Integer>> r = new ArrayList<>();
        if (a == null || a.length == 0 || b == null || b.length == 0 || k <= 0) {
            return r;
        }
        int na = a.length;
        int nb = b.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{0, 0, a[0] + b[0]});
        while (!pq.isEmpty() && k > 0) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            List<Integer> cur = new ArrayList<>();
            cur.add(a[i]);
            cur.add(b[j]);
            r.add(cur);
            if (j + 1 < nb) {
                pq.offer(new int[]{i, j + 1, a[i] + b[j + 1]});
            }
            if (i + 1 < na && j == 0) {
                pq.offer(new int[]{i + 1, j, a[i + 1] + b[j]});
            }
            k--;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new FindKPairsWithSmallestSums().kSmallestPairs(ArrayUtils.read1d("1,7,11"), ArrayUtils.read1d("2,4,6"), 9));
    }
}

class FindkPairsSmallestSum {
    // use bfs way similar to #1439. (i,j) -> (i+1, j) or (i, j+1). k is not too big in this problem so we can use this trick
    public List<List<Integer>> kSmallestPairs(int[] a, int[] b, int k) {
        if (a.length == 0 || b.length == 0) {
            return new ArrayList<>();
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int[] lens = new int[2];
        lens[0] = a.length;
        lens[1] = b.length;
        int[] input = new int[3];
        int sum = a[0] + b[0];
        input[0] = sum;
        q.offer(input);
        Set<String> visited = new HashSet<>();
        String indexstring = code(input);
        visited.add(indexstring);
        List<List<Integer>> r = new ArrayList<>();
        while (k > 0 && !q.isEmpty()) {
            int[] top = q.poll();
            k--;
            List<Integer> ri = new ArrayList<>();
            ri.add(a[top[1]]);
            ri.add(b[top[2]]);
            r.add(ri);
            for (int i = 1; i <= 2; i++) {
                int[] newone = Arrays.copyOf(top, top.length);
                int[] array = i == 1 ? a : b;
                if (newone[i] + 1 < lens[i - 1]) {
                    newone[0] -= array[newone[i]];
                    newone[0] += array[newone[i] + 1];
                    newone[i]++;
                    String curstring = code(newone);
                    if (!visited.contains(curstring)) {
                        visited.add(curstring);
                        q.offer(newone);
                    }
                }
            }
        }
        return r;
    }


    private String code(int[] newone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < newone.length; i++) {
            sb.append(newone[i]);
            sb.append(",");
        }
        return sb.toString();
    }

}
