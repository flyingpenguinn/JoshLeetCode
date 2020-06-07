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
    int[] bases = {1000_000_000, 1000_000, 1000};
    String[] lion = {"Billion", "Million", "Thousand"};
    String[] under20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};


    public String numberToWords(int n) {
        if (n == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lion.length; i++) {
            if (n >= bases[i]) {
                int level = n / bases[i];
                String inner = convert(level);
                add(sb, inner, lion[i]);
                n -= bases[i] * level;
            }
        }
        add(sb, convert(n), "");
        return sb.toString();
    }

    // n>1 <=999
    String convert(int n) {
        StringBuilder sb = new StringBuilder();
        add(sb, under20[n / 100], "Hundred");
        int stub = n % 100;
        if (stub < 20) {
            add(sb, under20[stub], "");
        } else {
            add(sb, tens[stub / 10], "");
            add(sb, under20[stub % 10], "");
        }
        return sb.toString();
    }

    void add(StringBuilder sb, String str, String counter) {
        if (str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(str);
        if(!counter.isEmpty()){
            sb.append(" ");
            sb.append(counter);
        }
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglish().numberToWords(123));
        System.out.println(new IntegerToEnglish().numberToWords(12345678));
    }
}
