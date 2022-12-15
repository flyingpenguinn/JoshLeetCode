import java.util.HashMap;
import java.util.Map;

public class MinTotalCostToMakeArraysUnequal {
    // find the initial bad set s. then try to add more elements to it if there is a dominant element
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFrequency = 0;
        int maxFrequencyValue = 0;
        int toSwap = 0;

        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                freq.put(nums1[i], freq.getOrDefault(nums1[i], 0) + 1);
                if (freq.get(nums1[i]) > maxFrequency) {
                    maxFrequencyValue = nums1[i];
                }
                maxFrequency = Math.max(maxFrequency, freq.get(nums1[i]));
                toSwap++;
                res += i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (maxFrequency > toSwap / 2 && nums1[i] != nums2[i] && nums1[i] != maxFrequencyValue &&
                    nums2[i] != maxFrequencyValue) {
                res += i;
                toSwap++;
            }
        }

        if (maxFrequency > toSwap / 2) {
            return -1;
        }

        return res;
    }
}
