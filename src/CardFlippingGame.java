import java.util.HashSet;
import java.util.Set;

/*
LC#822
On a table are N cards, with a positive integer printed on the front and back of each card (possibly different).

We flip any number of cards, and after we choose one card.

If the number X on the back of the chosen card is not on the front of any card, then this number X is good.

What is the smallest number that is good?  If no number is good, output 0.

Here, fronts[i] and backs[i] represent the number on the front and back of card i.

A flip swaps the front and back numbers, so the value on the front is now on the back and vice versa.

Example:

Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
Output: 2
Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.
 */
public class CardFlippingGame {
    // if a card is on both front and back then it can't be good
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> nums = new HashSet<>();
        int n = fronts.length;
        for (int i = 0; i < n; i++) {
            nums.add(fronts[i]);
        }
        for (int i = 0; i < n; i++) {
            nums.add(backs[i]);
        }
        int inf = 10000;
        int min = inf;

        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                nums.remove(fronts[i]);
            }
        }
        for (int k : nums) {
            min = Math.min(min, k);
        }
        return min >= inf ? 0 : min;
    }

}
