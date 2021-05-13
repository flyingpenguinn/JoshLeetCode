import java.util.ArrayList;
import java.util.List;

/*
LC#248
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticNumberIII {
    // use bigger allowed and eq allowed to filter bad numbers
    // O(5^n/2) at most
    // no string needed at all!
    private int[] dig = {0, 1, 8, 6, 9};
    private int[] mdig = {0, 1, 8, 9, 6};
    private int res = 0;
    private long llow = 0;
    private long lhigh = 0;

    private void countsame(int i, int len) {
        docountsame(0, len, 0, 0, 1);
    }

    private void docountsame(int i, int len, long cur1, long cur2, long base) {
        if (i > len / 2) {
            return;
        }
        int start = i == 0 ? 1 : 0;   // 0, 1, 8. if i==0 then we shoudlnt put 0
        for (int j = start; j < 3; j++) {
            long curnum = cur1 * (base * 10) + dig[j] * base + cur2;
            if (curnum >= llow && curnum <= lhigh) {
                res++;
            }
        }
        // actually getting 0 once here
        long curnum = cur1 * base + cur2;
        if (curnum >= llow && curnum <= lhigh) {
            res++;
        }
        for (int j = start; j < 5; j++) {
            docountsame(i + 1, len, cur1 * 10 + dig[j], mdig[j] * base + cur2, base * 10);
        }
    }

    public int strobogrammaticInRange(String low, String high) {
        lhigh = Long.valueOf(high);
        llow = Long.valueOf(low);
        if (lhigh < llow) {
            return 0;
        }
        countsame(0, high.length());
        return res;
    }
}