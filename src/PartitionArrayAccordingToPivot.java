import java.util.ArrayList;
import java.util.List;

public class PartitionArrayAccordingToPivot {
    // only need counter to know
    // mind this is not the same as sort color: othewrise if we swap we can't guarantee relative orders
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int sm = 0;
        int eq = 0;
        for (int ai : nums) {
            if (ai < pivot) {
                ++sm;
            } else if (ai == pivot) {
                ++eq;
            }
        }
        int[] res = new int[n];
        int si = 0;
        int ei = sm;
        int bi = sm + eq;
        for (int ai : nums) {
            if (ai < pivot) {
                res[si++] = ai;
            } else if (ai == pivot) {
                res[ei++] = ai;
            } else {
                res[bi++] = ai;
            }
        }
        return res;
    }
}
