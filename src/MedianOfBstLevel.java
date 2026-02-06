import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class MedianOfBstLevel {
    private List<Integer> val = new ArrayList<>();

    public int levelMedian(TreeNode root, int level) {
        inorder(root, 0, level);
        int vn = val.size();
        if (vn == 0) {
            return -1;
        }
        return val.get(vn / 2);
    }

    private void inorder(TreeNode n, int cl, int tl) {
        if (n == null) {
            return;
        }
        if (cl > tl) {
            return;
        }
        inorder(n.left, cl + 1, tl);
        if (cl == tl) {
            // cout<<"pushing level "<<n->val<<endl;
            val.add(n.val);
        }
        inorder(n.right, cl + 1, tl);
    }
}
