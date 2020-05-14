import java.util.ArrayList;
import java.util.List;

/*
LC#448
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */
public class FindAllNumbersDisappered {
    public List<Integer> findDisappearedNumbers(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int pos = Math.abs(a[i]) - 1;
            a[pos] = -Math.abs(a[pos]);
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                r.add(i + 1);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(new FindAllNumbersDisappered().findDisappearedNumbers(nums));
    }
}
