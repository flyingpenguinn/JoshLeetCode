/*
LC#273
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglish {
    // 1. inners are strings too!
    // 2. dont miss the space before base
    private int[] bases = {1000_000_000, 1000_000, 1000, 1};
    private String[] lion = {"Billion", "Million", "Thousand", ""};
    private String[] under20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    // under20 up to 19
    private String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    // tens from twenty

    public String numberToWords(int num) {
        // num can't <0
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int bi = 0; bi < bases.length; bi++) {
            int base = bases[bi];
            int units = num / base;
            if (units > 0) {
                String inner = convert1000(units);
                addResult(sb, lion[bi], inner);
            }
            num = num % base;
        }
        return sb.toString();
    }


    private String convert1000(int num) {
        // num<1000
        StringBuilder sb = new StringBuilder();
        int hun = num / 100;
        addResult(sb, "Hundred", under20[hun]); // hun<10
        num = num % 100;
        // num<100
        if (num < 20) {
            addResult(sb, "", under20[num]);
        } else {
            int tenDigit = num / 10;
            int singleDigit = num % 10;
            addResult(sb, "", tens[tenDigit]);
            addResult(sb, "", under20[singleDigit]);
        }
        return sb.toString();
    }

    private void addResult(StringBuilder sb, String base, String num) {
        if (num.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(num);
        if (!base.isEmpty()) {
            sb.append(" ");  // must add space here!
            sb.append(base);
        }
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglish().numberToWords(123));
        System.out.println(new IntegerToEnglish().numberToWords(12345678));
    }
}
