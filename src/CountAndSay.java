/*
LC#38
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.



Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"
 */

public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder last = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int cc = 0;
            int lastn = last.length();
            for (int j = 0; j <= lastn; j++) {
                if (j > 0 && (j == lastn || (last.charAt(j) != last.charAt(j - 1)))) {
                    sb.append(cc);
                    sb.append(last.charAt(j - 1));
                    cc = 1;
                } else {
                    cc++;
                }
            }
            last = sb;
        }
        return last.toString();
    }


}
