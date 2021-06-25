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

    private Map<String, String> m = new HashMap<>();
    private Map<String, String> pa = new HashMap<>();

    // union find: when two people have common email draw an edge between them
    // note we just need to merge with one of the same family member: i.e. link i with i-1
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        for (List<String> acct : accounts) {
            String name = acct.get(0);
            for (int i = 1; i < acct.size(); i++) {
                String email = acct.get(i);
                m.put(email, name);
                make(email);
                if (i - 1 >= 1) { // must be from 1. 0 is the name
                    unions(email, acct.get(i - 1));
                }
            }
        }
        Map<String, List<String>> res = new HashMap<>();
        for (String mail : pa.keySet()) {
            String ans = find(mail);
            res.computeIfAbsent(ans, key -> new ArrayList<>()).add(mail);
        }
        List<List<String>> rr = new ArrayList<>();
        for (String headMail : res.keySet()) {
            List<String> list = res.get(headMail);
            Collections.sort(list);
            String name = m.get(headMail);
            list.add(0, name);
            rr.add(list);
        }
        return rr;
    }

    private void make(String s) {
        if (!pa.containsKey(s)) { // avoid dupe
            pa.put(s, s);
        }
    }

    private String find(String s) {
        if (pa.get(s).equals(s)) {
            return s;
        }
        String rt = find(pa.get(s));
        pa.put(s, rt);
        return rt;
    }

    private void unions(String s, String t) {
        String as = find(s);
        String at = find(t);
        if (as.equals(at)) {
            return;
        }
        pa.put(as, at);
    }

}

