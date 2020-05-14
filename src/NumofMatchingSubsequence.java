import java.util.*;

public class NumofMatchingSubsequence {
    // just like issubsequence, but maintain a map of "waiting list"
    class QItem {
        String str;
        int pos;

        public QItem(String s, int pos) {
            this.str = s;
            this.pos = pos;
        }
    }

    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, Deque<QItem>> buckets = new HashMap<>();
        int count = 0;
        for (String w : words) {
            if (w.isEmpty()) {
                count++;
            } else {
                char head = w.charAt(0);
                Deque<QItem> ext = buckets.getOrDefault(head, new ArrayDeque<>());
                ext.add(new QItem(w, 0));
                buckets.put(head, ext);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Deque<QItem> ext = buckets.getOrDefault(c, new ArrayDeque<>());
            int size = ext.size();
            for (int j = 0; j < size; j++) {
                QItem curitem = ext.poll();
                String cur = curitem.str;
                int pos = curitem.pos;
                if (pos == cur.length() - 1) {
                    // all matched
                    count++;
                } else {
                    char next = cur.charAt(pos + 1);
                    Deque<QItem> nextd = buckets.getOrDefault(next, new ArrayDeque<>());
                    nextd.offer(new QItem(cur, pos + 1));
                    buckets.put(next, nextd);
                }

            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(new NumofMatchingSubsequence().numMatchingSubseq(s, words));
    }
}

class NumOfMatchingSubBinarySearch {
    public int numMatchingSubseq(String s, String[] words) {
        init(s);
        int c = 0;
        for (String w : words) {
            if (isSubsequence(w, s)) {
                c++;
            }
        }
        return c;
    }


    private Map<Character, List<Integer>> map =
            new HashMap<>();

    private void init(String t) {
        for (int i = 0; i < t.length(); i++) {
            char tc = t.charAt(i);
            List<Integer> ts =
                    map.getOrDefault(tc, new ArrayList<>());
            ts.add(i);
            map.put(tc, ts);
        }
    }

    public boolean isSubsequence(String s, String t) {


        int limit = t.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            char sc = s.charAt(i);
            List<Integer> ts = map.get(sc);
            if (ts == null) {
                return false;
            }
            // try to match as late in t as possible
            int lower = binarySearchLastSmaller(ts, limit);
            if (lower == -1) {
                return false;
            }
            limit = ts.get(lower);
        }
        return true;
    }

    private int binarySearchLastSmaller(List<Integer> list, int key) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) < key) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }

        }
        return u;
    }
}
