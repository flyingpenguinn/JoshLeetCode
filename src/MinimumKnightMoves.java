import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinimumKnightMoves {
    private int[][] directions = {{1, 2}, {-1, 2}, {2, 1}, {-2, 1}, {-1, -2}, {1, -2}, {-2, -1}, {2, -1}};

    int MaxDist = 400;

    // convert to first quadrant then bfs to find shortest path
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
       int xrange = x+10;
       int yrange = y+10;
        Deque<String> q = new ArrayDeque<>();
        q.offer("0,0,0");
        Set<String> seen = new HashSet<>();
        seen.add("0,0");

        while (!q.isEmpty()) {
            String top = q.poll();
            String[] topsp = top.split(",");
            int i = Integer.valueOf(topsp[0]);
            int j = Integer.valueOf(topsp[1]);

            int dist = Integer.valueOf(topsp[2]);
            if (dist >= MaxDist) {
                continue;
            }
            if (i == x && j == y) {
                return dist;
            }
            for (int[] d : directions) {
                int ni = i + d[0];
                int nj = j + d[1];
                String put = ni + "," + nj;
                if (!seen.contains(put) && ni >= 0 && ni <= xrange && nj >= 0 && nj <= yrange) {
                    seen.add(put);
                    int newdist = dist + 1;
                    q.offer(put + "," + newdist);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumKnightMoves().minKnightMoves(300,0));
    }
}
