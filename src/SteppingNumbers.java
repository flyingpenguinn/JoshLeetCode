import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/*
LC#1215

A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. For example, 321 is a Stepping Number while 421 is not.

Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.



Example 1:

Input: low = 0, high = 21
Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]


Constraints:

0 <= low <= high <= 2 * 10^9
 */
public class SteppingNumbers {

    // 3000 numbers in all so just recursion and sort....
    List<Integer> r = new ArrayList<>();

    public List<Integer> countSteppingNumbers(int low, int high) {

        for (int i = 0; i <= 9; i++) {
            gen(i, low, high);
        }
        Collections.sort(r);
        return r;
    }

    private void gen(long num, int low, int high) {
        if (num >= low && num <= high) {
            r.add((int) num);
        } else if (num > high) {
            return;
        }
        if (num == 0) {
            return;
        }
        long last = num % 10;
        if (last + 1 <= 9) {
            gen(num * 10 + last + 1, low, high);
        }
        if (last - 1 >= 0) {
            gen(num * 10 + last - 1, low, high);
        }
    }
}
