import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/*
LC#870
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.



Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]


Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {

    // a bag not a set! pick the smallest that can beat b, or just pick the smallest as there is no hope
    public int[] advantageCount(int[] a, int[] b) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int ai : a) {
            map.put(ai, map.getOrDefault(ai, 0) + 1);
        }
        int[] r = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            Integer cand = map.higherKey(b[i]);
            if (cand == null) {
                cand = map.firstKey();
            }
            int nc = map.getOrDefault(cand, 0) - 1;
            if (nc == 0) {
                map.remove(cand);
            } else {
                map.put(cand, nc);
            }
            r[i] = cand;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AdvantageShuffle().advantageCount(ArrayUtils.read1d("2,0,4,1,2"), ArrayUtils.read1d("1,3,0,0,2"))));
    }
}
