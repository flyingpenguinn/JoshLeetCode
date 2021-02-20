/*
LC#13 Same description and range as LC@12
 */

public class RomanToInteger {
    // just a table lookup
    private String[] rom = {"IV", "IX", "XL", "XC", "CD", "CM", "V", "I", "X", "L", "C", "D", "M"};
    private int[] points = {4, 9, 40, 90, 400, 900, 5, 1, 10, 50, 100, 500, 1000};

    public int romanToInt(String s) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            for (int j = 0; j < rom.length; j++) {
                if (s.startsWith(rom[j], i)) {
                    i += rom[j].length();
                    res += points[j];
                    break;
                }
            }
        }
        return res;
    }
}
