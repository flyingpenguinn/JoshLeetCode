import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1106
Return the result of evaluating a given boolean expression, represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...


Example 1:

Input: expression = "!(f)"
Output: true
Example 2:

Input: expression = "|(f,t)"
Output: true
Example 3:

Input: expression = "&(t,f)"
Output: false
Example 4:

Input: expression = "|(&(t,f,t),!(t))"
Output: false


Constraints:

1 <= expression.length <= 20000
expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
expression is a valid expression representing a boolean, as given in the description.
 */
public class ParseBooleanExp {
    // similar to brace expansion: must have doblock and docomma. docomma separates stuff to blocks, then handle block.
    // after stripping a block out, do comma again
    public boolean parseBoolExpr(String exp) {
        return doblock(exp, 0, exp.length() - 1);
    }

    // single t,f, or () blocks
    boolean doblock(String s, int l, int u) {
        if (l == u) {
            return s.charAt(l) == 't';
        } else if (s.startsWith("!(", l)) {
            int nextright = nextright(s, l + 1, u);
            boolean inner = docomma(s, l + 2, nextright - 1, true);
            return !inner;
        } else if (s.startsWith("&(", l)) {
            int nextright = nextright(s, l + 1, u);
            return docomma(s, l + 2, nextright - 1, true);
        } else if (s.startsWith("|(", l)) {
            int nextright = nextright(s, l + 1, u);
            return docomma(s, l + 2, nextright - 1, false);
        }
        throw new IllegalArgumentException(s.substring(l, u + 1));
    }

    // comma separated lists, could contain blocks
    boolean docomma(String s, int l, int u, boolean isand) {
        // handle level 0 commas
        int start = l;
        int level = 0;
        for (int i = l; i <= u + 1; i++) {
            // , on the ground level does the separation. must be on ground level itself!
            if (i == u + 1 || (s.charAt(i) == ',' && level == 0)) {
                boolean inner = doblock(s, start, i - 1);
                if (!isand && inner) {
                    return true;
                } else if (isand && !inner) {
                    return false;
                }
                start = i + 1;
            } else if (s.charAt(i) == '(') {
                level++;
            } else if (s.charAt(i) == ')') {
                level--;
            }
        }
        return isand;
    }

    private int nextright(String s, int l, int u) {
        int level = 0;
        int i = l;
        while (i <= u) {
            if (s.charAt(i) == ')') {
                level--;
                if (level == 0) {
                    return i;
                }
            } else if (s.charAt(i) == '(') {
                level++;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ParseBooleanExp().parseBoolExpr("|(f,t)"));
        System.out.println(new ParseBooleanExp().parseBoolExpr("!(f)"));

        System.out.println(new ParseBooleanExp().parseBoolExpr("&(f,t)"));
        System.out.println(new ParseBooleanExp().parseBoolExpr("|(&(t,f,t),!(t))"));
        System.out.println(new ParseBooleanExp().parseBoolExpr("|(f,&(t,t))"));


        System.out.println(new ParseBooleanExp().parseBoolExpr("!(&(&(!(&(f)),&(t),|(f,f,t)),&(t),&(t,t,f)))"));
    }
}


// ( in nums stack to help mark the end of numbers
class ParseBooleanExpStack {
    public boolean parseBoolExpr(String exp) {
        Deque<Character> bools = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '|' || c == '&' || c == '!') {
                ops.push(c);
            } else if (c == 't' || c == 'f' || c == '(') {
                bools.push(c);
            } else if (c == ')') {
                char op = ops.pop();
                boolean res = op == '|' ? false : true;
                while (!bools.isEmpty()) {
                    char booltop = bools.pop();
                    if (booltop == '(') {
                        break;
                    } else if (op == '|') {
                        res = res || getBool(booltop);
                    } else if (op == '&') {
                        res = res && getBool(booltop);
                    } else {
                        // !
                        res = !getBool(booltop);
                    }
                }
                bools.push(getChar(res));
            } else {
                // ,
            }
        }
        return getBool(bools.pop());
    }

    private boolean getBool(char pop) {
        return pop == 't';
    }

    private char getChar(boolean b) {
        return b ? 't' : 'f';
    }
}
