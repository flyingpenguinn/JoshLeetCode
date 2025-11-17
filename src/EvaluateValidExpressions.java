import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EvaluateValidExpressions {
    BiFunction<Long, Long, Long> add = (x, y) -> x + y;
    BiFunction<Long, Long, Long> sub = (x, y) -> x - y;
    BiFunction<Long, Long, Long> mul = (x, y) -> x * y;
    BiFunction<Long, Long, Long> div = (x, y) -> x / y;
    private Map<String, BiFunction<Long, Long, Long>> m = new HashMap<>();

    {
        m.put("add", Long::sum);
        m.put("sub", sub);
        m.put("mul", mul);
        m.put("div", div);
    }

    public long evaluateExpression(String s) {
        int n = s.length();
        int i = 0;
        Deque<String> opstack = new ArrayDeque<>();
        Deque<Long> numstack = new ArrayDeque<>();

        while (i < n) {
            String op = getCurrentOp(s, i);
            if (op != null) {
                opstack.push(op);
                i += 4;
            } else if (s.charAt(i) == ',') {
                ++i;
            } else if (s.charAt(i) == ')') {
                long num2 = numstack.pop();
                long num1 = numstack.pop();
                Long cres = m.get(opstack.pop()).apply(num1, num2);
                numstack.push(cres);

                ++i;
            } else {
                int p = i;
                int oldp = p;
                while (p < n && (s.charAt(p) == '-' || Character.isDigit((s.charAt(p))))) {
                    ++p;
                }
                String seg = s.substring(oldp, p);
                Long cnum = Long.parseLong(seg);
                numstack.push(cnum);
                i = p;
            }
        }
        return numstack.pop();
    }


    private String getCurrentOp(String s, int i) {
        String op = null;
        for (String k : m.keySet()) {
            if (s.startsWith(k, i)) {
                op = k;
                break;
            }
        }
        return op;
    }

    public static void main(String[] args) {
        System.out.println(new EvaluateValidExpressions().evaluateExpression("div(mul(4,sub(9,5)),add(1,1))"));
        System.out.println(new EvaluateValidExpressions().evaluateExpression("add(2,3)"));
        System.out.println(new EvaluateValidExpressions().evaluateExpression("-42"));

    }
}

