import java.util.HashMap;
import java.util.Map;

/*
LC#1497
Given an array of integers arr of even length n and an integer k.

We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.

Return True If you can find a way to do that or False otherwise.



Example 1:

Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
Example 2:

Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).
Example 3:

Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
Example 4:

Input: arr = [-10,10], k = 2
Output: true
Example 5:

Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
Output: true


Constraints:

arr.length == n
1 <= n <= 10^5
n is even.
-10^9 <= arr[i] <= 10^9
1 <= k <= 10^5
 */
public class CheckIfAnyPairsAreDivsibleByK {
    // similar to LC#974, convert negative mod to positive ones
    public boolean canArrange(int[] a, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int mod = a[i] % k;
            if (mod < 0) {
                mod += k;
                // -2, k=5, then 3
                // -3, k=5, then 2. still would match each other.
                // note -2 can match with either 2 or -3, and the latters are both mapped to 2
            }
            m.put(mod, m.getOrDefault(mod, 0) + 1);
        }
        for (int key : m.keySet()) {
            int count = m.get(key);
            if (key == 0) {
                // 2 traps
                // we need to single out mod==0 case because there is no valid other
                // also can't simply return when count %2==0
                if (count % 2 != 0) {
                    return false;
                }
            } else {
                int othercount = m.getOrDefault(k - key, 0);
                if (count != othercount) {
                    return false;
                }
            }
        }
        return true;
    }
}
