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
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<String, Integer> mail2holder = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> ac = accounts.get(i);
            for (int j = 1; j < ac.size(); j++) {
                String mail = ac.get(j);
                if (mail2holder.containsKey(mail)) {
                    int k = mail2holder.get(mail);
                    graph.computeIfAbsent(i, key -> new HashSet<>()).add(k);
                    graph.computeIfAbsent(k, key -> new HashSet<>()).add(i);
                }
                mail2holder.put(mail, i);
            }
        }
        Set<Integer> seen = new HashSet<>();
        List<List<String>> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!seen.contains(i)) {
                List<String> mails = new ArrayList<>(dfs(i, graph, seen, accounts));
                Collections.sort(mails);
                mails.add(0, accounts.get(i).get(0));
                r.add(mails);
            }
        }
        return r;
    }

    Set<String> dfs(int i, Map<Integer, Set<Integer>> graph, Set<Integer> seen, List<List<String>> a) {
        seen.add(i);
        Set<String> r = new HashSet<>();
        for (int j = 1; j < a.get(i).size(); j++) {
            r.add(a.get(i).get(j));
        }
        for (int next : graph.getOrDefault(i, new HashSet<>())) {
            if (!seen.contains(next)) {
                r.addAll(dfs(next, graph, seen, a));
            }
        }
        return r;
    }
}
