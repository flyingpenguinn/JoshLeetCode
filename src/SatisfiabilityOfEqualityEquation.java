import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SatisfiabilityOfEqualityEquation {

    public boolean equationsPossible(String[] equations) {
        DisjointSet<String> djs = new DisjointSet<>();
        for (String e : equations) {
            if (e.indexOf("==") != -1) {
                String[] es = e.split("==");
                String es1 = es[0];
                String es2 = es[1];
                String set1 = djs.find(es1);
                String set2 = djs.find(es2);
                djs.union(set1, set2);
            }
        }
        for (String e : equations) {
            if (e.indexOf("!=") != -1) {
                String[] es = e.split("!=");
                String es1 = es[0];
                String es2 = es[1];
                String set1 = djs.find(es1);
                String set2 = djs.find(es2);
                if (set1.equals(set2)) {
                    return false;
                }
            }
        }
        return true;
    }


    private class DisjointSet<T> {
        // from string to its representative
        Map<T, T> parent = new HashMap<>();
        // each representative's rank
        Map<T, Integer> rank = new HashMap<>();

        int numOfSets = 0;

        public T makeSet(T s) {
            parent.put(s, s);
            rank.put(s, 1);
            numOfSets++;
            return s;
        }

        // path compression
        public T find(T s) {
            T p = parent.get(s);
            if (p == null) {
                return makeSet(s);
            } else if (p.equals(s)) {
                return p;
            } else {
                T pt = find(p);
                parent.put(s, pt);
                return pt;
            }
        }


        // union by rank
        public T union(T x, T y) {
            T px = find(x);
            T py = find(y);
            if (px.equals(py)) {
                // no merge! all equal
                return px;
            }
            int rankx = rank.get(px);
            int ranky = rank.get(py);
            T toReturn = null;
            numOfSets--;
            if (rankx < ranky) {
                parent.put(px, py);
                toReturn = py;
            } else if (rankx > ranky) {
                parent.put(py, px);
                toReturn = px;
            } else {
                parent.put(px, py);
                rank.put(py, rank.get(py) + 1);
                toReturn = py;
            }


            return toReturn;

        }

        public int numOfSets() {
            return numOfSets;
        }

    }

    public static void main(String[] args) {
        String[] str = {"e!=o", "t!=d", "n==f", "x!=i", "h==p", "s!=q", "k==y", "x!=p", "x!=y", "q!=t", "g!=l", "t!=a", "y==c", "v!=u", "l!=y", "b!=w", "o!=f", "m==p", "p!=l", "b==r", "u!=l", "f!=t", "p!=g", "s!=o", "f==f", "k!=j", "f!=m", "p!=s", "l==l", "b!=o", "d!=y", "i==i", "i!=u", "l!=d", "g!=c", "f!=j", "c!=j", "j!=k", "d!=w", "s==v"};
        System.out.println(new SatisfiabilityOfEqualityEquation().equationsPossible(str));
    }
}
