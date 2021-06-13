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
    // if i and j can be together it's either i's rear part is palin and j is the reverse of prefix
    // or i's starting part is palin and j is the reverse of suffix, concatted before i
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        Map<String,Integer> m = new HashMap<>();
        for(int i=0; i<words.length; i++){
            m.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<n; i++){
            String word = words[i];
            int wn = word.length();
            for(int j=wn; j>=0; j--){
                // start from wn, allowing empty
                if(ispalin(word, j, wn-1)){
                    //get reverse of 0...j-1
                    String sub = word.substring(0, j);
                    int k= process(sub, m, res);
                    if(k!= -1 && k!= i){
                        res.add(List.of(i, k));
                    }
                }
            }
            for(int j=0; j<wn; j++){
                if(ispalin(word, 0, j)){
                    String sub = word.substring(j+1, wn);
                    int k = process(sub, m, res);
                    if(k!= -1 && k!= i){
                        res.add(List.of(k,i));
                    }
                }
            }
        }
        return res;
    }

    private int process(String sub, Map<String, Integer> m, List<List<Integer>> res){
        StringBuilder rsub = new StringBuilder(sub);
        String rsubstr = rsub.reverse().toString();
        return m.getOrDefault(rsubstr, -1);
    }

    private boolean ispalin(String w, int i, int j){
        while(i<j){
            if(w.charAt(i) != w.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();

        String[] input5 = {"ab", "bbba", "bba", "ba"};
        System.out.println(pp.palindromePairs(input5));


    }

}
