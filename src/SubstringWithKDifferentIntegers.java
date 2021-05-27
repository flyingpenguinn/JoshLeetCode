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
        return atmost(a, k)- atmost(a, k-1);
    }

    private int atmost(int[] a, int k){
        if(k==0){
            return 0;
        }
        int n = a.length;
        int start = 0;
        Map<Integer,Integer> m = new HashMap<>();
        int res = 0;
        for(int i=0; i<n; i++){
            m.put(a[i], m.getOrDefault(a[i], 0)+1);
            while(m.size()>k){
                m.put(a[start], m.get(a[start])-1);
                if(m.get(a[start])==0){
                    m.remove(a[start]);
                }
                start++;
            }
            // start...i good, have at most k chars
            res += i-start+1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(new SubstringWithKDifferentIntegers().subarraysWithKDistinct(a, k));
    }
}
