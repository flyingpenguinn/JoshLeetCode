public class MinOrOfRemainingElementsAfterOperations {
    // TODO: this is total gibberish
    public int minOrAfterOperations(int[] nums, int k) {
        int n = nums.length, ans = 0;

        // Going from MSB to LSB
        for (int j = 29; j >= 0; j--) {
            int cnt = 0; // count of elements which have 0 at j-th bit

            // binary(cur) = 111...11
            int cur = -1;

            // target = <currently computed ans prefix> then <0> then <j 1's>
            // target = ans01111111111
            // and 0 is at current MSB position j
            // aka
            // [29, j+1] bits: currently computed ans prefix / MSBits of the answer
            // j-th bit: 0
            // [j-1, 0] bits: all set to 1
            int target = ans | ((1 << j) - 1);

            for (int x : nums) {
                cur &= x;
                // Can the `target` 'cover by OR' the value of `cur`
                // If `cur` is covered => this automatically implies that its j-th bit is 0.
                // Same as:
                // if((cur & target) == cur)
                if ((cur | target) == target) {
                    cnt++;
                    cur = -1;
                }
            }

			/*
			`n - cnt`:

			- count of numbers which have bit set in either the current `j`-th
			position or in any bit with zero value from the current already
			computed prefix of `ans`

			- and if that accumulated count would be greater than a threshold k,
			we must set any of those bits to one; and because we're minimizing
			the answer, we set to 1 the smallest among them (which is at current `j` position)
			*/
            if (n - cnt > k) {
                ans |= (1 << j);
            }
        }
        return ans;
    }
}
