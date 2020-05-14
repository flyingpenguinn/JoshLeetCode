import java.util.Arrays;
import java.util.Comparator;

/*
LC#179
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

    // NOT JUST DICT ORDER
    // say 30 vs 3, 330 > 303
    class IntComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            String a = String.valueOf(o1);
            String b = String.valueOf(o2);

            // only used concact when they have the same prefix
            if (a.startsWith(b) || b.startsWith(a)) {
                return -((a + b).compareTo(b + a));
            }
            return -((a).compareTo(b));
        }
    }

    public String largestNumber(int[] nums) {
        Integer[] r = new Integer[nums.length];
        int rp = 0;
        for (int i : nums) {
            r[rp++] = i;
        }
        Arrays.sort(r, new IntComparator());
        StringBuilder sb = new StringBuilder();
        for (Integer str : r) {
            sb.append(str);
        }
        return 0 == r[0] ? "0" : sb.toString();  // max num so just [0] is enough
    }
}
