import java.util.*;

/*
LC#444
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
 */
public class SequenceReconstruction {

    // graph and org list consistent. topo sort exist, unique and == org. if unique,
    // for uniqueness: either use bfs topo sort, or before and after must be parent/child relastionship
    // need to handle cases where org and seqs donthave the same count of nodes! i.e. empty or len==1 sequences
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                int st = seq.get(i);
                g.putIfAbsent(st, new HashSet<>());
                if (i + 1 < seq.size()) {
                    int end = seq.get(i + 1);
                    g.putIfAbsent(end, new HashSet<>());
                    Set<Integer> cset = g.getOrDefault(st, new HashSet<>());
                    cset.add(end);
                    g.put(st, cset);
                }
            }
        }
        if (g.keySet().size() != org.length) {
            return false;
        }
        int n = g.keySet().size();
        int[] v = new int[n + 1];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (v[i] == 0) {
                boolean good = dfs(i, v, g, list);
                if (!good) {
                    return false;
                }
            }
        }
        if (list.size() != n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (org[n - 1 - i] != list.get(i)) {
                return false;
            }
            if (i + 1 < n) {
                int pre = list.get(i);
                int after = list.get(i + 1);
                Set<Integer> set = g.getOrDefault(after, new HashSet<>());
                if (!set.contains(pre)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int s, int[] v, Map<Integer, Set<Integer>> g, List<Integer> list) {
        v[s] = 1;
        for (int ch : g.getOrDefault(s, new HashSet<>())) {
            if (v[ch] == 0) {
                dfs(ch, v, g, list);
            } else if (v[ch] == 1) {
                return false;
            }
        }
        v[s] = 2;
        list.add(s);
        return true;
    }
}

class SequenceReconstructionBfs {
    // use bfs way to do topo- then each time there should be only one node whose indegree is 0
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        Map<Integer, Set<Integer>> g = new HashMap<>();
        Map<Integer, Integer> ind = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                int st = seq.get(i);
                g.putIfAbsent(st, new HashSet<>());
                if (i + 1 < seq.size()) {
                    int end = seq.get(i + 1);
                    g.putIfAbsent(end, new HashSet<>());
                    Set<Integer> cset = g.getOrDefault(st, new HashSet<>());
                    if (!cset.contains(end)) {
                        cset.add(end);
                        g.put(st, cset);
                        ind.put(end, ind.getOrDefault(end, 0) + 1);
                    }
                }
            }
        }
        if (g.keySet().size() != org.length) {
            return false;
        }

        int zero = -1;
        for (int k : g.keySet()) {
            if (!ind.containsKey(k)) {
                if (zero > 0) {
                    return false;
                }
                zero = k;
            }
        }
        int ri = 0;
        while (ri < org.length) {
            if (zero != org[ri++]) {
                return false;
            }
            Set<Integer> nextset = g.getOrDefault(zero, new HashSet<>());
            zero = -1;
            for (int next : nextset) {
                int nv = ind.getOrDefault(next, 0) - 1;
                if (nv == 0) {
                    ind.remove(next);
                    if (zero > 0) {
                        return false;
                    }
                    zero = next;
                } else {
                    ind.put(next, nv);
                }
            }
        }
        return true;
    }
}