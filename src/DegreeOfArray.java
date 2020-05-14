import java.util.HashMap;
import java.util.Map;

public class DegreeOfArray {

    // we notice that the number with the most occurrence must show up so we note down its start and end
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int minlen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int fi = map.getOrDefault(nums[i], 0) + 1;
            Integer curstart = start.get(nums[i]);
            if (curstart == null) {
                start.put(nums[i], i);
                curstart = i;
            }
            if (fi > max) {
                max = fi;
                minlen = i - curstart + 1;
            } else if (fi == max) {
                minlen = Math.min(minlen, i - curstart + 1);
            }
            map.put(nums[i], fi);
        }
        return minlen;

    }
}


//@SILU for min/max len/number of subarray of some feature that is monotonous we can use two pointers
// monotonous means this feature expands when end++ and shrinks when start--
// such as: max freq of chars, max freq of any number capped, freq of certain char's appearance, etc
class DegreeOfArrayTwoPointer {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : nums) {
            int fa = map.getOrDefault(a, 0) + 1;
            map.put(a, fa);
            max = Math.max(max, fa);
        }
        int end = -1;
        int start = 0;
        Map<Integer, Integer> cm = new HashMap<>();
        int curmax = 0;
        int minlen = Integer.MAX_VALUE;
        while (true) {
            if (curmax < max) {
                if (end == nums.length - 1) {
                    break;
                }
                end++;
                int fend = cm.getOrDefault(nums[end], 0) + 1;
                cm.put(nums[end], fend);
                curmax = Math.max(curmax, fend);
            } else {
                minlen = Math.min(minlen, end - start + 1);
                int fstart = cm.get(nums[start]);
                if (fstart == curmax) {
                    curmax--;
                }
                cm.put(nums[start], fstart - 1);
                start++;
            }
        }
        return minlen;


    }
}