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
    int[] m = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};

    public int strobogrammaticInRange(String low, String high) {
        long ch = count(high);
        long cl = count(low);
        long rt = ch - cl;
        if (isstrobo(low)) {
            rt++;
        }
        return rt < 0 ? 0 : (int) rt;
    }

    // strobo number from 0 to n inclusive
    private long count(String n) {
        int len = n.length();
        long r = 0;
        for (int i = 1; i < len; i++) {
            r += allstrobo(i, false);
        }
        return r + nobigger(len, n, 0, false, true);
    }

    // strobo number <=s at range i...n-1-i
    // biggercharallowed means whether we can have bigger chars than what s has
    private long nobigger(int n, String s, int i, boolean biggercharallowed, boolean eqallowed) {
        int r = 0;
        int ni = s.charAt(i) - '0';
        if (i == n / 2) {
            if (n % 2 == 1) {
                for (int j = 0; j <= 9; j++) {
                    if (m[j] != j) {
                        continue;
                    }
                    if (j < ni || (j > ni && biggercharallowed) || (j == ni && eqallowed)) {
                        r++;
                    }
                }
                return r;
            } else {
                return eqallowed ? 1 : 0;
                // key: if eq has been invalid and we are just looking for < digits then 0
                // this is the only place to check eqallowed
            }
        }
        int start = i == 0 ? 1 : 0;
        for (int j = start; j <= 9; j++) {
            if (m[j] < 0) {
                continue;
            }
            if (biggercharallowed) {
                r += nobigger(n, s, i + 1, true, true);
            } else if (j < ni) {
                r += nobigger(n, s, i + 1, true, true);
            } else if (j > ni) {
                break;
            } else {
                int vk = s.charAt(n - 1 - i) - '0';
                if (m[j] > vk) {
                    // s==6...0 vs 6...9  we are not going to allow eq any more
                    r += nobigger(n, s, i + 1, false, false);
                } else if (m[j] < vk) {
                    // 999959. we will get 9..6<9..9 and this will make eqallowed true
                    r += nobigger(n, s, i + 1, false, true);
                } else {
                    // s==8...8 vs 8...8, we will keep allowing eq if it is allowed before
                    r += nobigger(n, s, i + 1, false, eqallowed);
                }
            }
        }
        return r;
    }

    // all strobo numbers of length i
    private long allstrobo(int i, boolean allowzero) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 3;// 0,1,8
        }
        if (allowzero) {
            return 5 * allstrobo(i - 2, true);
        } else {
            return 4 * allstrobo(i - 2, true);
        }
    }

    private boolean isstrobo(String low) {
        int i = 0;
        int j = low.length() - 1;
        while (i <= j) {
            if (m[low.charAt(i++) - '0'] != (low.charAt(j--) - '0')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new StroboNumberIIIEasier().strobogrammaticInRange("9695", "9697"));
        /*System.out.println(new StrobogrammaticNumberIII().strobogrammaticInRange("0", "20000")); //54

        System.out.println(new StrobogrammaticNumberIII().strobogrammaticInRange("0", "10000"));//39
        System.out.println(new StrobogrammaticNumberIII().strobogrammaticInRange("0", "1680"));//21
        System.out.println(new StrobogrammaticNumberIII().strobogrammaticInRange("50", "100"));//3
        System.out.println(new StrobogrammaticNumberIII().strobogrammaticInRange("69", "100"));//3
*/
    }
}


class StroboNumberIIIEasier {
    int curlen = 0;

    public int strobogrammaticInRange(String low, String high) {

        long lhigh = Long.valueOf(high).longValue();
        long llow = Long.valueOf(low).longValue();
        if (llow > lhigh) {
            return 0;
        }
        int nhigh = strobo(lhigh);
        int nlow = strobo(llow - 1);
        return nhigh - nlow;
    }

    int strobo(long n) {
        if (n < 0) {
            return 0;
        }
        int r = 0;
        String sn = String.valueOf(n);
        int len = sn.length();
        for (int i = 1; i <= len; i++) {
            curlen = 0;
            doc(i, i, n, "", "", sn.charAt(0) - '0', true, i == len);
            r += curlen;
        }
        return r;
    }

    int[] m1 = {0, 1, 6, 8, 9};
    int[] m2 = {0, 1, 9, 8, 6};

    // i == remaining length
    private void doc(int i, int len, long limit, String cur, String rcur, int firstnum, boolean first, boolean dofilter) {
        if (i == 0) {
            if (isgood(cur, rcur, limit)) {
                curlen++;
            }
            return;
        }
        if (i == 1) {
            for (int j = 0; j < m1.length; j++) {
                if (m1[j] == m2[j]) {
                    doc(i - 1, len, limit, cur + m1[j], cur, firstnum, false, dofilter);
                }
            }
        } else {
            for (int j = 0; j < m1.length; j++) {
                if (first && m1[j] == 0 && len > 1) {
                    continue;
                }
                if (first && m1[j] > firstnum && dofilter) {
                    break;
                }
                doc(i - 2, len, limit, cur + m1[j], m2[j] + rcur, firstnum, false, dofilter);
            }
        }
    }

    private boolean isgood(String cur, String rcur, long limit) {
        String rn = cur + rcur;
        if (Long.valueOf(rn).longValue() <= limit) {
            return true;
        }
        return false;
    }

}