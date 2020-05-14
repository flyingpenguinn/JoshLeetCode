/*
LC#640
Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"
 */
public class SolveTheEquation {
    // or we can replace all - as +-, then split on +
    public String solveEquation(String s) {
        String[] ss = s.split("=");
        int[] pl = parse(ss[0]);
        int[] pr = parse(ss[1]);
        int coff = pl[0] - pr[0];
        int cons = pr[1] - pl[1];
        if (coff == 0 && cons == 0) {
            return "Infinite solutions";
        } else if (coff == 0 && cons != 0) {
            return "No solution";
        } else {
            int rt = cons / coff;
            return "x=" + rt;
        }

    }

    int[] parse(String s) {
        int coff = 0;
        int cons = 0;

        int n = s.length();
        int last = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || s.charAt(i) == '+' || s.charAt(i) == '-') {
                String cur = s.substring(last, i);
                if (cur.endsWith("x")) {
                    String coffs = cur.substring(0, cur.length() - 1);
                    if (coffs.equals("+") || coffs.isEmpty()) {
                        // +x or x
                        coff += 1;
                    } else if (coffs.equals("-")) {
                        // -x
                        coff += -1;
                    } else {
                        coff += Integer.valueOf(coffs);
                    }
                } else {
                    if (!cur.isEmpty()) {
                        // -x=-1, when we deal with -1...
                        cons += Integer.valueOf(cur);
                    }
                }
                last = i;
            }
        }
        return new int[]{coff, cons};
    }
}
