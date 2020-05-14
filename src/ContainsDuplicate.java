import java.util.HashSet;
import java.util.Set;

/*
LC#217
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
 */
public class ContainsDuplicate {
    // if sort- Onlgn, o1 space, a good trade off
    public boolean containsDuplicate(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            if (set.contains(ai)) {
                return true;
            }
            set.add(ai);
        }
        return false;
    }
}
