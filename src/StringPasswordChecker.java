/*
LC#420
A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion or replace of any one character are all considered as one change.
 */
public class StringPasswordChecker {
    // if >=20, we delete till it's 20, but try to delete artfully so as to cover some repeated char removal
    public int strongPasswordChecker(String s) {
        int lc = 1;
        int uc = 1;
        int dc = 1;
        int n = s.length();
        int i = 0;
        int ones = 0;
        int twos = 0;
        int replace = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                uc = 0;
            }
            if (Character.isLowerCase(c)) {
                lc = 0;
            }
            if (Character.isDigit(c)) {
                dc = 0;
            }
            int j = i + 1;
            while (j < n && s.charAt(j) == c) {
                j++;
            }
            int len = j - i;
            if (len >= 3 && len % 3 == 0) {
                ones++;
            } else if (len >= 3 && len % 3 == 1) {
                twos++;
            }
            replace += len / 3;// every 3 elements we need to replace one
            // i...j-1 the same
            i = j;
        }
        int missing = lc + uc + dc;
        if (n < 6) {
            // can solve by just inserting missing chars to break the repetition
            return Math.max(missing, 6 - n);
        } else if (n <= 20) {
            // no insertion, just replace chars to get missing numbers, plus the repetition breakers
            return Math.max(missing, replace);
        } else {
            int deleted = n - 20;
            // for each len%3==0 we just need to remove one char to reduce one replacement
            int eatenones = Math.min(deleted, ones);
            replace -= eatenones;
            deleted -= eatenones;
            int eatentwos = Math.min(deleted, twos * 2);
            // for each len%3==1 we just need to make two char to reduce one replacement
            replace -= eatentwos / 2;
            deleted -= eatentwos;
            // for the rest, we dont want to delete to <20 because it's worse than just replacing the middle one in a 3-group
            if (deleted >= 0) {
                replace = Math.max(0, replace - deleted / 3);
            }
            // some replace can be used to add missing ones. if not enough convert existing chars to missing ones
            int total = n - 20 + Math.max(replace, missing);
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println(new StringPasswordChecker().strongPasswordChecker("..................!!!"));//7
        System.out.println(new StringPasswordChecker().strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaa"));//28
        System.out.println(new StringPasswordChecker().strongPasswordChecker("ABABABABABABABABABAB1"));//2
        System.out.println(new StringPasswordChecker().strongPasswordChecker("hoAISJDBVWD09232UHJEPODKNLADU1"));//10
        System.out.println(new StringPasswordChecker().strongPasswordChecker("aaa111"));//2
        System.out.println(new StringPasswordChecker().strongPasswordChecker("..."));//3
        System.out.println(new StringPasswordChecker().strongPasswordChecker("aabbcc"));//2
        System.out.println(new StringPasswordChecker().strongPasswordChecker(""));//6
        System.out.println(new StringPasswordChecker().strongPasswordChecker("aB3cc"));//1


    }
}

class StringPasswordCheckerDp {
    // O(480n2).... each position we have 8 choices...
    public int strongPasswordChecker(String s) {
        int n = s.length();
        if (n == 0) {
            return 6;
        }
        dp = new Integer[n + 1][n + 1][4][2][2][2][21];
        return dos(s, 0, n, 0, 0, 0, 0, 0);
    }

    int Max = 1000000000;
    Integer[][][][][][][] dp;

    private int dos(String s, int i, int last, int cons, int lc, int uc, int dc, int count) {
        if (cons >= 3) {
            return Max;
        }

        if (count > 20) {
            int need = countneed(lc, uc, dc);
            count += need;
            if (count > 20) {
                need += s.length() - 20;
            }
            return need;
        }

        if (i == s.length()) {
            int need = countneed(lc, uc, dc);
            count += need;
            if (count < 6) {
                need += 6 - count;
            }
            return need;
        }

        if (dp[i][last][cons][lc][uc][dc][count] != null) {
            return dp[i][last][cons][lc][uc][dc][count].intValue();
        }
        int n = s.length();
        // remove
        int way1 = 1 + dos(s, i + 1, last, cons, lc, uc, dc, count);
        char c = s.charAt(i);
        int nlc = lc == 1 || Character.isLowerCase(c) ? 1 : 0;
        int nuc = uc == 1 || Character.isUpperCase(c) ? 1 : 0;
        int ndc = dc == 1 || Character.isDigit(c) ? 1 : 0;
        // keep this char
        int ncons = last == n || c != s.charAt(last) ? 1 : cons + 1;
        int way2 = dos(s, i + 1, i, ncons, nlc, nuc, ndc, count + 1);
        // change to a different lc
        int way3 = 1 + dos(s, i + 1, n, 1, 1, uc, dc, count + 1);
        // change to uc
        int way4 = 1 + dos(s, i + 1, n, 1, lc, 1, dc, count + 1);
        // change to digit
        int way5 = 1 + dos(s, i + 1, n, 1, lc, uc, 1, count + 1);
        // insert a different lc in front of this one
        int way6 = 1 + dos(s, i + 1, i, 1, 1, uc, dc, count + 2);
        // insert uc
        int way7 = 1 + dos(s, i + 1, i, 1, lc, 1, dc, count + 2);
        // insert dc
        int way8 = 1 + dos(s, i + 1, i, 1, lc, uc, 1, count + 2);
        int rt = min(way1, min(way2, min(way3, min(way4, min(way5, min(way6, min(way7, way8)))))));
        dp[i][last][cons][lc][uc][dc][count] = Integer.valueOf(rt);
        return rt;
    }

    int countneed(int lc, int uc, int dc) {
        int need = 0;
        need += lc == 0 ? 1 : 0;
        need += uc == 0 ? 1 : 0;
        need += dc == 0 ? 1 : 0;
        return need;
    }

    int min(int a, int b) {
        return Math.min(a, b);
    }
}
