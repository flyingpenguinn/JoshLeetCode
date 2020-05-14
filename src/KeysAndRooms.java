import java.util.List;

public class KeysAndRooms {

    // whether connected from 0
    boolean[] v;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        v = new boolean[n];
        int visited = dfs(0, rooms);
        return visited == n;

    }

    int dfs(int s, List<List<Integer>> r) {
        v[s] = true;
        int c = 1;
        for (int o : r.get(s)) {
            if (!v[o]) {
                c += dfs(o, r);
            }
        }
        return c;
    }

}
