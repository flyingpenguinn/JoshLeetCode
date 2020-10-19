import java.util.*;

/*
LC#267
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
 */
public class PalindromePermutationII {
    // note even for odd chars we can -2 them unless there is only one
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> m = new HashMap<>();
        int n = s.length();
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            m.put(c, m.getOrDefault(c,0)+1);
        }
        Character odd = null;
        List<String> res = new ArrayList<>();
        boolean removeOdd = false;
        for(char k: m.keySet()){
            int v = m.get(k);
            if(v % 2==1){
                if(odd!= null){
                    return res;
                }else{
                    odd = k;
                    if(v>1){
                        m.put(k, (v-1)/2);
                    }else{
                        removeOdd = true;
                    }
                }
            }else{
                // even part take half
                m.put(k, v/2);
            }
        }
        // the odd char is not simply one char: we need to make the even part of it join the rest as well if it's >1. if it's ==1, we should remove it
        if(removeOdd){
            m.remove(odd);
        }
        dfs(m, odd, res, "");
        return res;
    }

    private void dfs(Map<Character,Integer> m, Character odd, List<String> res, String cur){
        if(m.isEmpty()){
            if(odd==null){
                res.add(cur+reverse(cur));
            }else{
                res.add(cur+odd+reverse(cur));
            }
        }
        Set<Character> set = new HashSet<>(m.keySet());
        for(char k: set){
            update(m, k, -1);
            dfs(m, odd, res, cur+k);
            update(m, k, 1);
        }
    }

    private void update(Map<Character,Integer> m, char k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }

    private String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePermutationII().generatePalindromes("aabb"));
    }
}
