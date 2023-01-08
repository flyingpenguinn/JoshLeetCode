public class MakeNumberOfDistinctCharsEqual {
    // try swapping each char pair
    public boolean isItPossible(String word1, String word2) {
        int[] c1 = new int[26];
        for (char c : word1.toCharArray()) {
            ++c1[c - 'a'];
        }
        int[] c2 = new int[26];
        for (char c : word2.toCharArray()) {
            ++c2[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (c1[i] > 0) {
                for (int j = 0; j < 26; ++j) {
                    if (c2[j] > 0) {
                        --c1[i];
                        ++c1[j];
                        --c2[j];
                        ++c2[i];
                        if (dist(c1) == dist(c2)) {
                            return true;
                        }
                        ++c1[i];
                        --c1[j];
                        ++c2[j];
                        --c2[i];
                    }
                }
            }
        }
        return false;
    }

    private int dist(int[] c) {
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (c[i] > 0) {
                ++res;
            }
        }
        return res;
    }
}
