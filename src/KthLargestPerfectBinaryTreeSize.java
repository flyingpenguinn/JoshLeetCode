import base.TreeNode;

import java.util.PriorityQueue;

public class KthLargestPerfectBinaryTreeSize {
    private PriorityQueue<Integer> pq = new PriorityQueue<>();

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root, k);
        if (pq.size() < k) {
            return -1;
        }
        return pq.poll();
    }

    private int[] dfs(TreeNode root, int k) {
        if (root == null) {
            return new int[]{1, 0, 0};
        }
        if (root.left == null && root.right == null) {
            //   System.out.println(root.val+" 1");
            pq.offer(1);
            if (pq.size() > k) {
                pq.poll();
            }
            return new int[]{1, 1, 1};
        }
        int[] left = dfs(root.left, k);
        int[] right = dfs(root.right, k);
        int curis = 0;
        if (left[0] == 1 && right[0] == 1 && left[2] == right[2]) {
            curis = 1;
        }
        int curcount = left[1] + right[1] + 1;
        int curheight = Math.max(left[2], right[2]) + 1;
        //    System.out.println(root.val+" "+curcount+ " "+curis);
        if (curis == 1) {
            pq.offer(curcount);
        }
        if (pq.size() > k) {
            pq.poll();
        }
        return new int[]{curis, curcount, curheight};
    }

}
