import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringEncryptAndDecrypt {
    // dont really need trie...

    class EncrypterQuicker {

        private Map<Character, String> em = new HashMap<>();
        private Map<String, Set<Character>> dm = new HashMap<>();
        private Set<String> ds = new HashSet<>();

        public EncrypterQuicker(char[] keys, String[] values, String[] dictionary) {
            for (int i = 0; i < keys.length; ++i) {
                em.put(keys[i], values[i]);
                dm.computeIfAbsent(values[i], k -> new HashSet<>()).add(keys[i]);
            }
            for(String d: dictionary){
                ds.add(d);
            }
        }

        public String encrypt(String word1) {
            StringBuilder res = new StringBuilder();
            for(int i=0; i<word1.length(); ++i){
                char c = word1.charAt(i);
                res.append(em.get(c));
            }
            return res.toString();
        }

        public int decrypt(String word2) {
            int res = 0;
            for(String d: ds){
                int j =0;
                int i = 0;
                boolean bad = false;
                for(;i<word2.length() && j<d.length(); i+=2){
                    String str = word2.substring(i, i+2);
                    Set<Character> mapped = dm.getOrDefault(str, new HashSet<>());
                    if(mapped.contains(d.charAt(j))){
                        ++j;
                    }else{
                        bad = true;
                        break;
                    }
                }
                if(!bad && i==word2.length() && j==d.length()){
                    ++res;
                }
            }
            return res;
        }
    }

    class Encrypter {

        private class TN {
            private char c;
            private TN[] ch = new TN[26];
            private boolean isWord = false;

            public TN(char c) {
                this.c = c;
            }
        }

        private TN root = new TN('*');
        private Map<Character, String> em = new HashMap<>();
        private Map<String, Set<Character>> dm = new HashMap<>();

        public Encrypter(char[] keys, String[] values, String[] dictionary) {
            for (String s : dictionary) {
                TN p = root;
                for (int i = 0; i < s.length(); ++i) {
                    char c = s.charAt(i);
                    int cind = c - 'a';
                    if (p.ch[cind] == null) {
                        p.ch[cind] = new TN(c);
                    }
                    p = p.ch[cind];
                }
                p.isWord = true;
            }
            for (int i = 0; i < keys.length; ++i) {
                em.put(keys[i], values[i]);
                dm.computeIfAbsent(values[i], k -> new HashSet<>()).add(keys[i]);
            }
        }

        public String encrypt(String word1) {
            StringBuilder res = new StringBuilder();
            for(int i=0; i<word1.length(); ++i){
                char c = word1.charAt(i);
                res.append(em.get(c));
            }
            return res.toString();
        }

        public int decrypt(String word2) {
            return dfs(word2, 0, root);
        }

        private int dfs(String w, int i, TN p) {
            int n = w.length();
            if(i==n){
                return p.isWord?1:0;
            }
            String cs = w.substring(i, i+2);
            if(!dm.containsKey(cs)){
                return 0;
            }
            Set<Character> cands = dm.get(cs);
            int res = 0;
            for(char cand: cands){
                int cind = cand-'a';
                if(p.ch[cind] != null){
                    res += dfs(w, i+2, p.ch[cind]);
                }
            }
            return res;
        }
    }
}
