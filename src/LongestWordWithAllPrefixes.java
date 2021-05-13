import java.util.Arrays;

public class LongestWordWithAllPrefixes {
    class Trie{
        private char val;
        private Trie[] ch = new Trie[26];
        private boolean isword = false;
        public Trie(char c){
            this.val = c;
        }
    }
    private Trie root = new Trie('*');
    private void insert(String s){
        Trie p= root;
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            int cind = c-'a';
            if(p.ch[cind] == null){
                p.ch[cind] = new Trie(c);
            }
            p = p.ch[cind];
        }
        p.isword = true;
    }

    private boolean find(String s){
        Trie p = root;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            int cind = c-'a';
            if(p.ch[cind] == null){
                return false;
            }
            if(!p.ch[cind].isword){
                return false;
            }
            p = p.ch[cind];
        }
        return p.isword;
    }
    public String longestWord(String[] words) {
        for(String w: words){
            insert(w);
        }
        Arrays.sort(words, (x, y)-> x.length() != y.length()? Integer.compare(y.length(), x.length()): x.compareTo(y));
        for(String w: words){
            if(find(w)){
                return w;
            }
        }
        return "";
    }
}
