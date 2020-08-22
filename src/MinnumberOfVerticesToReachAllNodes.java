import java.util.ArrayList;
import java.util.List;

public class MinnumberOfVerticesToReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] ind = new int[n];
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            ind[end]++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < ind.length; i++) {
            if (ind[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
