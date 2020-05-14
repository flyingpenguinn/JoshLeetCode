import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1122
Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.



Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]


Constraints:

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
Each arr2[i] is distinct.
Each arr2[i] is in arr1.
 */
public class RelativeSortArray {

    // counting sort
    public int[] relativeSortArray(int[] a1, int[] a2) {
        int[] reg = new int[1001];
        for (int i = 0; i < a1.length; i++) {
            reg[a1[i]]++;
        }
        int rp = 0;
        int[] r = new int[a1.length];
        for (int i = 0; i < a2.length; i++) {
            for (int j = 0; j < reg[a2[i]]; j++) {
                r[rp++] = a2[i];
            }
            reg[a2[i]] = 0;
        }
        for (int i = 0; i < reg.length; i++) {
            for (int j = 0; j < reg[i]; j++) {
                r[rp++] = i;
            }
        }
        return r;
    }
}

class RelativeSortArrayShorter {
    public int[] relativeSortArray(int[] a1, int[] a2) {
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < a2.length; i++) {
            rank.put(a2[i], i);
        }
        // +1001 to push elements not in a2 to the bottom
        return Arrays.stream(a1).boxed().sorted((a, b) -> Integer.compare(rank.getOrDefault(a, 1001 + a), rank.getOrDefault(b, 1001 + b))).mapToInt(i -> i).toArray();
    }
}