import java.util.Arrays;
import java.util.Random;

/*
LC#384
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */
public class ShuffleAnArray {
    class Solution {

        int[] oa;

        public Solution(int[] a) {
            this.oa = a;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return oa;
        }

        /**
         * Returns a random shuffling of the array.
         */
        Random rand = new Random();

        public int[] shuffle() {
            int[] a = Arrays.copyOf(oa, oa.length);
            for (int i = a.length - 1; i >= 0; i--) {
                int j = rand.nextInt(i + 1);
                swap(a, i, j);
            }
            return a;
        }

        void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
}




