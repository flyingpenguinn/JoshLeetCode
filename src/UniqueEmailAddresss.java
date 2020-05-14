import java.util.HashSet;
import java.util.Set;

/*
LC#929
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.
All local and domain names are non-empty.
Local names do not start with a '+' character.
 */
public class UniqueEmailAddresss {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String e : emails) {
            String norm = norm(e);
            set.add(norm);
        }
        return set.size();
    }

    // not using any lib
    String norm(String s) {
        StringBuilder sb = new StringBuilder();
        boolean local = true;
        boolean skip = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+') {
                if (local) {
                    skip = true;
                    continue;
                } else {
                    sb.append(c);
                }
            } else if (c == '.') {
                if (local) {
                    continue;
                } else {
                    sb.append(c);
                }
            } else if (c == '@') {
                local = false;
                skip = false;
                sb.append(c);
            } else {
                if (skip) {
                    continue;
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

class UniqueEmailAddressLib {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String e : emails) {
            String norm = norm(e);
            set.add(norm);
        }
        return set.size();
    }

    // not using any lib
    String norm(String s) {
        String[] ss = s.split("@");
        String local = ss[0];
        int plus = local.indexOf("+");
        if (plus != -1) {
            local = local.substring(0, plus);
        }
        local = local.replaceAll("\\.", "");
        return local + "@" + ss[1];
    }
}