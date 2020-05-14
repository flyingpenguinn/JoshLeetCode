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
    String[] lion = {"Thousand", "Million", "Billion"};
    String[] under20 = {"*", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"*", "*", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};


    String space = " ";
    int max = 1000000000;

    public String numberToWords(int n) {
        if (n == 0) {
            return "Zero";
        }
        int base = max;
        StringBuilder sb = new StringBuilder();
        int i = lion.length - 1;
        while (n > 0) {
            int cur = n / base;
            if (cur != 0) {
                String curs = within1000(cur);
                sb.append(curs);
                if (base > 1) {
                    sb.append(lion[i] + space);
                }
                n -= base * cur;
            }
            i--;
            base /= 1000;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String within1000(int n) {

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            if (n < 20) {
                // 1 to 19
                sb.append(under20[n] + space);
                break;
            } else if (n < 100) {
                // >=20 <100
                int cur = n / 10;
                sb.append(tens[cur] + space);
                n -= cur * 10;
            } else {
                // >=100
                int cur = n / 100;
                sb.append(under20[cur] + space);
                sb.append("Hundred ");
                n -= cur * 100;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglish().numberToWords(123));
        System.out.println(new IntegerToEnglish().numberToWords(12345678));
    }
}
