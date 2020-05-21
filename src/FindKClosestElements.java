import base.ArrayUtils;

import java.util.*;

public class FindKClosestElements {
    // quick nlogn solution...
    public List<Integer> findClosestElements(int[] a, int k, int t) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] == y[0] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
        for (int i = 0; i < a.length; i++) {
            pq.offer(new int[]{Math.abs(a[i] - t), a[i]});
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

// binary search to get the <= and > part, then two pointers
class FindKClosestElementsFaster {
    public List<Integer> findClosestElements(int[] a, int k, int t) {
        int[] pos = binarysearchlastequal(a, t);
        int i = pos[0];
        // i last <=, j first >
        int j = pos[1];
        List<Integer> r = new ArrayList<>();
        int n = a.length;
        while (k > 0) {
            if (i == -1) {
                j++;
            } else if (j == n) {
                i--;
            } else if (Math.abs(a[i] - t) <= Math.abs(a[j] - t)) {
                i--;
            } else {
                j++;
            }
            k--;
        }
        for(int p=i+1; p<=j-1; p++){
            r.add(a[p]);
        }
        return r;
    }

    private int[] binarysearchlastequal(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return new int[]{u, l};
    }
}