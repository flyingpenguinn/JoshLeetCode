import java.io.IOException;
import java.util.*;

/*
LC#1505
Given a string num representing the digits of a very large integer and an integer k.

You are allowed to swap any two adjacent digits of the integer at most k times.

Return the minimum integer you can obtain also as a string.



Example 1:


Input: num = "4321", k = 4
Output: "1342"
Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
Example 2:

Input: num = "100", k = 1
Output: "010"
Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
Example 3:

Input: num = "36789", k = 1000
Output: "36789"
Explanation: We can keep the number without any swaps.
Example 4:

Input: num = "22", k = 22
Output: "22"
Example 5:

Input: num = "9438957234785635408", k = 23
Output: "0345989723478563548"


Constraints:

1 <= num.length <= 30000
num contains digits only and doesn't have leading zeros.
1 <= k <= 10^9
 */
public class MinPossibleIntegerAfterAtmostKSwaps {
    // brute force accepted...
    // @todo segment tree?
    public String minInteger(String s, int k) {
        int n = s.length();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(s.charAt(i) - '0');
        }
        if (k >= n * (n - 1) / 2) {
            Collections.sort(a);
        } else {
            for (int i = 0; i < n && k > 0; i++) {
                int min = a.get(i);
                int mini = i;
                int j = i + 1;
                int walked = 0;
                while (j < n && walked < k) {
                    if (a.get(j) < min) {
                        min = a.get(j);
                        mini = j;
                    }
                    walked++;
                    j++;
                }
                a.remove(mini);
                a.add(i, min);
                k -= (mini - i);
            }
        }
        return arrayToString(a);
    }

    private String arrayToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            sb.append(a.get(i));
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(new MinPossibleIntegerAfterAtmostKSwaps().minInteger("4321", 4));
        System.out.println(new MinPossibleIntegerAfterAtmostKSwaps().minInteger("9000900", 3));

        System.out.println(new MinPossibleIntegerAfterAtmostKSwaps().minInteger("9438957234785635408", 23));
        System.out.println(new MinPossibleIntegerAfterAtmostKSwaps().minInteger("100", 1));
        System.out.println(new MinPossibleIntegerAfterAtmostKSwaps().minInteger("36789", 1000));
    }
}
