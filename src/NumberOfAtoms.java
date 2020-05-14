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
    public String countOfAtoms(String formula) {
        char[] a = formula.toCharArray();
        Map<String, Integer> r = doc(0, a.length - 1, a);
        TreeMap<String, Integer> tm = new TreeMap<>(r);
        StringBuilder sb = new StringBuilder();
        for (String k : tm.keySet()) {
            sb.append(k);
            Integer ct = tm.get(k);
            if (ct > 1) {
                sb.append(ct);
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> doc(int l, int u, char[] a) {
        StringBuilder atom = new StringBuilder();
        Map<String, Integer> latest = null;

        int i = l;
        Map<String, Integer> m = new HashMap<>();
        while (i <= u) {
            // when the char itself can't decide, use while to roll forward
            if (Character.isUpperCase(a[i])) {
                atom.append(a[i++]);
                while (i <= u && Character.isLowerCase(a[i])) {
                    atom.append(a[i]);
                    i++;
                }
                String atos = atom.toString();
                latest = new HashMap<>();
                // put in first, adjust later
                latest.put(atos, 1);
                merge(latest, m);
                atom = new StringBuilder();
            } else if (Character.isDigit(a[i])) {
                int count = 0;
                while (i <= u && Character.isDigit(a[i])) {
                    count = count * 10 + (a[i] - '0');
                    i++;
                }
                for (String k : latest.keySet()) {
                    int lc = latest.get(k);
                    int ov = m.getOrDefault(k, 0);
                    //key here: we originally put lc elements. now we should put in count*lc. so we add the missing (count-1)*lc elements
                    m.put(k, ov + lc * (count - 1));
                }
                latest = null;
            } else if (a[i] == '(') {
                int nextright = getnextright(a, i, u);
                latest = doc(i + 1, nextright - 1, a);
                // put in first, adjust later
                merge(latest, m);
                i = nextright + 1;
            }
        }
        return m;
    }

    private void merge(Map<String, Integer> latest, Map<String, Integer> m) {
        for (String k : latest.keySet()) {
            m.put(k, m.getOrDefault(k, 0) + latest.get(k));
        }
    }

    private int getnextright(char[] a, int l, int u) {
        int level = 0;
        int i = l;
        while (i <= u) {
            if (a[i] == '(') {
                level++;
            } else if (a[i] == ')') {
                level--;
                if (level == 0) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfAtoms().countOfAtoms("K4(ON(SO3)2)2"));
    }
}
