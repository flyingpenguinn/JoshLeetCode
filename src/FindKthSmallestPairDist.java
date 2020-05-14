import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
LC#719
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
public class FindKthSmallestPairDist {
    // it doesnt care about order so we can sort it. O(logn^2)
    // this question can be converted to row/col sorted matrix problem. so we can use the same way as that one. a[j]-a[i] is put on i,j
    // cols value are decreasing
    public int smallestDistancePair(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int l = 0;
        int u = a[n - 1] - a[0];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (pairs(a, mid) >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // how many pair diffs <=m. here we calc how many pairs > and then deduct from it
    private int pairs(int[] a, int m) {
        int low = 0;
        int high = 0;
        int n = a.length;
        int r = 0;
        while (true) {
            if (a[high] - a[low] <= m) {
                // if <= we calc r with high because every low...high, low+1...high.... high-1..high would constitute pairs
                r += high - low;
                high++;
                if (high == n) {
                    break;
                }
            } else {
                // >m now, high...n-1 all >m
                low++;
            }
        }
        return r;
    }

    // how many pairs are having dist <= mid, is the number >=k. use two pointer to get this number
    // note we can also


    public static void main(String[] args) {
        System.out.println(new FindKthSmallestPairDist().smallestDistancePair(ArrayUtils.read1d("62,100,4"), 2));
        System.out.println(new FindKthSmallestPairDist().smallestDistancePair(ArrayUtils.read1d("1,3,1"), 1));
        System.out.println(new FindKthSmallestPairDist().smallestDistancePair(ArrayUtils.read1d("1,3,1,4"), 6));
    }
}


// TLE, but if k is small, this will be useful
class FindKthSmallestPairDistBfs {
    // if we now have aj-ai, next would be aj-ai+1 or aj+1-ai
    public int smallestDistancePair(int[] a, int k) {
        Arrays.sort(a);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        Set<String> seen = new HashSet<>();
        int n = a.length;
        for (int i = 0; i + 1 < n; i++) {
            pq.offer(new int[]{a[i + 1] - a[i], i, i + 1});
            seen.add(code(i, i + 1));
        }
        while (k > 0) {
            int[] top = pq.poll();
            k--;
            if (k == 0) {
                return top[0];
            }

            if (top[1] + 1 < top[2]) {
                int ni = top[1] + 1;
                int nj = top[2];
                process(a, pq, seen, ni, nj);
            }
            if (top[2] + 1 < n) {
                int ni = top[1];
                int nj = top[2] + 1;
                process(a, pq, seen, ni, nj);
            }
        }
        return -1;
    }

    protected void process(int[] a, PriorityQueue<int[]> pq, Set<String> seen, int ni, int nj) {
        String code = code(ni, nj);
        if (!seen.contains(code)) {
            seen.add(code);
            pq.offer(new int[]{a[nj] - a[ni], ni, nj});
        }
    }

    String code(int i, int j) {
        return i + "," + j;
    }
}