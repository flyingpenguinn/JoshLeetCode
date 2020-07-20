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
    public int[] twoSum(int[] a, int t) {
        if(a==null){
            return new int[0];
        }
        Map<Integer,Integer> m = new HashMap<>();
        for(int i=0; i<a.length;i++){
            // up to i-1 not found
            if(m.containsKey(t-a[i])){
                return new int[]{m.get(t-a[i]), i};
            }
            m.put(a[i], i);
        }
        return new int[0];
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
