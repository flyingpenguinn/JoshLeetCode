public class ImplementTrieII {
    class Trie {

        class Tn{
            private char c;
            private Tn[] ch = new Tn[26];
            private int pcnt = 0; // how many prefix
            private int fcnt = 0; // how many full words
            public Tn(char c ){
                this.c = c;
            }
        }

        public Trie() {

        }

        private Tn root = new Tn('*');

        public void insert(String s) {
            update(s, 1);
        }

        public int countWordsEqualTo(String s) {
            Tn t = find(s);
            return t==null? 0: t.fcnt;
        }

        public int countWordsStartingWith(String s) {
            Tn t = find(s);
            return t==null? 0: t.pcnt;
        }

        public void erase(String s) {
            update(s, -1);
        }

        private void update(String s, int d){
            int n = s.length();
            Tn p = root;
            for(int i=0; i<n; i++){
                char c = s.charAt(i);
                int ci = c-'a';
                if(p.ch[ci] == null){
                    p.ch[ci] = new Tn(c);
                }
                p.ch[ci].pcnt += d;
                p = p.ch[ci];
            }
            p.fcnt += d;
        }

        private Tn find(String s){
            int n = s.length();
            Tn p = root;
            for(int i=0; i<n; i++){
                char c = s.charAt(i);
                int ci = c-'a';
                if(p.ch[ci] == null){
                    return null;
                }
                p = p.ch[ci];
            }
            return p;
        }
    }
}
