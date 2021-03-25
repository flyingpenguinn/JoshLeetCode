import base.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
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
        TreeMap<Integer,Integer> ta = new TreeMap<>();
        for(int i=0; i<a.length; i++){
            update(ta, a[i], 1);
        }
        int[] res = new int[a.length];
        for(int i=0; i<b.length;i++){
            Integer higher = ta.higherKey(b[i]);
            if(higher != null){
                update(ta, higher, -1);
                res[i] = higher;
            }else{
                res[i] = ta.firstKey();
                update(ta, res[i], -1);
            }
        }
        return res;
    }

    private void update(Map<Integer,Integer> m, int k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AdvantageShuffle().advantageCount(ArrayUtils.read1d("2,0,4,1,2"), ArrayUtils.read1d("1,3,0,0,2"))));
    }
}
