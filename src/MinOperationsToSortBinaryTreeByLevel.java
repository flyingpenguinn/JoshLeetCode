import base.TreeNode;

import java.util.*;

public class MinOperationsToSortBinaryTreeByLevel {
    // actually about min swaps to make array sorted
    public int minimumOperations(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<Integer> nums = new ArrayList<>();
        int curl = 1;
        int res = 0;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            nums.add(top.val);
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            --curl;
            if (curl == 0) {
                //  System.out.println(nums);
                int[] input = new int[nums.size()];
                for (int i = 0; i < nums.size(); ++i) {
                    input[i] = nums.get(i);
                }
                res += swaps(input);
                curl = q.size();
                nums = new ArrayList<>();
            }
        }
        return res;
    }

    public int swaps(int[] a) {
        int n = a.length;
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(a[i], i);
        }
        Arrays.sort(a);
        Set<Integer> seen = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (seen.contains(i)) {
                continue;
            }
            if (m.get(a[i]) == i) {
                continue;
            }
            int cur = 0;
            int j = i;
            while (!seen.contains(j)) {
                seen.add(j);
                j = m.get(a[j]);
                ++cur;
            }
            res += cur - 1;
        }
        return res;
    }
}
