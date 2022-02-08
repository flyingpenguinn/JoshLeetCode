import java.util.ArrayList;
import java.util.List;

public class PartitionArrayAccordingToPivot {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        List<Integer> sm = new ArrayList<>();
        List<Integer> eq = new ArrayList<>();
        List<Integer> bg = new ArrayList<>();
        for (int ai : nums) {
            if (ai < pivot) {
                sm.add(ai);
            } else if (ai == pivot) {
                eq.add(ai);
            } else {
                bg.add(ai);
            }
        }
        int[] res = new int[n];
        int ri = 0;
        for (int ai : sm) {
            res[ri++] = ai;
        }
        for (int ai : eq) {
            res[ri++] = ai;
        }
        for (int ai : bg) {
            res[ri++] = ai;
        }
        return res;
    }
}
