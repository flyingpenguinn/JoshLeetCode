package base;

import java.util.HashMap;
import java.util.Map;

// we would rather construct graph and use dfs if we only find once at the end...
// djs is useful when we need to find many times in the middle
public class DisjointSet<T> {
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

