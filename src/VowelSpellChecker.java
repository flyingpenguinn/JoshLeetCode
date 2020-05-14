import java.util.*;

public class VowelSpellChecker {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exacts = new HashSet<>();
        Map<String, String> fuzzy = new HashMap<>();
        for (String s : wordlist) {
            exacts.add(s);
            String sig = s.toLowerCase();
            String sigVal = fuzzy.get(sig);
            if (sigVal == null) {
                // return the first for case insensitiveness
                fuzzy.put(sig, s);
            }
            String noVowelKey = getNoVowelSig(sig);
            if (!noVowelKey.equals(sig)) {
                String noVowelValue = fuzzy.get(noVowelKey);
                if (noVowelValue == null) {
                    fuzzy.put(noVowelKey, s);
                }
            }
        }
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (exacts.contains(queries[i])) {
                result[i] = queries[i];
            } else {
                String sigq = queries[i].toLowerCase();
                String sigqvalue = fuzzy.get(sigq);
                if (sigqvalue != null) {
                    result[i] = sigqvalue;
                } else {
                    String novowq = getNoVowelSig(sigq);
                    String novowqval = fuzzy.get(novowq);
                    if (novowqval != null) {
                        result[i] = novowqval;
                    } else {
                        result[i] = "";
                    }
                }
            }
        }
        return result;
    }

    private String getNoVowelSig(String sig) {
        StringBuilder sigb = new StringBuilder(sig);
        for (int i = 0; i < sig.length(); i++) {
            if (isVowel(sigb.charAt(i))) {
                sigb.setCharAt(i, '_');
            }
        }
        String noVowelSig = sigb.toString();
        return noVowelSig;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


    public static void main(String[] args) {
        String[] wordList = {"KiTe", "kite", "hare", "Hare"};
        String[] queries = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};
        String[] r = new VowelSpellChecker().spellchecker(wordList, queries);
        System.out.println(Arrays.toString(r));
    }
}
