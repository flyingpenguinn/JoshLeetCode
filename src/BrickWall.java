import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
    // just find the most popular seam
    public int leastBricks(List<List<Integer>> wall) {
        int rows = wall.size();
        // last of each row

        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            int cur = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                cur += wall.get(i).get(j);
                int cc = map.getOrDefault(cur, 0) + 1;
                map.put(cur, cc);
                max = Math.max(max, cc);
            }
        }
        return rows - max;
    }
}
