import java.util.*;

/*
LC#269
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    // trap1: what are the chars in the dict? need to traverse. not all 26 chars are in the dict
    // trap2: abc, ab will yield an invalid seq
    List<Integer>[] g = new ArrayList[26];
    boolean bad = false;
    int[] status;
    StringBuilder sb = new StringBuilder();

    public String alienOrder(String[] ws) {
        status = new int[26];
        Arrays.fill(status, -1);
        for(int i=0; i<ws.length;i++){
            for(int j=0; j<ws[i].length();j++){
                status[ws[i].charAt(j)-'a'] = 0;
            }
        }
        for(int i=0; i<26;i++){
            g[i] = new ArrayList<>();
        }
        for(int i=1; i<ws.length;i++){
            link(ws[i-1], ws[i]);
        }
        for(int i=0; i<26;i++){
            if(bad){
                return "";
            }
            if(status[i]==0){
                dfs(i);
            }
        }
        return sb.reverse().toString();
    }

    void link(String s, String t){
        int i = 0;
        int j = 0;
        while(i<s.length() && j<t.length()){
            int sind = s.charAt(i)-'a';
            int tind = t.charAt(j)-'a';
            if(sind!= tind){
                g[sind].add(tind);
                break;  // break on the first mismatch
            }else{
                i++;
                j++;
            }
        }
        if(i<s.length() && j==t.length()){
            bad = true; // eq all the way but a longer one is smaller, bad
        }
    }

    void dfs(int i){
        status[i] = 1;
        for(int next: g[i]){
            if(status[next]==0){
                dfs(next);
            }else if (status[next] == 1){ // found circle
                bad = true;
                return;
            }
        }
        sb.append((char)('a'+i));
        status[i] = 2;
    }

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().alienOrder(new String[]{"abc", "ab"}));
    }
}
