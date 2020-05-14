import base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostFrequentSubtreeSum {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, Integer> freq = new HashMap<>();
    int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        dos(root);
        Set<Integer> mv = map.getOrDefault(max, new HashSet<>());

        int[] r = new int[mv.size()];
        int i = 0;
        for (int mi : mv) {
            r[i++] = mi;
        }
        return r;
    }

    int dos(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dos(root.left);
        int right = dos(root.right);
        int sum = left + right + root.val;
        int oldFreq = freq.getOrDefault(sum, 0);
        Set<Integer> oldcm = map.getOrDefault(oldFreq, new HashSet<>());
        oldcm.remove(sum);
        map.put(oldFreq, oldcm);

        int newFreq = oldFreq + 1;
        max = Math.max(max, newFreq);
        freq.put(sum, newFreq);

        Set<Integer> newcm = map.getOrDefault(newFreq, new HashSet<>());
        newcm.add(sum);
        map.put(newFreq, newcm);
        return sum;
    }
}
