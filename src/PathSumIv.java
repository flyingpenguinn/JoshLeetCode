import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class PathSumIv {

    // similar to level order traversal...
    // right node + parent sum to get the value
    class QItem {
        int val;
        int sum;

        public QItem(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }

    public int pathSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        Deque<QItem> cur = new ArrayDeque<>();
        Deque<QItem> next = new ArrayDeque<>();
        cur.push(new QItem(nums[0], nums[0] % 10));
        int level = 1;
        int sum = nums[0] % 10;
        for (int i = 1; i < n; i++) {
            if (nums[i] / 100 > level + 1) {
                // jumping level
                cur = next;
                next = new ArrayDeque<>();
                level++;
            }
            QItem top = cur.peekFirst();
            int td = (nums[i] % 100) / 10;
            int toptd = (top.val % 100) / 10;
            // 2->3,4   3->5,6
            if ((td + 1) / 2 > toptd) {
                cur.pollFirst();
                top = cur.peekFirst();
            }
            QItem item = new QItem(nums[i], top.sum + nums[i] % 10);
            next.offer(item);
            sum += nums[i] % 10;
            boolean prevpos = nums[i - 1] % 100 / 10 == td - 1;
            boolean samelevel = nums[i - 1] / 100 == nums[i] / 100;
            if (td % 2 == 0 && prevpos && samelevel) {
                sum += top.sum;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new PathSumIv().pathSum(ArrayUtils.read1d("113, 221")));
    }
}
