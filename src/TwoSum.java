import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

https://leetcode.com/problems/two-sum/

 */

// can't sort then ij because this asks for index!
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            int other = target - nums[i];
            Integer otherIndex = map.get(other);
            if (otherIndex != null) {
                int[] r = new int[2];
                r[0] = otherIndex;
                r[1] = i;
                return r;
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

// THIS WOULD NOT AC: CAN'T SORT THEN FIND BECAUSE THIS ASKS FOR INDEXES
// THIS ALGO ONLY WORKS WHEN ARRAY IS SORTED
class TwoSumSort {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                int[] r = new int[2];
                r[0] = i;
                r[1] = j;
                return r;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }
}
