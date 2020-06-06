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
public class AccountMerge {

    // union find: when two people have common email draw an edge between them
    // note we just need to merge with one of the same family member

    public List<List<String>> accountsMerge(List<List<String>> a) {
        Map<Integer, Integer> pa = new HashMap<>();
        Map<String, List<Integer>> rm = new HashMap<>();

        for (int i = 0; i < a.size(); i++) {
            List<String> emails = a.get(i);
            for (int j = 1; j < emails.size(); j++) {
                rm.computeIfAbsent(emails.get(j), k -> new ArrayList<>()).add(i);
            }
            pa.put(i, i);
        }
        for (int i = 0; i < a.size(); i++) {
            List<String> emails = a.get(i);
            for (int j = 1; j < emails.size(); j++) {
                List<Integer> other = rm.getOrDefault(emails.get(j), new ArrayList<>());
                for (int o : other) {
                    boolean merged = union(i, o, pa);
                    if (merged) {
                        // just need to merge with one of them
                        break;
                    }
                }
            }
        }
        Map<Integer, Set<String>> res = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            int pai = find(i, pa);
            List<String> emails = a.get(i);
            for (int j = 1; j < emails.size(); j++) {
                res.computeIfAbsent(pai, k -> new HashSet<>()).add(emails.get(j));
            }
        }
        List<List<String>> r = new ArrayList<>();
        for (int k : res.keySet()) {
            List<String> emails = new ArrayList<>(res.get(k));
            Collections.sort(emails);
            List<String> ri = new ArrayList<>();
            ri.add(a.get(k).get(0)); // add name at front
            ri.addAll(emails);
            r.add(ri);
        }
        return r;
    }

    private boolean union(int i, int o, Map<Integer, Integer> pa) {
        int pi = find(i, pa);
        int po = find(o, pa);
        if (pi != po) {
            pa.put(po, pi);
            return true;
        } else {
            return false;
        }
    }

    private int find(int i, Map<Integer, Integer> pa) {
        if (pa.get(i) == i) {
            return i;
        }
        int rt = find(pa.get(i), pa);
        pa.put(i, rt);
        return rt;
    }


    public static void main(String[] args) {

        System.out.println(new AccountMerge().accountsMerge(Lists.stringToLists("[[John,johnsmith@mail.com,john_newyork@mail.com],[John,johnsmith@mail.com,john00@mail.com],[Mary,mary@mail.com],[John,johnnybravo@mail.com]]")));
    }
}
