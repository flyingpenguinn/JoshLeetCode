import java.util.*;

class ThroneInheritance {
    private Map<String, List<String>> g = new HashMap<>();
    private Set<String> dead =new HashSet<>();
    private String root = null;

    public ThroneInheritance(String kingName) {
        root = kingName;
    }

    public void birth(String parentName, String childName) {
        g.computeIfAbsent(parentName, k-> new ArrayList<>()).add(childName);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        return dfs(root);
    }

    private List<String> dfs(String s){
        List<String> cur = new ArrayList<>();
        if(!dead.contains(s)){
            cur.add(s);
        }
        for(String ch: g.getOrDefault(s, new ArrayList<>())){
            List<String> later = dfs(ch);
            cur.addAll(later);
        }
        return cur;
    }
}