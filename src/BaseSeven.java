/*
LC#504
Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].
 */
public class BaseSeven {
    // we would convert other bases in the same way
    public String convertToBase7(int n) {
        if (n < 0) {
            return "-" + convertToBase7(-n);
        }
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = n % 7;
            sb.append(mod);
            n = n / 7;
        }
        return sb.reverse().toString();
    }
}
