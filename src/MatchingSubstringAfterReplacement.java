import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatchingSubstringAfterReplacement {
    public boolean matchReplacement(String t, String s, char[][] mappings) {
        Map<Character, Set<Character>> mm = new HashMap<>();
        for (char[] m : mappings) {
            char v1 = m[0];
            char v2 = m[1];
            mm.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
        }
        for (int i = 0; i + s.length() - 1 < t.length(); ++i) {
            int j = 0;
            Map<Character, Character> rm = new HashMap<>();
            while (j < s.length()) {
                char tc = t.charAt(i+j);
                char sc = s.charAt(j);
                if(tc==sc){
                    ++j;
                }else if(rm.containsKey(sc) && rm.get(sc) == tc){
                    ++j;
                }else if(mm.getOrDefault(sc, new HashSet<>()).contains(tc)){
                    rm.put(sc, tc);
                    ++j;
                }else{
                    break; // not mapped
                }
            }
            if(j==s.length()){
                return true;
            }
        }
        return false;
    }




    public static void main(String[] args) {
        System.out.println(new MatchingSubstringAfterReplacement().matchReplacement("6twu7otvh677372m6r7u0aa9sr7xkchqmd44ij8wjeq82hsgaesckty82b6u3unz0uieko10a3", "6twu7otvh677372m6r7u0a495r7xkchqmd44ij8wjeq82h5gaesckty82b6u3unz0ulekol0", ArrayUtils.readAsChar("[[l,i],[5,s],[l,1],[4,a]]")));
    }

}
