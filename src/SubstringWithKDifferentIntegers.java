import java.util.HashMap;
import java.util.Map;

/*
LC#992
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
 */
public class SubstringWithKDifferentIntegers {
    // == k => ?<=k - <=k-1
    public int subarraysWithKDistinct(int[] a, int k) {
        return findanswer(a, k) - findanswer(a, k - 1);
    }


    //<=k distinct numbers
    // note it's hard to calc exactly == k because when we do low++ we dont know how many low...<high are actually == k
    protected int findanswer(int[] a, int k) {
        int n = a.length;
        int low = 0;
        int high = -1;
        Map<Integer, Integer> m = new HashMap<>();
        int r = 0;
        while (true) {
            if (m.keySet().size() <= k) {
                // low...high, low+1.... high... high...high
                r += high - low + 1;
                high++;
                if (high == n) {
                    break;
                }
                update(m, a[high], 1);
            } else {
                // low...high-1
                update(m, a[low++], -1);
            }
        }
        return r;
    }

    private void update(Map<Integer, Integer> m, int k, int nd) {
        int nv = m.getOrDefault(k, 0) + nd;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(new SubstringWithKDifferentIntegers().subarraysWithKDistinct(a, k));
    }
}
