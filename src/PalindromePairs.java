import java.util.*;

/*
LC#336
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
 */
public class PalindromePairs {
    // two possibilities- ab vs cba. we handle
    private class Trie{
        private char val;
        private Trie[] ch = new Trie[26];
        private int word = -1;
        private List<Integer> palins = new ArrayList<>();
        public Trie(char v){
            val = v;
        }
    }

    private Trie root= new Trie('*');

    // palins means indexes that are palin from 0...i and node p represents i+1
    private void insertTrie(String w, int index){
        Trie p = root;
        int n = w.length();
        if(ispalin(w, 0, n-1)){
            p.palins.add(index);
        }
        for(int i=n-1; i>=0; i--){
            char c = w.charAt(i);
            int cind = c - 'a';
            Trie next = p.ch[cind];
            if(next == null){
                next = p.ch[cind] = new Trie(c);
            }
            if(ispalin(w, 0, i-1)){
                // covers empty string here
                next.palins.add(index);
            }
            p = next;
        }
        p.word = index;
    }

    public List<List<Integer>> palindromePairs(String[] ws) {
        List<List<Integer>> res = new ArrayList<>();
        int n = ws.length;
        for(int i=0; i<n; i++){
            String w = ws[i];
            insertTrie(w, i);
        }
        for(int i=0; i<n; i++){
            String w = ws[i];
            Trie p = root;
            boolean nofound = false;
            for(int j=0; j<w.length(); j++){
                int cind = w.charAt(j)-'a';
                // abc vs ba. at node b on the right it has ba's index
                if(ispalin(w, j, w.length()-1) && p.word != -1){
                    addlist(res, i, p.word);
                }
                if(p.ch[cind] == null){
                    nofound = true;
                    break;
                }else{
                    p = p.ch[cind];
                }
            }
            // if not nofound, ab vs cba. p is at b
            if(!nofound){
                for(int pa: p.palins){
                    addlist(res, i, pa);
                }
            }
        }
        return res;
    }

    private boolean ispalin(String w, int i, int j){
        while(i<j){
            if(w.charAt(i++) != w.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    private void addlist(List<List<Integer>> res, int x,int y){
        if(x==y){
            return;
        }
        res.add(List.of(x,y));
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();

        String[] input5 = {"ab", "bbba", "bba", "ba"};
        System.out.println(pp.palindromePairs(input5));


    }

}
