import java.util.*;

public class KSimilarString {

    // every time put one char in place
    Set<String> visited = new HashSet<>();

    class QueueItem {
        String str;
        int steps;
        int index;

        public QueueItem(String str, int steps, int index) {
            this.str = str;
            this.steps = steps;
            this.index = index;
        }
    }

    public int kSimilarity(String a, String b) {
        Deque<QueueItem> q = new ArrayDeque<>();
        q.offer(new QueueItem(a, 0, 0));
        visited.add(a);
        while (!q.isEmpty()) {
            QueueItem top = q.pop();
            String topstr = top.str;
            if (topstr.equals(b)) {
                return top.steps;
            }
            int index = top.index;
            while (topstr.charAt(index) == b.charAt(index)) {
                index++;
            }
            for (int j = index + 1; j < a.length(); j++) {
                if (topstr.charAt(j) == b.charAt(index)) {
                    String ns = swap(index, j, topstr);
                    if (!visited.contains(ns)) {
                        visited.add(ns);
                        q.offer(new QueueItem(ns, top.steps + 1, index + 1));
                    }
                }
            }
        }
        return -1;
    }

    private String swap(int i, int j, String a) {
        StringBuilder sb = new StringBuilder(a);
        sb.setCharAt(i, a.charAt(j));
        sb.setCharAt(j, a.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new KSimilarString().kSimilarity("aaaabbbbccccddddeeee", "edcbaacbcebcdbeedaad"));
        //    System.out.println(new KSimilarString().kSimilarity("abc", "bca"));
    }
}

class KsimilarStringDp {

    Map<String, Map<Integer, Integer>> dp = new HashMap<>();

    public int kSimilarity(String a, String b) {
        return doKsimilar(a, b, 0);
    }

    private int doKsimilar(String a, String b, int start) {
        if (start == b.length()) {
            return 0;
        }
        Map<Integer, Integer> cm = dp.getOrDefault(a, new HashMap<>());
        Integer cached = cm.get(start);
        if (cached != null) {
            return cached;
        }
        int min = Integer.MAX_VALUE;
        if (a.charAt(start) == b.charAt(start)) {
            min = doKsimilar(a, b, start + 1);
        } else {
            char bc = b.charAt(start);
            for (int i = start + 1; i < a.length(); i++) {
                if (a.charAt(i) == bc) {
                    String na = swap(start, i, a);
                    int steps = doKsimilar(na, b, start + 1);
                    if (steps != Integer.MAX_VALUE) {
                        min = Math.min(min, steps + 1);
                    }
                }
            }
        }
        cm.put(start, min);
        dp.put(a, cm);
        return min;
    }


    private String swap(int i, int j, String a) {
        StringBuilder sb = new StringBuilder(a);
        sb.setCharAt(i, a.charAt(j));
        sb.setCharAt(j, a.charAt(i));
        return sb.toString();
    }

}



