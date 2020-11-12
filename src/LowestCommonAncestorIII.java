public class LowestCommonAncestorIII {
    public Node lowestCommonAncestor(Node p, Node q) {
        int hp = height(p);
        int hq = height(q);
        while (hp > hq) {
            p = p.parent;
            hp--;
        }
        while (hq > hp) {
            q = q.parent;
            hq--;
        }
        while (p.val != q.val) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }

    private int height(Node p) {
        return p.parent == null ? 0 : height(p.parent) + 1;
    }
}
