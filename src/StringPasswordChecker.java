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
            // for each len%3==1 we just need to remove two char to reduce one replacement
            replace -= eatentwos / 2;
            deleted -= eatentwos;
            // for the rest, instead of replacing, we will just delete all 3 thus saving one more replacement
            if (deleted >= 0) {
                replace = Math.max(0, replace - deleted / 3);
            }
            // we are then left with the replacements we have to do plus missings and deletions that can't be converted to replacements
            int total = n - 20 + Math.max(replace, missing);
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println(new StringPasswordChecker().strongPasswordChecker("FFFFFFFFFFFFFFF11111111111111111111AAA"));//1


    }
}
