import java.util.*;

public class RankTransformOfMatrix {

    class Node {
        private int i;
        private int j;
        private int v;
        private int rankdp = -1;

        private int rank() {
            Node pcur = find(this);
            return prank(pcur);
        }

        private int prank(Node pcur) {
            if (pcur.rankdp != -1) {
                return pcur.rankdp;
            }
            int rt = 1;
            for (Node prev : pcur.prevs) {
                int rprev = prev.rank();
                rt = Math.max(rt, rprev + 1);
            }
            rankdp = rt;
            return rt;
        }

        private Node parent = this;
        private Set<Node> prevs = new HashSet<>();

        public Node(int i, int j, int v) {
            this.i = i;
            this.j = j;
            this.v = v;
        }

        public String toString() {
            return "(" + i + "," + j + ")" + "=" + v;
        }
    }


    public int[][] matrixRankTransform(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Node[][] nd = new Node[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nd[i][j] = new Node(i, j, a[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            List<Node> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(nd[i][j]);
            }
            Collections.sort(row, (x, y) -> x.v - y.v);
            for (int k = 1; k < row.size(); k++) {
                Node prev = row.get(k - 1);
                Node cur = row.get(k);
                process(prev, cur);
            }
        }
        for (int j = 0; j < n; j++) {
            List<Node> col = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                col.add(nd[i][j]);
            }
            Collections.sort(col, (x, y) -> x.v - y.v);
            for (int k = 1; k < col.size(); k++) {
                Node prev = col.get(k - 1);
                Node cur = col.get(k);
                process(prev, cur);
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Node cur = find(nd[i][j]);
                res[i][j] = cur.rank();
            }
        }
        return res;
    }

    private Node find(Node n) {
        if (n.parent == n) {
            return n;
        }
        Node rt = find(n.parent);
        n.parent = rt;
        return rt;
    }

    private void union(Node n1, Node n2) {
        Node pn1 = find(n1);
        Node pn2 = find(n2);
        if (pn1 != pn2) {
            pn2.parent = pn1;
            pn1.prevs.addAll(pn2.prevs);
        }
    }

    private void process(Node prev, Node cur) {
        Node pprev = find(prev);
        Node pcur = find(cur);
        if (prev.v < cur.v) {
            pcur.prevs.add(pprev);
        } else {
            union(pprev, pcur);
        }
    }
}
