import java.util.*;

public class CircularPermutationOneBinaryDiff {

    // use gray code!
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        List<Integer> from0 = grayCode(n); // generate one bit diff permutations
        int index = from0.indexOf(start);
        List<Integer> r = new ArrayList<>();
        for (int i = index; i < from0.size(); i++) {
            r.add(from0.get(i));
        }
        for (int i = 0; i < index; i++) {
            r.add(from0.get(i));
        }
        return r;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> rs = new ArrayList<Integer>();
        rs.add(0);
        for (int i = 0; i < n; i++) {
            int oldsize = rs.size();
            for (int j = oldsize - 1; j >= 0; j--) {
                // we need to put 11 after 01. what we add at first will be the next of the original size -1
                rs.add(rs.get(j) | 1 << i);
                // keep the originally there just add 1xxx
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        System.out.println(new CircularPermutationOneBinaryDiff().circularPermutation(16, 33));
    }
}

class CircularPermBruteForceDfs {

    List<Integer> res;

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> r = new ArrayList<>();
        r.add(start);
        Set<Integer> contained = new HashSet<>();
        contained.add(start);
        int limit = (int) Math.pow(2, n);
        dfs(start, limit, n, r, contained);
        return r;
    }

    private void dfs(int i, int limit, int n, List<Integer> cur, Set<Integer> set) {
        if (cur.size() == limit) {
            res = cur;
            return;
        }
        String binary = Integer.toBinaryString(i);
        String padded = pad(n, binary);
        for (int j = 0; j < padded.length(); j++) {
            char c = padded.charAt(j);
            String next = padded.substring(0, j) + (c == '1' ? '0' : '1') + padded.substring(j + 1);
            int nextint = Integer.parseInt(next, 2);
            if (nextint <= limit && !set.contains(nextint)) {
                cur.add(nextint);
                set.add(nextint);
                dfs(nextint, limit, n, cur, set);
                if (res != null) {
                    return;
                }
                cur.remove(cur.size() - 1);
                set.remove(nextint);
            }

        }
    }

    private String pad(int n, String binary) {
        int len = n - binary.length();
        StringBuilder pads = new StringBuilder();
        while (len > 0) {
            pads.append("0");
            len--;
        }
        return pads.toString() + binary;
    }

}
