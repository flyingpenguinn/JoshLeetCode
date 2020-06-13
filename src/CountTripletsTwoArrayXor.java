import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/*
LC#1442
Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.



Example 1:

Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
Example 2:

Input: arr = [1,1,1,1,1]
Output: 10
Example 3:

Input: arr = [2,3]
Output: 0
Example 4:

Input: arr = [1,3,5,7,9]
Output: 3
Example 5:

Input: arr = [7,11,12,9,5,2,7,17,22]
Output: 8


Constraints:

1 <= arr.length <= 300
1 <= arr[i] <= 10^8
 */
public class CountTripletsTwoArrayXor {
    // way 1: fix j, hash map i, and hashmap k. ignoring code here
    // way 2: ai... j-1 and aj...k the same, so ai...k xor == 0
    public int countTriplets(int[] a) {
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            int xor = a[i];
            for (int k = i + 1; k < n; k++) {
                xor ^= a[k];
                if (xor == 0) {
                    r += k - i;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        //        System.out.println(new CountTripletsTwoArrayXorOn().countTriplets(ArrayUtils.read1d("2,3,1,6,7")));
        System.out.println(new CountTripletsTwoArrayXorOn().countTriplets(ArrayUtils.read1d("1,1,1,1")));
    }
}


class CountTripletsTwoArrayXorOn {
    // because ai^ai+1^...ak==0 => a0...ai-1ai....ak ^ a0....ai-1 = 0 => a0... ai-1 and a0...ak are the same in xor
    public int countTriplets(int[] a) {
        int n = a.length;
        int r = 0;
        // xor value to index, and previous results. i.e. sum of i- (pre+1)
        Map<Integer, int[]> m = new HashMap<>();
        m.put(0, new int[]{-1, -1, 1});
        // if all == 0 then it starts from index 0. note every time we put i-(i+1) into map too so that later k can just add k-i
        int xor = 0;
        for (int k = 0; k < n; k++) {
            xor ^= a[k];
            int cr = 0;
            int times = 0;
            if (m.containsKey(xor)) {
                int[] cur = m.get(xor);
                int i = cur[0]; // cur[0] is actually i-1
                times = cur[2];
                cr = cur[1] + (k - i) * times;
                r += cr;
            }
            // cr-1 because we add i-(i+1) for later ks
            m.put(xor, new int[]{k, cr - 1, times + 1});
        }
        return r;
    }
}


