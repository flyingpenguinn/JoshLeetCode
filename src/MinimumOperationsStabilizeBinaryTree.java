import base.TreeNode;

import java.util.*;

public class MinimumOperationsStabilizeBinaryTree {
    // TODO slope dp...
    static class Slope {
        PriorityQueue<Long> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> right = new PriorityQueue<>();
        long offsetL, offsetR, min;

        int size() {
            return left.size() + right.size();
        }

        void addLeft(long a) {
            if (!right.isEmpty() && right.peek() + offsetR < a) {
                long r = right.poll() + offsetR;
                min += a - r;
                left.offer(r - offsetL);
                right.offer(a - offsetR);
            } else {
                left.offer(a - offsetL);
            }
        }

        void addRight(long a) {
            if (!left.isEmpty() && left.peek() + offsetL > a) {
                long l = left.poll() + offsetL;
                min += l - a;
                right.offer(l - offsetR);
                left.offer(a - offsetL);
            } else {
                right.offer(a - offsetR);
            }
        }

        void addAbs(long a) {
            addLeft(a);
            addRight(a);
        }

        void expand() {
            offsetL--;
            offsetR++;
        }

        void merge(Slope other) {
            min += other.min;

            while (!other.left.isEmpty()) {
                addLeft(other.left.poll() + other.offsetL);
            }

            while (!other.right.isEmpty()) {
                addRight(other.right.poll() + other.offsetR);
            }
        }
    }

    public long stabilizeBinaryTree(TreeNode root) {
        if (root == null) return 0;

        ArrayList<TreeNode> order = new ArrayList<>();
        order.add(root);

        for (int i = 0; i < order.size(); i++) {
            TreeNode u = order.get(i);
            if (u.left != null) order.add(u.left);
            if (u.right != null) order.add(u.right);
        }

        IdentityHashMap<TreeNode, Slope> dp = new IdentityHashMap<>();

        for (int i = order.size() - 1; i >= 0; i--) {
            TreeNode u = order.get(i);

            Slope l = u.left == null ? null : dp.remove(u.left);
            Slope r = u.right == null ? null : dp.remove(u.right);

            if (l != null) l.expand();
            if (r != null) r.expand();

            Slope cur;

            if (l == null && r == null) {
                cur = new Slope();
            } else if (l == null) {
                cur = r;
            } else if (r == null) {
                cur = l;
            } else {
                if (l.size() < r.size()) {
                    Slope tmp = l;
                    l = r;
                    r = tmp;
                }

                cur = l;
                cur.merge(r);
            }

            cur.addAbs(u.val);
            dp.put(u, cur);
        }

        return dp.get(root).min;
    }
}
