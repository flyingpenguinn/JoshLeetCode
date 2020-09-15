import base.ArrayUtils;

import java.util.*;

/*
LC#1439

You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.



Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
Example 2:

Input: mat = [[1,3,11],[2,4,6]], k = 9
Output: 17
Example 3:

Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
Output: 9
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
Example 4:

Input: mat = [[1,1,10],[2,2,9]], k = 7
Output: 12


Constraints:

m == mat.length
n == mat.length[i]
1 <= m, n <= 40
1 <= k <= min(200, n ^ m)
1 <= mat[i][j] <= 5000
mat[i] is a non decreasing array.
 */
public class FindKthSmallestSumInMatrixSortedRow {

    // we know how to merge 2 lists. merging n lists is just to merge 2 at a time then loop on this process until we only have one.
    // can also merge n/2 first then merge the remaining 2 as the last step
    // note k is small and every time we merge till we get k numbers and stop
    public int kthSmallest(int[][] a, int k) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            List<Integer> li = new ArrayList<>();
            for (int j = 0; j < a[i].length && j < k; j++) {
                li.add(a[i][j]);
            }
            list.add(li);
        }
        while (list.size() > 1) {
            List<List<Integer>> nlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i += 2) {
                if (i + 1 < list.size()) {
                    List<Integer> ri = merge(list.get(i), list.get(i + 1), k);
                    nlist.add(ri);
                } else {
                    nlist.add(list.get(i));
                }
            }
            list = nlist;
        }
        return list.get(0).get(k - 1);
    }

    private List<Integer> merge(List<Integer> l1, List<Integer> l2, int k) {
        // i1, i2, value
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        List<Integer> res = new ArrayList<>();
        pq.offer(new int[]{0, 0, l1.get(0) + l2.get(0)});
        while (!pq.isEmpty() && k > 0) {
            int[] top = pq.poll();
            res.add(top[2]);
            int i = top[0];
            int j = top[1];
            if (j + 1 < l2.size()) {
                pq.offer(new int[]{i, j + 1, l1.get(i) + l2.get(j + 1)});
            }
            if (j == 0 && i + 1 < l1.size()) {
                pq.offer(new int[]{i + 1, j, l1.get(i + 1) + l2.get(j)});
            }
            k--;
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(new FindKthSmallestInSortedRowOnebyOne().kthSmallest(ArrayUtils.read("[[1,3,11],[2,4,6]]"), 3));
        System.out.println(new FindKthSmallestSumSortedRowBinarySearch().kthSmallest(ArrayUtils.read("[[1,3,11],[2,4,6]]"), 3));
    }
}

class FindKthSmallestInSortedRowOnebyOne {
    // we can also merge one by one, dont need to use divide and conquer...
    public int kthSmallest(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        List<Integer> cur = tolist(a[0], n);
        for (int i = 1; i < m; i++) {
            List<Integer> next = tolist(a[i], n);
            cur = dotwolists(cur, next, k);
        }
        return cur.get(k - 1);
    }

    protected List<Integer> tolist(int[] ints, int n) {
        List<Integer> cur = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            cur.add(ints[j]);
        }
        return cur;
    }


    private List<Integer> dotwolists(List<Integer> a, List<Integer> b, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        List<Integer> r = new ArrayList<>();
        if (a.size() == 0 || b.size() == 0 || k == 0) {
            return r;
        }
        pq.offer(new int[]{0, 0, a.get(0) + b.get(0)});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int ai = top[0];
            int bi = top[1];
            r.add(top[2]);
            if (r.size() == k) {
                break;
            }
            if (ai + 1 < a.size()) {
                pq.offer(new int[]{ai + 1, bi, a.get(ai + 1) + b.get(bi)});
            }
            if (ai == 0 && bi + 1 < b.size()) {
                pq.offer(new int[]{ai, bi + 1, a.get(ai) + b.get(bi + 1)});
            }
        }
        return r;
    }
}

class FindKthSmallestInSortedRowsBfs {
    // every time when we have a list of indexes, the next one is one of them+1
    // the gist of the sorted row problem is when we are at i, j, we can go to either i+1, j, or i, j+1
    public int kthSmallest(int[][] a, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int m = a.length;
        int n = a[0].length;
        int[] input = new int[m + 1];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += a[i][0];
        }
        input[0] = sum;
        q.offer(input);
        Set<String> visited = new HashSet<>();
        String indexstring = code(input);
        visited.add(indexstring);
        while (k > 0) {
            int[] top = q.poll();
            k--;
            if (k == 0) {
                return top[0];
            }
            for (int i = 1; i <= m; i++) {
                int[] newone = Arrays.copyOf(top, top.length);
                if (newone[i] + 1 < n) {
                    newone[0] -= a[i - 1][newone[i]];
                    newone[0] += a[i - 1][newone[i] + 1];
                    newone[i]++;
                    String curstring = code(newone);
                    if (!visited.contains(curstring)) {
                        visited.add(curstring);
                        q.offer(newone);
                    }
                }
            }
        }
        return -1;
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

class FindKthSmallestSumSortedRowBinarySearch {
    // note the backtrack way works because k<=200 and we prune bad results early
    public int kthSmallest(int[][] a, int k) {
        int l = 0;
        int u = 5000 * 40;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, mid, k)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // whether there are >= k sum of numbers <=mid
    private boolean good(int[][] a, int mid, int k) {
        int r = dog(a, mid, k, 0);
        return r >= k;
    }

    // at row i,  how many sums from this row onward are <=t. note the beautiful pruning!
    private int dog(int[][] a, int t, int k, int i) {
        if (t < 0) {
            return 0;
        }
        int m = a.length;
        if (i == m) {
            return 1;
        }
        int n = a[0].length;
        int sum = 0;
        for (int j = 0; j < n; j++) {
            int later = dog(a, t - a[i][j], k, i + 1);
            if (later == 0) {
                // even j won't work, so later bigger columns won't work even further
                break;
            }
            sum += later;
            if (sum >= k) {
                break;
            }
        }
        return sum;
    }
}
