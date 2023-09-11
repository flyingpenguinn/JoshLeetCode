import java.util.List;

public class PointsIntersectWithCars {
    public int numberOfPoints(List<List<Integer>> a) {
        int n = a.size();
        int[] cov = new int[200];
        for (int i = 0; i < n; ++i) {
            int start = a.get(i).get(0);
            int end = a.get(i).get(1);
            ++cov[start];
            --cov[end + 1];
        }
        int accu = 0;
        int res = 0;
        for (int i = 0; i < 200; ++i) {
            accu += cov[i];
            if (accu > 0) {
                ++res;
            }
        }
        return res;
    }
}
