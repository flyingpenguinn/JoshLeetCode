import java.util.Arrays;

/*
LC#423
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
 */
public class ReconstructOriginalDigitsFromEnglish {

    // use the uniqueness of each english word!
    private String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private char[] uniq = {'z', 'o', 'w', 'h', 'u', 'f', 'x', 's', 'g', 'e'}; // this char is used to count for each number
    private int[] seq = {6, 0, 4, 2, 1, 8, 3, 5, 7, 9}; // we solve in this order
    private int[] sm = new int[26];

    public String originalDigits(String s) {

        for (int i = 0; i < s.length(); i++) {
            int cind = s.charAt(i) - 'a';
            sm[cind]++;
        }
        int[] rm = new int[10];
        for (int j = 0; j < seq.length; j++) {
            int ind = seq[j]; // the number itself
            char uc = uniq[ind]; // the uniq char of this num
            rm[ind] = sm[uc - 'a'];
            deduct(ind, rm[ind]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rm.length; i++) {
            int count = rm[i];
            while (count > 0) {
                sb.append(i);
                count--;
            }
        }
        return sb.toString();
    }

    private void deduct(int i, int c) {
        String str = nums[i];
        for (int j = 0; j < str.length(); j++) {
            int cind = str.charAt(j) - 'a';
            sm[cind] -= c;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReconstructOriginalDigitsFromEnglish().originalDigits("owoztneoer"));
    }
}

