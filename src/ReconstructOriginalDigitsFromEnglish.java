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
    String[] digitstr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public String originalDigits(String s) {
        int n = s.length();
        int[] map = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map[c - 'a']++;
        }
        int[] dc = new int[10];
        dc[0] = map['z' - 'a'];
        dc[2] = map['w' - 'a'];
        dc[8] = map['g' - 'a'];
        dc[3] = map['h' - 'a'] - dc[8];
        dc[4] = map['u' - 'a'];

        dc[6] = map['x' - 'a'];
        dc[1] = map['o' - 'a'] - dc[2] - dc[4] - dc[0];
        dc[7] = map['s' - 'a'] - dc[6];
        dc[5] = map['v' - 'a'] - dc[7];
        dc[9] = map['i' - 'a'] - dc[5] - dc[6] - dc[8];


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < dc[i]; j++) {
                sb.append(i);
            }
        }
        //System.out.println(Arrays.toString(map));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReconstructOriginalDigitsFromEnglish().originalDigits("owoztneoer"));
    }
}
