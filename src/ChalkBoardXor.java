/*
LC#810
We are given non-negative integers nums[i] which are written on a chalkboard.  Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first.  If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses.  (Also, we'll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)

Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.

Return True if and only if Alice wins the game, assuming both players play optimally.

Example:
Input: nums = [1, 1, 2]
Output: false
Explanation:
Alice has two choices: erase 1 or erase 2.
If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose.
If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.

Notes:

1 <= N <= 1000.
0 <= nums[i] <= 2^16.
 */
public class ChalkBoardXor {

    // if xor all is non zero, we can always keep it non zero...

    /*

    So "erasing" an element (n_i) causes the total XOR of all the numbers (N) to change to N XOR n_i.
    This is important because this means that Alice can only LOSE if she chooses a number n_i such that N XOR n_i = 0

    So when does N XOR n_i = 0? The only way is if n_i = N.
    Meaning that Alice can only LOSE if she picks a number that equals to the XOR of all the elements!
     */
    public boolean xorGame(int[] a) {
        int n = a.length;
        int xor = a[0];
        for (int i = 1; i < n; i++) {
            xor ^= a[i];
        }
        return xor == 0 || n % 2 == 0;
    }
}
