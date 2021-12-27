import java.util.*;

public class FindAllPossibleRecipes {
    // dp on graph to make sure we have all the ingredients for a given dish.
    // note we need to check circles, and we remember if something is doable
    private Map<String, Set<String>> g = new HashMap<>();
    private Set<String> doable = new HashSet<>();
    public List<String> findAllRecipes(String[] rs, List<List<String>> ing, String[] ss) {
        int n = rs.length;
        for(int i=0; i<n; ++i){
            List<String> comp = ing.get(i);
            for(String c: comp){
                g.computeIfAbsent(rs[i],k->new HashSet<>()).add(c);
            }
        }
        for(String s: ss){
            doable.add(s);
        }
        List<String> res = new ArrayList<>();
        for(int i=0; i<n; ++i){
            Map<String,Integer> st = new HashMap<>();
            if(cando(rs[i], st)){
                res.add(rs[i]);
            }
        }
        return res;
    }

    private boolean cando(String s, Map<String,Integer> st){
        if(doable.contains(s)){
            return true;
        }
        if(!g.containsKey(s)){
            return false;
        }
        st.put(s, 1);
        for(String ne: g.getOrDefault(s, new HashSet<>())){
            if(st.getOrDefault(ne, 0)==1){
                return false;
            }
            if(!cando(ne,st)){
                return false;
            }
        }
        doable.add(s);
        st.put(s, 2);
        return true;
    }
}
