/*
LC#13 Same description and range as LC@12
 */

public class RomanToInteger {
    public int romanToInt(String s) {
        int r = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.startsWith("CM", i)) {
                r += 900;
                i += 2;
            } else if (s.startsWith("CD", i)) {
                r += 400;
                i += 2;
            } else if (s.startsWith("XC", i)) {
                r += 90;
                i += 2;
            } else if (s.startsWith("XL", i)) {
                r += 40;
                i += 2;
            } else if (s.startsWith("IX", i)) {
                r += 9;
                i += 2;
            } else if (s.startsWith("IV", i)) {
                r += 4;
                i += 2;
            } else if (s.startsWith("M", i)) {
                r += 1000;
                i++;
            } else if (s.startsWith("D", i)) {
                r += 500;
                i++;
            } else if (s.startsWith("C", i)) {
                r += 100;
                i++;
            } else if (s.startsWith("L", i)) {
                r += 50;
                i++;
            } else if (s.startsWith("X", i)) {
                r += 10;
                i++;
            } else if (s.startsWith("V", i)) {
                r += 5;
                i++;
            } else if (s.startsWith("I", i)) {
                r++;
                i++;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return r;
    }
}
