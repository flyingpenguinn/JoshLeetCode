import base.ArrayUtils;

import java.util.*;

/*
LC658
Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.



Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 10^4
Absolute value of elements in the array and x will not exceed 104
 */
public class FindKClosestElements {
    // quick nlogk solution...
    public List<Integer> findClosestElements(int[] a, int k, int t) {
        // big queue.. think about what we want to throw away
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] == y[0] ? Integer.compare(y[1], x[1]) : Integer.compare(y[0], x[0]));
        for (int i = 0; i < a.length; i++) {
            pq.offer(new int[]{Math.abs(a[i] - t), a[i]});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            r.add(pq.poll()[1]);
        }
        Collections.sort(r);
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("1,2,3,4,5"), 4, 3));
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("1,2,3,4,5"), 4, -1));
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("1,2,3,4,5"), 4, 1));
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("1,2,3,4,5"), 4, 2));
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("1,2,3,3,5,7"), 4, 4));
        System.out.println(new FindKClosestElementsFaster().findClosestElements(ArrayUtils.read1d("2,2,2,2,2,3,5,5,5"), 9, 4));
    }

}


class FindKClosestElementsFaster {
    // binary search to get the <= and > part, then two pointers
    public List<Integer> findClosestElements(int[] a, int k, int x) {
        // k >=1 <=length of array
        // a sorted
        // abs values won't overflow
        int[] starts = binarySearch(a, x);
        int i = starts[0];
        int j = starts[1];
        while(i>=0 && j<a.length && k>0 ){
            int diffi = Math.abs(a[i]-x);
            int diffj = Math.abs(a[j]-x);
            if( diffi <= diffj ){
                i--;
            }else if (diffi>diffj){
                j++;
            }
            k--;
        }
        while(i>=0 && k>0){
            i--;
            k--;
        }
        while(j<a.length && k>0){
            j++;
            k--;
        }
        List<Integer> r = new ArrayList<>();
        for(int p = i+1; p<=j-1; p++){
            r.add(a[p]);
        }
        return r;
    }

    private int[] binarySearch(int[] a, int t){
        int l = 0;
        int u = a.length-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid] <= t){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return new int[]{u, l};
    }
}

class KClosestElementsSlidingWindow {
    // sliding window, O(n)
    public List<Integer> findClosestElements(int[] a, int k, int x) {
        // k >=1 <=length of array
        // a sorted
        // abs values won't overflow
        int n = a.length;
        int diff = 0;

        for (int i = 0; i < k - 1; i++) {
            diff += Math.abs(a[i] - x);
        }
        int minDiff = Integer.MAX_VALUE;
        int minStart = -1;
        for (int i = k - 1; i < n; i++) {
            diff += Math.abs(a[i] - x);
            int head = i - k + 1;
            if (diff < minDiff) {
                minDiff = diff;
                minStart = head;
            }
            diff -= Math.abs(a[head] - x);
        }
        List<Integer> r = new ArrayList<>();
        for (int i = minStart; i < minStart + k; i++) {
            r.add(a[i]);
        }
        return r;
    }
}