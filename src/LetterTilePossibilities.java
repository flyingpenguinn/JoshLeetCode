import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// permutations of subset, either start from permutation and count subsets, or start from subsets and count their permutation
public class LetterTilePossibilities {
    // similar to permutations II but we need to count all shorter strings
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            count[tiles.charAt(i) - 'A']++;
        }
        return dop(count);
    }

    // permutations from position i with count as status array
    private int dop(int[] count) {
        int r = 0;
        for (int j = 0; j < 26; j++) {
            // key: each ending at certain index i with a valid char is a new permutation. this is how we count different lengths
            if (count[j] > 0) {
                r++;
                count[j]--;
                r += dop(count);
                count[j]++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new LetterTileSetGeneration().numTilePossibilities("AAABBC"));
    }
}

class LetterTileSetGeneration {
    int count = 0;
    Set<String> subsets = new HashSet<>();

    // generate all subsets and calc different permutations by n!/m1!*m2!... m1, m2 are repetitions
    public int numTilePossibilities(String tiles) {
        char[] tc = tiles.toCharArray();
        Arrays.sort(tc);
        gensub(tc, 0);
        return count;
    }

    private int getperms(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'A']++;
        }
        int base = fact(s.length());
        for (int i = 0; i < 26; i++) {
            if (c[i] > 0) {
                base /= fact(c[i]);
            }
        }
        return base;
    }

    private int fact(int length) {
        return length == 0 ? 1 : length * fact(length - 1);
    }

    private Set<String> gensub(char[] tiles, int i) {
        Set<String> r = new HashSet<>();
        if (i == tiles.length) {
            r.add("");
            return r;
        }
        char c = tiles[i];
        Set<String> without = gensub(tiles, i + 1);
        r.addAll(without);
        for (String ws : without) {
            String cur = c + ws;
            r.add(cur);
            if (!subsets.contains(cur)) {
                count += getperms(cur);
                subsets.add(cur);
            }
        }
        return r;
    }
}

class LetterTileBruteForce {
    // gen permutations of all subsets and count
    public int numTilePossibilities(String tiles) {
        Set<String> r = dop(tiles, 0);
        // minus empty
        return r.size() - 1;
    }

    Set<String> dop(String s, int i) {
        Set<String> r = new HashSet<>();

        if (i == s.length()) {
            r.add("");
            return r;
        }
        Set<String> wo = dop(s, i + 1);
        r.addAll(wo);
        for (String woi : wo) {
            for (int j = 0; j <= woi.length(); j++) {
                StringBuilder sb = new StringBuilder(woi);
                sb.insert(j, s.charAt(i));
                r.add(sb.toString());
            }
        }
        return r;
    }
}