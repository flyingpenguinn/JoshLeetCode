import java.util.*;

public class FrequencesOfShortestSubsequences {
    public List<List<Integer>> supersequences(String[] words) {
        // Extract all unique letters
        Set<Integer> sigmaSet = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                sigmaSet.add(c - 'a');
            }
        }

        int n = sigmaSet.size();
        int[] sigma = new int[n];
        int idx = 0;
        for (int x : sigmaSet) sigma[idx++] = x;
        Arrays.sort(sigma);

        // Build directed graph
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) mp.put(sigma[i], i);

        List<Integer>[] e = new ArrayList[n];
        for (int i = 0; i < n; i++) e[i] = new ArrayList<>();
        for (String word : words) {
            e[mp.get(word.charAt(0) - 'a')].add(mp.get(word.charAt(1) - 'a'));
        }

        int len = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            if (check(i, e, n)) {
                int t = calcLen(i, n);
                if (t < len) {
                    ans.clear();
                    len = t;
                }
                if (t == len) ans.add(i);
            }
        }

        List<List<Integer>> ret = new ArrayList<>();
        for (int msk : ans) {
            List<Integer> tmp = new ArrayList<>(Collections.nCopies(26, 0));
            for (int j = 0; j < n; j++) {
                tmp.set(sigma[j], (msk >> j & 1) == 1 ? 2 : 1);
            }
            ret.add(tmp);
        }

        return ret;
    }

    private int calcLen(int msk, int n) {
        return Integer.bitCount(msk) + n;
    }

    private boolean check(int msk, List<Integer>[] e, int n) {
        int[] deg = new int[n];
        for (int sn = 0; sn < n; sn++) {
            if ((msk >> sn & 1) == 0) {
                for (int fn : e[sn]) {
                    if ((msk >> fn & 1) == 0) {
                        deg[fn]++;
                    }
                }
            }
        }

        int head = 0, tail = 0;
        int[] q = new int[n];
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0 && (msk >> i & 1) == 0) q[tail++] = i;
        }

        int cnt = 0;
        while (head < tail) {
            int sn = q[head++];
            cnt++;
            for (int fn : e[sn]) {
                if ((msk >> fn & 1) == 0 && --deg[fn] == 0) {
                    q[tail++] = fn;
                }
            }
        }
        return cnt == n - Integer.bitCount(msk);
    }
}
