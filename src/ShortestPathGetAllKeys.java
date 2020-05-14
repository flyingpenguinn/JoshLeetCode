import java.util.*;

public class ShortestPathGetAllKeys {
    int min = Integer.MAX_VALUE;

    public int shortestPathAllKeys(String[] grid) {

        int[] start = null;
        List<Character> keys = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    start = new int[3];
                    start[0] = i;
                    start[1] = j;
                    start[2] = 0;
                } else if (Character.isUpperCase(c)) {
                    keys.add(c);
                }
            }
        }
        if (start == null) {
            return -1;
        }
        if (keys.isEmpty()) {
            return 0;
        }
        List<List<Character>> perms = perm(keys, 0);
        // 720 seqs
        for (List<Character> keyseq : perms) {
            // 6*n*m bfs
            int steps = bfs(grid, start, keyseq, 0, new HashSet<>());
            min = Math.min(min, steps);
        }
        return min >= MaxReturn ? -1 : min;
    }

    int scale = 30;
    int MaxReturn = 1000000;

    private int bfs(String[] grid, int[] start, List<Character> keyseq, int nextkey, HashSet<Character> locksOpen) {
        char keyToFind = Character.toLowerCase(keyseq.get(nextkey));
        Set<Integer> visited = new HashSet<>();
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(start);
        visited.add(getSetKey(start));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            // prune
            if (top[2] >= min) {
                break;
            }
            if (grid[i].charAt(j) == keyToFind) {
                // found!
                locksOpen.add(keyseq.get(nextkey));
                if (nextkey == keyseq.size() - 1) {
                    return top[2];
                } else {
                    return bfs(grid, top, keyseq, nextkey + 1, locksOpen);
                }
            } else {
                for (int[] d : directions) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    int[] nitem = new int[3];
                    nitem[0] = ni;
                    nitem[1] = nj;
                    nitem[2] = top[2] + 1;
                    if (inRange(grid, ni, nj) && !visited.contains(getSetKey(nitem))) {
                        char c = grid[ni].charAt(nj);
                        if (c == '#') {
                            // wall
                            continue;
                        }
                        if (Character.isUpperCase(c) && !locksOpen.contains(c)) {
                            // lock can't open
                            continue;
                        }
                        // empty or lock can open
                        visited.add(getSetKey(nitem));
                        q.offer(nitem);
                    }
                }
            }
        }
        return MaxReturn;
    }

    private int getSetKey(int[] pos) {
        return scale * pos[0] + pos[1];
    }

    private List<List<Character>> perm(List<Character> c, int i) {
        List<List<Character>> r = new ArrayList<>();
        if (i == c.size()) {
            r.add(new ArrayList<>());
            return r;
        } else {
            List<List<Character>> later = perm(c, i + 1);
            for (List<Character> li : later) {
                for (int j = 0; j <= li.size(); j++) {
                    List<Character> nli = new ArrayList<>(li);
                    nli.add(j, c.get(i));
                    r.add(nli);
                }
            }
            return r;
        }
    }


    private int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


    private boolean inRange(String[] grid, int ni, int nj) {
        return ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length();
    }

    public static void main(String[] args) {
        String[] grid = {"@.a.#", "###.#", "b.A.B"};
        System.out.println(new ShortestPathGetAllKeys().shortestPathAllKeys(grid));
    }
}

class ShortestPathGetAllKeysWithState {
//TODO write this myself, bfs use set to manage state, better than keys!
}