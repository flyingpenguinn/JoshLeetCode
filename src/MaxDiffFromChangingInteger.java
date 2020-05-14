/*
LC#1432
You are given an integer num. You will apply the following steps exactly two times:

Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
The new integer cannot have any leading zeros, also the new integer cannot be 0.
Let a and b be the results of applying the operations to num the first and second times, respectively.

Return the max difference between a and b.



Example 1:

Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
Example 2:

Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8
Example 3:

Input: num = 123456
Output: 820000
Example 4:

Input: num = 10000
Output: 80000
Example 5:

Input: num = 9288
Output: 8700


Constraints:

1 <= num <= 10^8
 */
public class MaxDiffFromChangingInteger {
    // change the first non nine number to 9
    // change the first number to 1 for min if it's not 1
    // otherwise change the first >=2 to 0
    public int maxDiff(int num) {
        String sn = (String.valueOf(num));
        int min = num;
        if (sn.charAt(0) != '1') {
            char c = sn.charAt(0);
            String newstr = sn.replaceAll(String.valueOf(c), "1");
            min = Integer.valueOf(newstr);
        } else {
            // otherwise change the first num >=2 to 0. note we can't touch 1 and make it 0 otherwise the number starts with 0
            int firstnon = 1;
            while (firstnon < sn.length()) {
                if (sn.charAt(firstnon) != '0' && sn.charAt(firstnon) != '1') {
                    break;
                } else {
                    firstnon++;
                }
            }
            if (firstnon != sn.length()) {
                char c = sn.charAt(firstnon);
                String newstr = sn.replaceAll(String.valueOf(c), "0");
                min = Integer.valueOf(newstr);
            }
        }
        int firstnonnine = 0;
        while (firstnonnine < sn.length()) {
            if (sn.charAt(firstnonnine) != '9') {
                break;
            } else {
                firstnonnine++;
            }
        }
        int max = num;
        if (firstnonnine != sn.length()) {
            char c = sn.charAt(firstnonnine);
            String newstr = sn.replaceAll(String.valueOf(c), "9");
            max = Integer.valueOf(newstr);
        }
        return max - min;
    }

}

class MaxDifferenceBruteForce {
    public int maxDiff(int n) {
        String sn = String.valueOf(n);
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            String curmax = sn.replaceAll(String.valueOf(i), "9");
            String curmin = sn.replaceAll(String.valueOf(i), "0");
            if (curmin.startsWith("0")) {
                curmin = sn.replaceAll(String.valueOf(i), "1");
            }
            max = Math.max(max, Integer.valueOf(curmax));
            min = Math.min(min, Integer.valueOf(curmin));
        }
        return max - min;
    }

}