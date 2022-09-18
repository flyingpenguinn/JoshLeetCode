public class SumPrefixScoresOfStrings {
    private class Tn {
        char c;
        Tn[] ch = new Tn[26];
        int score = 0;

        public Tn(char c) {
            this.c = c;
        }
    }

    public int[] sumPrefixScores(String[] words) {
        int n = words.length;

        Tn root = new Tn('*');

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            Tn p = root;
            for (int j = 0; j < w.length(); ++j) {
                char c = w.charAt(j);
                int cind = c - 'a';
                if (p.ch[cind] == null) {
                    p.ch[cind] = new Tn(c);
                }
                p = p.ch[cind];
                ++p.score;
            }
        }
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            int cs = 0;
            Tn p = root;
            for (int j = 0; j < w.length(); ++j) {
                char c = w.charAt(j);
                int cind = c - 'a';
                cs += p.ch[cind].score;
                p = p.ch[cind];
            }
            res[i] = cs;
        }

        return res;
    }
}
