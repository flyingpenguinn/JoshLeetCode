import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallestStringWithSwaps {
    // find connected components and because we can swap freely it's just a sorting:
    // similar to bubble sort concept that if you can swap neighbors you can sort
    List<Integer>[] graph;
    boolean[] visited;

    List<Integer> cur = new ArrayList<>();

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        graph = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> p : pairs) {
            graph[p.get(0)].add(p.get(1));
            graph[p.get(1)].add(p.get(0));
        }
        char[] sb = new char[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cur.clear();
                dfs(i);
                Collections.sort(cur);
                List<Character> chars = new ArrayList<>();
                for (int index : cur) {
                    chars.add(s.charAt(index));
                }
                Collections.sort(chars);
                for (int j = 0; j < cur.size(); j++) {
                    sb[cur.get(j)] = chars.get(j);
                }
            }
        }
        return new String(sb);
    }

    private void dfs(int s) {
        //    System.out.println(s);
        visited[s] = true;
        cur.add(s);
        for (int c : graph[s]) {
            if (!visited[c]) {
                dfs(c);
            }
        }

    }
}
