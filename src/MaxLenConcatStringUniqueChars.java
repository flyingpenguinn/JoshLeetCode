import java.util.*;

/*
LC#1239
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26


Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
 */
public class MaxLenConcatStringUniqueChars {

    // can skip strings with duplicated chars to make it even faster
    Map<Integer, Map<BitSet, Integer>> dp = new HashMap<>();

    public int maxLength(List<String> arr) {
        BitSet used = new BitSet(26);
        return dp(0, arr, used);
    }

    private int dp(int i, List<String> a, BitSet used) {
        if (i == a.size()) {
            return used.cardinality();
        }
        Map<BitSet, Integer> cm = dp.getOrDefault(i, new HashMap<>());
        Integer cached = cm.get(used);
        if (cached != null) {
            return cached;
        }
        String cur = a.get(i);
        boolean good = true;
        BitSet nused = (BitSet) used.clone();
        for (int j = 0; j < cur.length(); j++) {
            int bit = cur.charAt(j) - 'a';
            if (nused.get(bit)) {
                good = false;
                break;
            }
            nused.set(bit);
        }
        int rt = dp(i + 1, a, used);
        if (good) {
            int with = dp(i + 1, a, nused);
            rt = Math.max(with, rt);

        }
        cm.put(used, rt);
        dp.put(i, cm);
        return rt;
    }

    public static void main(String[] args) {
        String[] input = {"yy", "bkhwmpbiisbldzknpm"};
        System.out.println(new MaxLenConcatStringUniqueChars().maxLength(Arrays.asList(input)));
    }
}
