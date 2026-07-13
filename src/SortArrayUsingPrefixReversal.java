import java.util.*;

public class SortArrayUsingPrefixReversal {
    private List<Integer> rev(List<Integer> a, int len) {
        int n = a.size();
        List<Integer> res = new ArrayList<>();
        for (int i = len - 1; i >= 0; --i) {
            res.add(a.get(i));
        }
        for (int i = len; i < n; ++i) {
            res.add(a.get(i));
        }
        return res;
    }

    private boolean sorted(List<Integer> a) {
        int n = a.size();
        for (int i = 0; i + 1 < n; ++i) {
            if (a.get(i) > a.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private class Item {
        List<Integer> a;
        int cd;

        public Item(List<Integer> a, int cd) {
            this.a = a;
            this.cd = cd;
        }
    }

    public int sortArray(int[] a, int[] pre) {
        int n = a.length;
        Deque<Item> q = new ArrayDeque<>();
        q.offerLast(new Item(tolist(a), 0));
        Set<List<Integer>> seen = new HashSet<>();
        while (!q.isEmpty()) {
            Item top = q.pollFirst();
            List<Integer> list = top.a;
            int cd = top.cd;
            if (sorted(list)) {
                return cd;
            }
            for (int i = 0; i < pre.length; ++i) {
                int clen = pre[i];
                List<Integer> next = rev(list, clen);
                if (!seen.contains(next)) {
                    seen.add(next);
                    q.offerLast(new Item(next, cd + 1));
                }
            }
        }
        return -1;
    }

    private List<Integer> tolist(int[] a) {
        List<Integer> res = new ArrayList<>();
        for (int ai : a) {
            res.add(ai);
        }
        return res;
    }
}
