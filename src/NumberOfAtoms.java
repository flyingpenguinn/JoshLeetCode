import java.util.*;

/*
LC#726
Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input:
formula = "H2O"
Output: "H2O"
Explanation:
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input:
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation:
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input:
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation:
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 */
public class NumberOfAtoms {
    public String countOfAtoms(String s) {
        Map<String, Integer> m = doc(s, 0, s.length() - 1);
        return tostring(m);
    }

    Map<String, Integer> doc(String s, int l, int u) {
        int i = l;
        String element = null;
        Map<String, Integer> r = new HashMap<>();
        while (i <= u) {
            if (Character.isUpperCase(s.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(i));
                int j = i + 1;
                while (j <= u && Character.isLowerCase(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                element = sb.toString();
                r.put(element, r.getOrDefault(element, 0) + 1);
                i = j;
            } else if (Character.isDigit(s.charAt(i))) {
                int j = i;
                int count = 0;
                while (j <= u && Character.isDigit(s.charAt(j))) {
                    count = count * 10 + (s.charAt(j) - '0');
                    j++;
                }
                r.put(element, r.getOrDefault(element, 0) + (count - 1));
                element = null;
                i = j;
            } else if (s.charAt(i) == '(') {
                int level = 0;
                int j = i;
                while (j <= u) {
                    if (s.charAt(j) == '(') {
                        level++;
                    } else if (s.charAt(j) == ')') {
                        level--;
                        if (level == 0) {
                            Map<String, Integer> inner = doc(s, i + 1, j - 1);
                            j++; // move to the next position for digits
                            int count = 0;
                            while (j <= u && Character.isDigit(s.charAt(j))) {
                                count = count * 10 + (s.charAt(j) - '0');
                                j++;
                            }
                            for (String ik : inner.keySet()) {
                                int added = inner.get(ik) * count;
                                r.put(ik, r.getOrDefault(ik, 0) + added);
                            }
                            i = j;
                            break;
                        }
                    }
                    j++;
                }
            }
        }
        return r;
    }

    String tostring(Map<String, Integer> m) {
        List<String> list = new ArrayList<>(m.keySet());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            int count = m.get(list.get(i));
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfAtoms().countOfAtoms("K4(ON(SO3)2)2"));
    }
}
