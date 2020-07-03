import base.Lists;

import java.util.*;

/*
LC#721
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
 */
public class AccountsMerge {

    // union find: when two people have common email draw an edge between them
    // note we just need to merge with one of the same family member
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<String, Integer> mail2holder = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> ac = accounts.get(i);
            for (int j = 1; j < ac.size(); j++) {
                String mail = ac.get(j);
                if (mail2holder.containsKey(mail)) {
                    Integer other = mail2holder.get(mail);
                    graph.computeIfAbsent(i, key -> new ArrayList<>()).add(other);
                    graph.computeIfAbsent(other, key -> new ArrayList<>()).add(i);
                }
                mail2holder.put(mail, i);
            }
        }
        int[] pa = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = i;
        }
        for (int i = 0; i < n; i++) {
            List<Integer> con = graph.getOrDefault(i, new ArrayList<>());
            for (int j : con) {
                union(pa, i, j);
            }
        }
        Map<Integer, Set<String>> res = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = find(pa, i);
            for (int j = 1; j < accounts.get(i).size(); j++) {
                res.computeIfAbsent(parent, k -> new HashSet<>()).add(accounts.get(i).get(j));
            }
        }

        List<List<String>> r = new ArrayList<>();
        for (int k : res.keySet()) {
            List<String> withname = new ArrayList<>();
            withname.add(accounts.get(k).get(0));
            List<String> mails = new ArrayList<>(res.get(k));
            Collections.sort(mails);
            withname.addAll(mails);
            r.add(withname);
        }
        return r;
    }

    void union(int[] pa, int i, int j) {

        int pi = find(pa, i);
        int pj = find(pa, j);
        if (pi != pj) {
            pa[pi] = pj;
        }
    }

    int find(int[] pa, int i) {
        if (pa[i] == i) {
            return i;
        } else {
            int rt = find(pa, pa[i]);
            pa[i] = rt;
            return rt;
        }
    }


    public static void main(String[] args) {

        System.out.println(new AccountsMerge().accountsMerge(Lists.stringToLists("[[John,johnsmith@mail.com,john_newyork@mail.com],[John,johnsmith@mail.com,john00@mail.com],[Mary,mary@mail.com],[John,johnnybravo@mail.com]]")));
    }
}

class AccountsMergeDfs {
    // easier to implement. mind the duplicated emails...

    public List<List<String>> accountsMerge(List<List<String>> accts) {
        // check null error out if needed
        Map<Integer,Set<Integer>> g = buildGraph(accts);
        Set<Integer> seen = new HashSet<>();
        List<List<String>> r = new ArrayList<>();
        for(int i=0; i<accts.size(); i++){
            if(!seen.contains(i)){
                List<Integer> comp = dfs(accts,i, g, seen);
                List<String> compSol = buildSol(comp, accts,i);
                r.add(compSol);
            }
        }
        return r;
    }

    private Map<Integer,Set<Integer>> buildGraph(List<List<String>> accts){
        // mail to last seen id of accounts
        Map<String, Integer> m2Id = new HashMap<>();
        Map<Integer,Set<Integer>> g = new HashMap<>();
        for(int i = 0; i<accts.size(); i++){
            List<String> detail = accts.get(i);
            for(int j=1; j<detail.size(); j++){
                String mail = detail.get(j);
                if(m2Id.containsKey(mail)){
                    int other = m2Id.get(mail);
                    // bidirectional connection
                    g.computeIfAbsent(i, k-> new HashSet<>()).add(other);
                    g.computeIfAbsent(other, k-> new HashSet<>()).add(i);
                }
                m2Id.put(mail, i);
            }
        }
        return g;
    }

    private List<String> buildSol(List<Integer> comp,List<List<String>> accts, int i ){
        List<String> compSol = new ArrayList<>();
        compSol.add(accts.get(i).get(0));
        // all same emails map to same name
        Set<String> emails = new HashSet<>();
        // emails may have duplications
        for(int index: comp){
            for(int j=1; j<accts.get(index).size(); j++){
                emails.add(accts.get(index).get(j));
            }
        }
        List<String> sortedMail = new ArrayList<>(emails);
        Collections.sort(sortedMail);
        compSol.addAll(sortedMail);
        return compSol;
    }


    private List<Integer> dfs(List<List<String>> accts, int i, Map<Integer,Set<Integer>> g, Set<Integer> seen){
        seen.add(i);
        List<Integer> r = new ArrayList<>();
        r.add(i);
        Set<Integer> nexts = g.getOrDefault(i, new HashSet<>());
        for(int ne: nexts){
            if(!seen.contains(ne)){
                List<Integer> nr = dfs(accts, ne, g, seen);
                r.addAll(nr);
            }
        }
        return r;
    }
}
