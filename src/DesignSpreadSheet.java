import java.util.TreeMap;

public class DesignSpreadSheet {
    class Solution {
        class Trie{
            char c ;
            Trie[] ch = new Trie[26];
            int count = 0;
            public Trie(char c){
                this.c = c;
            }
        }

        private TreeMap<Integer,Integer> m = new TreeMap<>();
        private Trie t = new Trie('*');
        private void update(int k, int d){
            int nv = m.getOrDefault(k, 0)+d;
            if(nv<=0){
                m.remove(k);
            }else{
                m.put(k, nv);
            }
        }
        private void insert(String w){
            int wn = w.length();
            Trie p = t;
            for(int i=0; i<wn; ++i){
                char c = w.charAt(i);
                int cind = c-'a';
                if(p.ch[cind] == null){
                    p.ch[cind] = new Trie(c);
                }
                int ccnt = ++p.ch[cind].count;
                if(ccnt >= k){
                    update(i+1, 1);
                }
                p = p.ch[cind];
            }
        }

        private void remove(String w){
            int wn = w.length();
            Trie p = t;
            for(int i=0; i<wn; ++i){
                char c = w.charAt(i);
                int cind = c-'a';
                int ccnt = --p.ch[cind].count;
                if(ccnt == k-1){
                    update(i+1, -1);
                }
                p = p.ch[cind];
            }
        }
        private int k = -1;
        public int[] longestCommonPrefix(String[] words, int k) {
            int n = words.length;
            this.k = k;
            for(String w: words){
                insert(w);
            }
            int[] res = new int[n];
            for(int i=0; i<n; ++i){
                remove(words[i]);
                if(m.isEmpty()){
                    res[i] = 0;
                }else {
                    res[i] = m.lastKey();
                }
                insert(words[i]);
            }
            return res;
        }
    }
}
