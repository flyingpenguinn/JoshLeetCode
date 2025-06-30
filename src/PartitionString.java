import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionString {
    private class Trie{
        Trie[] ch = new Trie[26];
    }
    public List<String> partitionString(String s) {
        int n = s.length();
        Trie root = new Trie();
        Trie p = root;
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            sb.append(c);
            int cind = c - 'a';
            if(p.ch[cind] == null){
                p.ch[cind] = new Trie();
                res.add(sb.toString());
                sb = new StringBuilder();
                p = root;
            }else{
                p = p.ch[cind];
            }
        }
        return res;
    }
}
