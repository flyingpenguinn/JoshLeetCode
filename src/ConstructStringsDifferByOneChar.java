public class ConstructStringsDifferByOneChar {
    // for every starting pair count how many diffs we got and break when we have two
    // note we can't build hashmaps because .contains(tostring()) is On
    // we can use a trie as dict to lessen the time
    public int countSubstrings(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                boolean diff = false;
                int k = i;
                int l = j;
                while (k < s.length() && l < t.length()) {
                    if (s.charAt(k) != t.charAt(l)) {
                        if (diff) {
                            break;
                        } else {
                            res++;
                            diff = true;
                        }
                    } else if (diff) {
                        res++;
                    }
                    k++;
                    l++;
                }
            }
        }
        return res;
    }
}
