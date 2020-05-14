import java.util.*;

/*
LC#869
Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.



Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true


Note:

1 <= N <= 10^9
 */
public class ReorderedPow2 {
    // can encode each number to a long too
    Map<Integer, Set<String>> map = new HashMap<>();
    int Max = 1000000000;

    public boolean reorderedPowerOf2(int n) {
        if (n < 0) {
            return false;
        }
        for (int b = 1; b <= Max; b *= 2) {
            String sb = String.valueOf(b);
            char[] sortedsb = sb.toCharArray();
            Arrays.sort(sortedsb);
            String sortedsbstr = new String(sortedsb);
            map.computeIfAbsent(sortedsb.length, k -> new HashSet<>()).add(sortedsbstr);
        }
        // System.out.println(map);
        String sn = String.valueOf(n);
        char[] sortedsn = sn.toCharArray();
        Arrays.sort(sortedsn);
        String sortedsnstr = new String(sortedsn);
        // System.out.println(sortedsnstr);

        return map.get(sortedsn.length).contains(sortedsnstr);
    }
}

class ReorderPow2Elegant {
    public boolean reorderedPowerOf2(int num) {
        long c = counter(num);
        for (int i = 0; i < 32; i++)
            if (counter(1 << i) == c) return true;
        return false;
    }

    private long counter(int num) {
        long res = 0;
        for (; num > 0; num /= 10) res += (int) Math.pow(10, num % 10);
        return res;
    }
}
