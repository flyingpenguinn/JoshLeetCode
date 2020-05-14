import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTreeToMatchPreorder {
    class Rt {
        List<Integer> f;
        int size;

        public Rt(List<Integer> f, int size) {
            this.f = f;
            this.size = size;
        }
    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] v) {
        return doflip(root, v, 0).f;
    }

    Rt doflip(TreeNode node, int[] v, int i) {
        if (node == null || i == v.length) {
            return new Rt(new ArrayList<>(), 0);
        }

        if (node.val != v[i]) {
            return bad();
        }
        Rt lr = doflip(node.left, v, i + 1);
        if (isbad(lr)) {
            Rt rr = doflip(node.right, v, i + 1);
            if (isbad(rr)) {
                return bad();
            }
            int rs = rr.size;
            lr = doflip(node.left, v, i + rs + 1);
            if (isbad(lr)) {
                return bad();
            }
            int ls = lr.size;
            List<Integer> r = new ArrayList<>();
            r.addAll(lr.f);
            r.addAll(rr.f);
            r.add(node.val);
            return new Rt(r, ls + rs + 1);
        } else {
            int ls = lr.size;
            Rt rr = doflip(node.right, v, i + ls + 1);
            if (isbad(rr)) {
                return bad();
            }
            List<Integer> r = new ArrayList<>();
            r.addAll(lr.f);
            r.addAll(rr.f);
            int rs = rr.size;

            return new Rt(r, ls + rs + 1);
        }
    }

    Rt bad() {
        List<Integer> r = new ArrayList<>();
        r.add(-1);
        return new Rt(r, -1);
    }

    boolean isbad(Rt r) {
        return r.size == -1;
    }
}

class FlipBinaryTreeShorter {
    // split the bad and good logic. when it's bad it's immediately bad
    int i = 0;
    boolean bad = false;
    List<Integer> r = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] v) {
        dfs(root, v);
        return bad ? bad() : r;
    }

    private void dfs(TreeNode root, int[] v) {
        if (root == null) {
            return;
        }
        if (bad) {
            return;
        }
        if (root.val != v[i]) {
            bad = true;
            return;
        }
        i++;
        if (root.left == null) {
            dfs(root.right, v);
        } else if (root.left.val != v[i]) {
            r.add(root.val);
            dfs(root.right, v);
            dfs(root.left, v);
        } else {
            dfs(root.left, v);
            dfs(root.right, v);
        }
    }

    List<Integer> bad() {
        List<Integer> r = new ArrayList<>();
        r.add(-1);
        return r;
    }
}
