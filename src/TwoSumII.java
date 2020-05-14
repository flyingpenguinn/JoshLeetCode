public class TwoSumII {
    public int[] twoSum(int[] nums, int target) {
        int l = 0;
        int u = nums.length - 1;
        while (l < u) {
            int sum = nums[l] + nums[u];
            if (sum == target) {
                int[] r = new int[2];
                r[0] = l + 1;
                r[1] = u + 1;
                return r;
            } else if (sum < target) {
                l++;
            } else {
                u--;
            }
        }
        return null;
    }
}
