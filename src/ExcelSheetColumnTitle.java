/*
LC#168
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"
 */
public class ExcelSheetColumnTitle {
    // 26*26*a+26*b+c. a,b,c <=26 if %26==0 we know c==26
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            //  System.out.println(n);
            int cur = 0;
            if (n % 26 == 0) {
                cur = 26;
            } else {
                cur = n % 26;
            }
            sb.append((char) ('A' - 1 + cur));
            n = (n - cur) / 26;
        }
        return sb.reverse().toString();
    }
}
