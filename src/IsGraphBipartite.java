import base.ArrayUtils;

import java.util.Arrays;

public class IsGraphBipartite {
    int label[];

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        if (graph == null || n == 0) {
            return true;
        }
        label = new int[n];
        for (int i = 0; i < n; i++) {
            if (label[i] == 0) {
                boolean possible = doLabel(i, 1, graph);
                if (!possible) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean doLabel(int i, int color, int[][] graph) {
        label[i] = color;
        for (int next : graph[i]) {
            int nextColor = color == 1 ? 2 : 1;
            if (label[next] != 0) {
                if (label[next] != nextColor) {
                    return false;
                } else {
                    continue;
                }
            }
            boolean possible = doLabel(next, nextColor, graph);
            if (!possible) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = ArrayUtils.read("[[1,3],[0,2],[1,3],[0,2]]");
        System.out.println(new IsGraphBipartite().isBipartite(graph));
    }
}
