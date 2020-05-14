public class ChalkBoardXor {

    // if xor all is non zero, we can always keep it non zero...

    /*

    So "erasing" an element (n_i) causes the total XOR of all the numbers (N) to change to N XOR n_i.
    This is important because this means that Alice can only LOSE if she chooses a number n_i such that N XOR n_i = 0

    So when does N XOR n_i = 0? The only way is if n_i = N.
    Meaning that Alice can only LOSE if she picks a number that equals to the XOR of all the elements!
     */
    public boolean xorGame(int[] nums) {
        int base = nums[0];
        for (int i = 1; i < nums.length; i++) {
            base ^= nums[i];
        }
        if (base == 0) {
            return true;
        }
        return nums.length % 2 == 0;
    }
}
