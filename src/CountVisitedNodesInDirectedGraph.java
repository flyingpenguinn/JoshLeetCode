import java.util.*;

public class CountVisitedNodesInDirectedGraph {
    // 1. find scc
    // 2. condense the sccs to nodes
    // 3. find the results for each scc
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        Graph g = new Graph(n);
        g.V = n;
        for (int i = 0; i < n; ++i) {
            int v1 = i;
            int v2 = edges.get(i);
            g.addEdge(v1, v2);
        }
        List<List<Integer>> comps = g.findScc();
        int group = 0;
        int[] res = new int[n];
        HashMap<Integer, Integer> m = new HashMap<>();
        HashMap<Integer, Integer> sm = new HashMap<>();
        for (List<Integer> v : comps) {
            for (int vi : v) {
                m.put(vi, group);
            }
            sm.put(group, v.size());
            ++group;
        }
        Map<Integer, List<Integer>> ng = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v1 = i;
            int v2 = edges.get(i);
            int g1 = m.get(v1);
            int g2 = m.get(v2);
            if (g1 == g2) {
                continue;
            }
            ng.computeIfAbsent(g1, k -> new ArrayList<>()).add(g2);
        }
        if (ng.isEmpty()) {
            Arrays.fill(res, sm.get(0));
            return res;
        }
        int[] gres = new int[group];
        for (int i = 0; i < group; ++i) {
            dfsng(i, gres, ng, sm);
        }
        for (int i = 0; i < n; ++i) {
            res[i] = gres[m.get(i)];
        }
        return res;
    }

    private int dfsng(int i, int[] gres, Map<Integer, List<Integer>> ng, HashMap<Integer, Integer> sm) {
        if (gres[i] != 0) {
            return gres[i];
        }
        gres[i] = sm.get(i);
        for (int ne : ng.getOrDefault(i, new ArrayList<>())) {
            gres[i] += dfsng(ne, gres, ng, sm);
        }
        return gres[i];
    }

    class Graph {
        private int V;
        private LinkedList<Integer> adj[];

        // Create a graph
        Graph(int s) {
            V = s;
            adj = new LinkedList[s];
            for (int i = 0; i < s; ++i)
                adj[i] = new LinkedList();
        }

        // Add edge
        void addEdge(int s, int d) {
            adj[s].add(d);
        }

        // DFS
        void DFSUtil(int s, boolean[] visitedVertices, List<Integer> nodes) {
            visitedVertices[s] = true;
            nodes.add(s);
            int n;

            Iterator<Integer> i = adj[s].iterator();
            while (i.hasNext()) {
                n = i.next();
                if (!visitedVertices[n])
                    DFSUtil(n, visitedVertices, nodes);
            }
        }

        // Transpose the graph
        Graph Transpose() {
            Graph g = new Graph(V);
            for (int s = 0; s < V; s++) {
                Iterator<Integer> i = adj[s].listIterator();
                while (i.hasNext())
                    g.adj[i.next()].add(s);
            }
            return g;
        }

        void fillOrder(int s, boolean visitedVertices[], Stack stack) {
            visitedVertices[s] = true;

            Iterator<Integer> i = adj[s].iterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitedVertices[n])
                    fillOrder(n, visitedVertices, stack);
            }
            stack.push(Integer.valueOf(s));
        }

        // Print strongly connected component
        public List<List<Integer>> findScc() {
            Stack stack = new Stack();
            List<List<Integer>> res = new ArrayList<>();
            boolean visitedVertices[] = new boolean[V];
            for (int i = 0; i < V; i++)
                visitedVertices[i] = false;

            for (int i = 0; i < V; i++)
                if (visitedVertices[i] == false)
                    fillOrder(i, visitedVertices, stack);

            Graph gr = Transpose();

            for (int i = 0; i < V; i++)
                visitedVertices[i] = false;

            while (stack.empty() == false) {
                int s = (int) stack.pop();

                if (visitedVertices[s] == false) {
                    List<Integer> nodes = new ArrayList<>();
                    gr.DFSUtil(s, visitedVertices, nodes);
                    res.add(nodes);
                }
            }
            return res;
        }
    }
}
