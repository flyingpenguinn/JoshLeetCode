import java.util.ArrayList;
import java.util.List;

/*
LC#1291
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.



Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]


Constraints:

10 <= low <= high <= 10^9
 */
public class SequentialDigits {
    List<Integer> r = new ArrayList<>();
    int low;
    int high;

    public List<Integer> sequentialDigits(int low, int high) {
        this.low = low;
        this.high = high;
        int lowlen = String.valueOf(low).length();
        int highlen = String.valueOf(high).length();
        for (int i = lowlen; i <= highlen; i++) {
            for (int j = 1; j <= 9; j++) {
                gen(j, i, j, 1);
            }
        }
        return r;
    }

    private void gen(int last, int len, int cur, int i) {
        if (i == len) {
            if (cur >= low && cur <= high) {
                r.add(cur);
            }
            return;
        }
        if (last + 1 <= 9) {
            gen(last + 1, len, cur * 10 + last + 1, i + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SequentialDigits().sequentialDigits(10, 1000000000));
    }
}
