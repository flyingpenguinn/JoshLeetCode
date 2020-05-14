import base.ArrayUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups {
    boolean[] visited;
    Set<String> set = new HashSet<>();

    // num of connected components, dfs or disjoint set. since it's just traversing once without changing it, both works
    public int numSimilarGroups(String[] a) {
        if (a.length == 0) {
            return 0;
        }
        visited = new boolean[a.length];
        int n = a.length;
        int w = a[0].length();
        int component = 0;
        boolean approach1 = n < w * w;
        if (!approach1) {
            for (String ai : a) {
                set.add(ai);
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (approach1) {
                if (!visited[i]) {
                    component++;
                    dfs1(i, a);
                }
            } else {
                if (set.contains(a[i])) {
                    component++;
                    dfs2(a[i]);
                }
            }
        }
        return component;
    }

    private void dfs1(int cur, String[] a) {
        visited[cur] = true;
        String s = a[cur];
        // using this way complexity is n*n*w
        for (int i = 0; i < a.length; i++) {
            if (!visited[i] && isSimilar(s, a[i])) {
                dfs1(i, a);
            }
        }
    }

    // using this way it's n*w*w*w
    private void dfs2(String s) {
        set.remove(s);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                StringBuilder sb = new StringBuilder(s);
                char tmp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, tmp);
                String sbs = sb.toString();
                if (set.contains(sbs)) {
                    dfs2(sbs);
                }
            }
        }
    }

    private boolean isSimilar(String rem, String s) {
        char diff1 = '-';
        char diff2 = '-';
        int count = 0;
        for (int i = 0; i < rem.length(); i++) {
            char c1 = rem.charAt(i);
            char c2 = s.charAt(i);
            if (c1 != c2) {
                if (count == 2) {
                    return false;
                }
                if (count == 1) {
                    if (c1 != diff2 && c2 != diff1) {
                        return false;
                    }
                    count++;
                }
                if (count == 0) {
                    diff1 = c1;
                    diff2 = c2;
                    count++;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\SimilarStringGroups.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        String[] strs = s.split(",");
        long start = System.currentTimeMillis();
        System.out.println(new SimilarStringGroups().numSimilarGroups(strs));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
