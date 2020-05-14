/*
LC#738

Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].
 */

public class MonotoneIncreasingDigits {
    // solution must be xxx(all 9 after) if it's not num itself
    public int monotoneIncreasingDigits(int num) {
        String s = String.valueOf(num);
        char[] cs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            if (cs[i] > cs[i + 1]) {
                int j = i;
                // find the last j that needs --
                for (; j >= 0; j--) {
                    if (cs[j] > cs[j + 1]) {
                        cs[j]--;
                    } else {
                        break;
                    }
                }
                // j+1 is the num needs --. from +2 onwards it's all 9
                int k = j + 2;
                while (k < n) {
                    cs[k++] = '9';
                }

                return Integer.valueOf(new String(cs));
            }
        }
        return Integer.valueOf(s);
    }
}

class MonotoneIIncreasingRecursion {
    // try follow number. if not possible, from that impossible pos onward all 9
    public int monotoneIncreasingDigits(int n) {
        String sn = String.valueOf(n);
        return Integer.valueOf(dom(sn, 0));
    }

    String dom(String s, int i) {
        int len = s.length();
        if (i == s.length()) {
            return "";
        }
        int d = s.charAt(i) - '0';
        int l = i == 0 ? -1 : s.charAt(i - 1) - '0';
        // System.out.println(l+","+d);
        if (l > d) {
            return null;
        }
        String w1 = dom(s, i + 1);
        if (w1 != null) {
            return d + w1;
        }
        if (d == 0 || d - 1 < l) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i + 1; j < len; j++) {
            sb.append('9');
        }
        return (d - 1) + sb.toString();
    }
}
