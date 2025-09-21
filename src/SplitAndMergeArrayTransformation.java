import java.util.*;

public class SplitAndMergeArrayTransformation {
    class Item {
        List<Integer> a;
        int dist;

        public Item(List<Integer> a, int dist) {
            this.a = a;
            this.dist = dist;
        }
    }

    public int minSplitMerge(int[] a, int[] b) {
        int n = a.length;
        List<Integer> la = new ArrayList<>();
        for (int ai : a) {
            la.add(ai);
        }
        List<Integer> lb = new ArrayList<>();
        for (int bi : b) {
            lb.add(bi);
        }
        Queue<Item> q = new ArrayDeque<>();
        q.offer(new Item(la, 0));
        Set<List<Integer>> seen = new HashSet<>();
        seen.add(la);
        while (!q.isEmpty()) {
            Item top = q.poll();
            List<Integer> ca = top.a;

            int cd = top.dist;
            if (ca.equals(lb)) {
                return cd;
            }
            for (int i = 0; i < n; ++i) {
                for (int j = i; j < n; ++j) {
                    List<Integer> newa = split(ca, i, j);
                    List<Integer> rem = extract(ca, i, j);
                    for (int k = 0; k < rem.size(); ++k) {
                        List<Integer> na = insert(rem, k, newa);
                        if (seen.contains(na)) {
                            continue;
                        }
                        seen.add(na);
                        int nd = top.dist + 1;
                        q.offer(new Item(na, nd));
                    }
                }
            }
        }
        return -1;
    }


    private List<Integer> insert(List<Integer> a, int p, List<Integer> other) {
        int n = a.size();
        List<Integer> res = new ArrayList<>(a);
        res.addAll(p, other);
        return res;
    }

    private List<Integer> extract(List<Integer> a, int i, int j) {
        List<Integer> res = new ArrayList<>();
        for (int k = 0; k < a.size(); ++k) {
            if (k >= i && k <= j) {
                continue;
            }
            res.add(a.get(k));
        }
        return res;
    }

    private List<Integer> split(List<Integer> a, int i, int j) {
        List<Integer> res = new ArrayList<>();
        for (int k = i; k <= j; ++k) {
            res.add(a.get(k));
        }
        return res;
    }
}
