import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PreviousPermutationWithOneSwap {
    public int[] prevPermOpt1(int[] a) {
        int n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && a[i] > a[i + 1]) {
                // bigget number that is smaller than a[i], swap with a[i]. use binary search for better time complexity
                int biggestSmaller = binarySearch(a, i + 1, n - 1, a[i]);
                // can also do another scan to get the lastshow...
                int lastshow = map.get(a[biggestSmaller]);
                swap(a, i, lastshow);
                break;
            } else {
                map.put(a[i], i);
            }
        }
        return a;
    }

    private int binarySearch(int[] a, int l, int u, int t) {
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PreviousPermutationWithOneSwap().prevPermOpt1(ArrayUtils.read1d("90,27,11,36,57,87"))));
        System.out.println(Arrays.toString(new PreviousPermutationWithOneSwap().prevPermOpt1(ArrayUtils.read1d("3,2,1"))));
        System.out.println(Arrays.toString(new PreviousPermutationWithOneSwap().prevPermOpt1(ArrayUtils.read1d("1,1,5"))));
        System.out.println(Arrays.toString(new PreviousPermutationWithOneSwap().prevPermOpt1(ArrayUtils.read1d("1,9,4,6,7"))));
        System.out.println(Arrays.toString(new PreviousPermutationWithOneSwap().prevPermOpt1(ArrayUtils.read1d("3,1,1,3"))));
    }
}
