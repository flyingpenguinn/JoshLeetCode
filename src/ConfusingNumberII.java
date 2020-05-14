import java.util.*;

/*
LC#1088
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.



Example 1:

Input: 20
Output: 6
Explanation:
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation:
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].


Note:

1 <= N <= 10^9
 */
public class ConfusingNumberII {
    int curlen = 0;

    public int confusingNumberII(int n) {
        int r = 0;
        String sn = String.valueOf(n);
        int len = sn.length();
        for (int i = 1; i <= len; i++) {
            curlen = 0;
            doc(i, n, 0, sn.charAt(0) - '0', true, i == len);
            r += curlen;
        }
        return r;
    }

    int[] cand = {0, 1, 6, 8, 9};
    int[] map = {0, 1, -1, -1, -1, -1, 9, 7, 8, 6};

    // i == remaining length
    private void doc(int i, int limit, long cur, int firstnum, boolean first, boolean dofilter) {
        if (i == 0) {
            if (isconf(cur, limit)) {
                curlen++;
            }
            return;
        }
        for (int j = 0; j < cand.length; j++) {
            if (first && cand[j] == 0) {
                continue;
            }
            if (first && cand[j] > firstnum && dofilter) {
                break;
            }
            doc(i - 1, limit, cur * 10 + cand[j], firstnum, false, dofilter);
        }
    }

    private boolean isconf(long cur, int n) {
        if (cur > n) {
            return false;
        }
        String scur = String.valueOf(cur);
        int i = 0;
        int j = scur.length() - 1;
        while (i <= j) {
            if (scur.charAt(j) - '0' == map[scur.charAt(i) - '0']) {
                i++;
                j--;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new ConfusingNumberII().confusingNumberII(20));
        System.out.println(new ConfusingNumberII().confusingNumberII(999999999));

        System.out.println(new ConfusingNumberII().confusingNumberII(1000000000));

    }

}

class ConfusingNumberDigitalQuicker {
    // longer, vut takes only single digit ms
    public int confusingNumberII(int num) {
        char[] sn = String.valueOf(num).toCharArray();
        int n = sn.length;
        return conf(n, sn);
    }

    int allvalid(int i, int n) {
        if (i == n - 1) {
            return i != 0 ? 5 : 4;
        }
        int c = i != 0 ? 5 : 4;
        return c * allvalid(i + 1, n);
    }

    int conf(int n, char[] sn) {
        int r = 0;
        for (int i = 1; i < n; i++) {
            int av = allvalid(0, i);
            int as = allstrobo(0, i);
            //        System.out.println(av + "-" + as);

            r += av - as;
        }
        int vn = validn(0, sn, false);
        int snr = strobon(0, sn, false, true);
        //     System.out.println(vn + "-" + snr);

        r += vn - snr;
        return r;
    }

    int allstrobo(int i, int n) {
        if (i == n / 2) {
            if (n % 2 == 0) {
                return 1;
            } else {
                return i != 0 ? 3 : 2; //018
            }
        }
        int c = i != 0 ? 5 : 4;
        return c * allstrobo(i + 1, n);
    }

    int[] m = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};

    // n=sn.len digit strobo num <=sn
    // whether we allow bigger and eq number

    int validn(int i, char[] sn, boolean ba) {
        int n = sn.length;
        if (i == n) {
            return 1;
        }
        int vi = sn[i] - '0';
        int r = 0;
        int st = i != 0 ? 0 : 1;
        for (int j = st; j <= 9; j++) {
            if (m[j] < 0) {
                continue;
            }
            if (ba) {
                r += validn(i + 1, sn, ba);
            } else {
                if (j > vi) {
                    break;
                } else if (j < vi) {
                    r += validn(i + 1, sn, true);
                } else {
                    r += validn(i + 1, sn, false);
                }
            }
        }
        return r;
    }

    int strobon(int i, char[] sn, boolean ba, boolean ea) {
        int n = sn.length;
        int vi = sn[i] - '0';
        int r = 0;
        int st = i != 0 ? 0 : 1;
        if (i == n / 2) {
            if (n % 2 == 0) {
                return ea ? 1 : 0;
            } else {
                for (int j = st; j <= 9; j++) {
                    if (m[j] < 0 || m[j] != j) {
                        continue;
                    }
                    if (ba) {
                        r++;
                    } else {
                        if (j > vi) {
                            break;
                        } else if (j < vi) {
                            r++;
                        } else {
                            r += ea ? 1 : 0;
                        }
                    }
                }
                return r;
            }
        }
        for (int j = st; j <= 9; j++) {
            if (m[j] < 0) {
                continue;
            }
            if (ba) {
                r += strobon(i + 1, sn, ba, ea);
            } else {
                if (j > vi) {
                    break;
                } else if (j < vi) {
                    r += strobon(i + 1, sn, true, true);
                } else {
                    // j==vi
                    int vk = sn[n - 1 - i] - '0';
                    if (m[j] < vk) {
                        r += strobon(i + 1, sn, ba, true);
                        // 9...6 vs 9...7.
                        // note ea = true because at k... digits this number is smaller
                    } else if (m[j] > vk) {
                        r += strobon(i + 1, sn, ba, false);
                        // 988..6 vs 988..5 must remember this 6>5 we must do a smaller number at some point
                    } else {
                        // otherwise keep looking at later ones we may have chance to plant a smaller number
                        r += strobon(i + 1, sn, ba, ea);
                    }
                }
            }
        }
        return r;
    }
}
