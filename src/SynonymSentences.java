import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
LC#1258
Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.


Example 1:

Input:
synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
text = "I am happy today but was sad yesterday"
Output:
["I am cheerful today but was sad yesterday",
​​​​​​​"I am cheerful today but was sorrow yesterday",
"I am happy today but was sad yesterday",
"I am happy today but was sorrow yesterday",
"I am joy today but was sad yesterday",
"I am joy today but was sorrow yesterday"]


Constraints:

0 <= synonyms.length <= 10
synonyms[i].length == 2
synonyms[0] != synonyms[1]
All words consist of at most 10 English letters only.
text is a single space separated sentence of at most 10 words.
 */
public class SynonymSentences {
    String[] sp;
    HashMap<String, String> pm = new HashMap<>();
    List<String> r = new ArrayList<>();

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        sp = text.split(" ");
        for (List<String> syn : synonyms) {
            union(syn.get(0), syn.get(1));
        }
        dfs(0, "");
        return r;
    }

    void dfs(int i, String cur) {

        if (i == sp.length) {
            r.add(cur);
            return;
        }
        String blank = cur.isEmpty() ? "" : " ";
        String g = find(sp[i], false);
        if (g != null) {
            List<String> ks = new ArrayList<>();
            for (String k : pm.keySet()) {
                // find all mates via uf
                if (find(k, false).equals(g)) {
                    ks.add(k);
                }
            }
            Collections.sort(ks);
            for (String k : ks) {
                dfs(i + 1, cur + blank + k);
            }
        } else {
            // no syn
            dfs(i + 1, cur + blank + sp[i]);
        }
    }

    String find(String k, boolean cn) {
        if (!pm.containsKey(k)) {
            if (cn) {
                pm.put(k, k);
                return k;
            } else {
                return null;
            }
        } else {
            String mk = pm.get(k);
            if (k.equals(mk)) {
                return k;
            } else {
                String pk = find(mk, false);
                pm.put(k, pk);
                return pk;
            }
        }
    }

    void union(String s1, String s2) {
        String p1 = find(s1, true);
        String p2 = find(s2, true);
        if (p1.equals(p2)) {
            return;
        }
        pm.put(p1, p2);
    }
}
