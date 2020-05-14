import base.DisjointSet;
import base.Lists;

import java.util.*;

public class AccountMerge {


    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> mailtoName = new HashMap<>();
        DisjointSet<String> dsj = new DisjointSet<>();
        for (List<String> mails : accounts) {
            String name = mails.get(0);
            String rep = null;
            for (int i = 1; i < mails.size(); i++) {
                String mail = mails.get(i);
                if (mailtoName.get(mail) != null && !mailtoName.get(mail).equals(name)) {
                    throw new IllegalArgumentException("multiple names...");
                }
                mailtoName.put(mail, name);
                String curSet = dsj.find(mail);
                if (rep == null) {
                    rep = curSet;
                } else {
                    rep = dsj.union(curSet, rep);
                }
            }
        }
        Map<String, TreeSet<String>> repToMails = new HashMap<>();
        for (String m : mailtoName.keySet()) {
            String repMail = dsj.find(m);
            TreeSet<String> mails = repToMails.getOrDefault(repMail, new TreeSet<>());
            mails.add(m);
            repToMails.put(repMail, mails);
        }
        List<List<String>> result = new ArrayList<>();
        for (String rep : repToMails.keySet()) {
            List<String> curList = new ArrayList<>();
            String name = mailtoName.get(rep);
            curList.add(name);
            TreeSet<String> mailList = repToMails.get(rep);
            curList.addAll(mailList);
            result.add(curList);
        }

        return result;
    }


    public static void main(String[] args) {

        System.out.println(new AccountMerge().accountsMerge(Lists.stringToLists("[[John,johnsmith@mail.com,john_newyork@mail.com],[John,johnsmith@mail.com,john00@mail.com],[Mary,mary@mail.com],[John,johnnybravo@mail.com]]")));
    }
}
