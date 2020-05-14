public class LexicographicallySmallestString {
    // union find, merge to smaller
    public String smallestEquivalentString(String a, String b, String s) {
        int[] p = new int[26];
        for (int i = 0; i < 26; i++) {
            p[i] = i;
        }
        for (int i = 0; i < a.length(); i++) {
            int c1 = find(a.charAt(i) - 'a', p);
            int c2 = find(b.charAt(i) - 'a', p);
            union(c1, c2, p);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append((char) (find(s.charAt(i) - 'a', p) + 'a'));
        }
        return sb.toString();
    }

    int find(int i, int[] p) {
        if (p[i] == i) {
            return i;
        }
        int rt = find(p[i], p);
        p[i] = rt;
        return rt;
    }

    void union(int c1, int c2, int[] p) {
        int p1 = find(c1, p);
        int p2 = find(c2, p);
        if (p1 < p2) {
            p[p2] = p1;
        } else if (p2 < p1) {
            p[p1] = p2;
        }
    }
}
