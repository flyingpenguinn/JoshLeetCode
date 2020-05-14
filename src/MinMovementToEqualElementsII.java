import java.util.Arrays;
import java.util.Random;

public class MinMovementToEqualElementsII {
    public int minMoves2(int[] nums) {
        int r = 0;
        Arrays.sort(nums);
        for (int n : nums) {
            // if even, middle two give same result
            r += Math.abs(n - nums[nums.length / 2]);
        }
        return r;
    }
}

class MinmoveIISelectionAlgo {
    Random rand = new Random();

    public int minMoves2(int[] nums) {
        int r = 0;
        int median = select(nums, (nums.length + 1) / 2);
        for (int n : nums) {
            // if even, middle two give same result
            r += Math.abs(n - median);
        }
        return r;
    }

    private int select(int[] nums, int th) {
        return dosel(nums, 0, nums.length - 1, th);
    }

    // t-th, starting from 1
    private int dosel(int[] nums, int l, int u, int t) {
        if (l == u) {
            return nums[l];
        }
        int pivotPos = partition(nums, l, u);
        int leftl = pivotPos - 1 - l + 1;
        // if t==2, we want leftl ==1 so that pivotPos is the 2nd
        if (leftl == t - 1) {
            return nums[pivotPos];
        } else if (leftl > t - 1) {
            return dosel(nums, l, pivotPos - 1, t);
        } else {
            // if t==5 and left ==2, we know we are looking for 5-2-1 = 2nd in right
            return dosel(nums, pivotPos + 1, u, t - (leftl + 1));
        }
    }

    private int partition(int[] nums, int l, int u) {
        int i = l - 1;
        int j = l;
        int randitem = rand.nextInt(u-l+1);
        swap(nums, l+randitem, u);
        int pivot = nums[u];
        while (j <= u) {
            if (nums[j] <= pivot) {
                swap(nums, ++i, j);
            }
            j++;
        }
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
