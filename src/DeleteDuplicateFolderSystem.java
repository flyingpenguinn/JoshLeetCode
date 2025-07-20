import java.util.*;

public class DeleteDuplicateFolderSystem {

    private static class Node {
        String v;
        TreeMap<String, Node> ch = new TreeMap<>();
        boolean deleted = false;

        Node(String s) {
            v = s;
        }
    }

    private Node root = new Node("*");
    private Map<String, Node> nm = new HashMap<>();
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        for (List<String> p : paths) {
            Node cur = root;
            for (String s : p) {
                cur.ch.putIfAbsent(s, new Node(s));
                cur = cur.ch.get(s);
            }
        }
        dfs1(root);
        dfs2(root, new ArrayList<>());
        return res;
    }

    private String dfs1(Node n) {
        String sig = "";
        for (Node c : n.ch.values()) {
            sig += "|" + dfs1(c);
        }
        if (!sig.isEmpty()) {
            if (nm.containsKey(sig)) {
                nm.get(sig).deleted = true;
                n.deleted = true;
            } else {
                nm.put(sig, n);
            }
        }
        sig += "(" + n.v + ")";
        return sig;
    }

    private void dfs2(Node n, List<String> path) {
        if (n == null || n.deleted) return;
        if (n != root) {
            path.add(n.v);
            res.add(new ArrayList<>(path));
        }
        for (Node c : n.ch.values()) {
            dfs2(c, path);
        }
        if (n != root) {
            path.remove(path.size() - 1);
        }

    }

}
