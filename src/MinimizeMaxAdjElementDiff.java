import java.util.ArrayList;

public class MinimizeMaxAdjElementDiff {
    // TODO- do it myself
    public int minDifference(int[] nums) {
        ArrayList<int[]> arr = new ArrayList<>();
        int n = nums.length;
        int last = -1;
        int min = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) {
                if (i > 0 && nums[i - 1] != -1) min = Math.max(min, Math.abs(nums[i] - nums[i - 1]));
                last = i;
                continue;
            }
            int p = i;
            while (p < n && nums[p] == -1) p++;
            int cnt = p - i;
            if (p == n) {
                arr.add(new int[]{last, -1, cnt});
                break;
            } else {
                arr.add(new int[]{last, p, cnt});
            }
            i = p - 1;
        }
        if (arr.size() == 0) return min;
        int l = min, r = (int) 1e9;
        while (l < r) {
            int mid = l + (r - l) / 2;
            boolean flag = check(arr, mid, nums);
            if (flag) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    boolean check(ArrayList<int[]> arr, int l, int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int[] seg : arr) {
            if (seg[0] != -1) {
                min = Math.min(nums[seg[0]] + l, min);
                max = Math.max(nums[seg[0]] - l, max);
            }
            if (seg[1] != -1) {
                min = Math.min(nums[seg[1]] + l, min);
                max = Math.max(nums[seg[1]] - l, max);
            }
        }
        if (min >= max) return true;
        for (int[] seg : arr) {
            if (seg[0] == -1 || seg[1] == -1) continue;
            int m1 = Math.max(nums[seg[0]], nums[seg[1]]);
            int m2 = Math.min(nums[seg[0]], nums[seg[1]]);
            if ((Math.abs(m1 - min) <= l && Math.abs(m2 - min) <= l) || (Math.abs(m1 - max) <= l && Math.abs(m2 - max) <= l))
                continue;
            if (Math.abs(max - m1) <= l && Math.abs(min - m2) <= l && Math.abs(min - max) <= l && seg[2] > 1) continue;
            return false;
        }
        return true;
    }
}
