import java.util.*;

public class DeleteDuplicateFolderSystem {
    // each folder use rolling hash to give a signature
    class Node {
        String v;

        public Node(String v) {
            this.v = v;
        }

    }

    Map<Node, TreeMap<String, Node>> chd = new HashMap<>(); // must sort so that a/b a/c and d/c d/b can be detected as the same
    Map<Long, Set<Node>> hmap = new HashMap<>();
    Set<Node> deleted = new HashSet<>();
    long hashbase = 10000000037L;
    long mod = 10000000019L;
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Collections.sort(paths, (x, y) -> Integer.compare(x.size(), y.size()));
        Node root = new Node("/");
        for (List<String> p : paths) {
            Node ptr = root;
            for (int i = 0; i < p.size(); i++) {
                String pv = p.get(i);
                TreeMap<String, Node> pm = chd.getOrDefault(ptr, new TreeMap<>());
                if (pm.containsKey(pv)) {
                    ptr = pm.get(pv);
                } else {
                    Node nnode = new Node(pv);
                    pm.put(pv, nnode);
                    chd.put(ptr, pm);
                    ptr = nnode;
                }

            }
        }
        dfs(root);
        for (Long k : hmap.keySet()) {
            Set<Node> nodes = hmap.get(k);
            if (nodes.size() >= 2) {
                deleted.addAll(nodes);
            }
        }
        dfs2(root, new ArrayList<>());
        return res;
    }

    private void dfs2(Node root, List<String> path) {
        TreeMap<String, Node> ch = chd.getOrDefault(root, new TreeMap<>());
        if (!root.v.equals("/")) {
            path.add(root.v);
        }
        if (!deleted.contains(root)) {
            if (!path.isEmpty()) {
                res.add(new ArrayList<>(path));
            }
            for (Node ne : ch.values()) {
                dfs2(ne, path);
            }
        }
        if (!root.v.equals("/")) {
            path.remove(path.size() - 1);
        }
    }

    private long dfs(Node root) {
        long hash = 0;
        TreeMap<String, Node> ch = chd.getOrDefault(root, new TreeMap<>());
        for (Node ne : ch.values()) {
            long chash = dfs(ne);
            hash += hashbase * chash;
            hash %= mod;
        }
        if (hash != 0) {
            hmap.computeIfAbsent(hash, k -> new HashSet<>()).add(root);
        }
        hash += hashbase * root.v.hashCode();
        hash %= mod;
        return hash;
    }
}
