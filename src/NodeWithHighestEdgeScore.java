public class NodeWithHighestEdgeScore {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] scores = new long[n];
        for (int i = 0; i < n; ++i) {
            int s = i;
            int e = edges[i];
            scores[e] += s;
        }
        long max = -1;
        int maxi = -1;
        for (int i = 0; i < n; ++i) {
            if (scores[i] > max) {
                max = scores[i];
                maxi = i;
            }
        }
        return maxi;
    }
}
