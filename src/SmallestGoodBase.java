/*
LC#483
For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format.

Example 1:

Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.


Example 2:

Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.


Example 3:

Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.


Note:

The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.
 */
public class SmallestGoodBase {
    // traverse digits, binary search the base. search digits from big to small
    public String smallestGoodBase(String ns) {
        long n = Long.valueOf(ns);
        // bits of 1
        int digits = Long.bitCount(n);
        for (int i = digits; i >= 1; i--) {
            long cur = getbase(i, n);
            if (cur != -1) {
                return String.valueOf(cur);
            }
        }
        return "";
    }

    private long getbase(int bits, long n) {
        long l = 2;
        long u = n - 1;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            int diff = diff(mid, bits, n);
            if (diff == 0) {
                return mid;
            } else if (diff < 0) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return -1;
    }

    private int diff(long base, int bits, long n) {
        long r = 0;
        while (bits > 0) {
            if (r > (Long.MAX_VALUE - base) / base) {
                return 1;
            }
            r = r * base + 1;
            bits--;
        }
        return Long.compare(r, n);
    }

    public static void main(String[] args) {
        System.out.println(new SmallestGoodBase().smallestGoodBase("919818571896748567"));
    }
}
