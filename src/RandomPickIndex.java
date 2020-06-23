import java.util.Random;

/*
LC#398
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */
public class RandomPickIndex {
    // reservoir sampling...
    /*
    For this problem we can take algorithm like this:

Draw the 1st char. If there is a second char, then we will hold 1st char by prob = 1/2, and replace the 1st char to 2nd char with prob = 1/2. After this step we suppose that the char is X now.

After then, if there is 3rd char, then we will hold the X with prob = 2/3 and replace X to 3rd char with prob = 1/3. Why do they hold the same prob to be picked?
Because:
Obviously, Prob(the 3rd char is picked) = 1/3;
Prob(the 2nd char is picked) = 1 * 1/2 * 2/3 = 1/3;
Prob(the 1st char is picked) = 1 * 1/2 * 2/3 = 1/3;

So we can say that when we now has n chars and there is still another char in the file, we can pick the other char with prob= 1/(n+1), also keep original char with prob = n/(n+1), then we can secure each char is picked with same prob = 1/(n+1), because prob = 1 * 1/2 * 2/3 * ···· * n/(n+1) = 1/(n+1).
     */
    class Solution {

        int[] a = null;

        public Solution(int[] nums) {
            this.a = nums;
        }

        Random rand = new Random();

        public int pick(int t) {
            int cand = -1;
            int pool = 1;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == t) {
                    int ran = rand.nextInt(pool++);
                    if (ran == 0) {
                        cand = i;
                    }
                }
            }
            return cand;
        }
    }
}
