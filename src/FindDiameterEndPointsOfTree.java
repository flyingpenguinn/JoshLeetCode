import java.util.*;

public class FindDiameterEndPointsOfTree {
    private List<Integer>[] t;

    public String findSpecialNodes(int n, int[][] edges) {
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        int[] dist = bfs(0);
        // System.out.println(Arrays.toString(dist));
        int maxd = 0;
        int maxi = -1;
        for (int i = 0; i < n; ++i) {
            if (dist[i] > maxd) {
                maxd = dist[i];
                maxi = i;
            }
        }
        //  System.out.println(maxd+" "+maxi);
        int[] dist2 = bfs(maxi);
        //  System.out.println("dist2 "+Arrays.toString(dist2));
        int maxd2 = 0;
        int maxi2 = -1;
        for (int i = 0; i < n; ++i) {
            if (dist2[i] > maxd2) {
                maxd2 = dist2[i];
                maxi2 = i;
            }
        }
        // System.out.println(maxd2+" "+maxi2);
        int[] dist3 = bfs(maxi2);
        //  System.out.println("dist3 "+Arrays.toString(dist3));
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (dist2[i] == maxd2 || dist3[i] == maxd2) {
                res.append(1);
            } else {
                res.append(0);
            }
        }
        return res.toString();
    }

    private int[] bfs(int i) {
        int n = t.length;
        Deque<Integer> q = new ArrayDeque<>();
        int[] dist = new int[n];
        Arrays.fill(dist, 2 * n);
        q.offerLast(i);
        dist[i] = 0;
        while (!q.isEmpty()) {
            int top = q.pollFirst();
            int nd = dist[top] + 1;
            for (int ne : t[top]) {
                if (dist[ne] > nd) {
                    dist[ne] = nd;
                    q.offerLast(ne);
                }
            }
        }
        return dist;
    }
}
