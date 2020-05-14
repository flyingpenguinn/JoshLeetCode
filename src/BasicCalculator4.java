import java.util.*;

public class BasicCalculator4 {

    class Poly {

        static final String Const = "#";

        // no brackets, vars have no coefficient
        public Poly(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == '+' || c == '-') {
                    String var = sb.toString();
                    map.put(var, 1);
                } else if (c == ' ') {
                    continue;
                } else {
                    sb.append(c);
                }
            }
            String var = sb.toString();
            if (Character.isDigit(var.charAt(0))) {
                int newvalue = map.getOrDefault("-", 0) + Integer.valueOf(var);
                map.put(Const, newvalue);
            } else {
                map.put(var, 1);
            }
        }

        // string is all multiplied variables
        // Integer is the coefficient
        // const is #
        // we can use List<String> to avoid splitting strings frequently and the "Const" mark
        Map<String, Integer> map = new HashMap<>();

        public void add(Poly other) {
            for (String key : other.map.keySet()) {
                updateMap(map, key, other.map.get(key));
            }
        }

        public void minus(Poly other) {
            for (String key : other.map.keySet()) {
                updateMap(map, key, -other.map.get(key));
            }
        }

        public void multiply(Poly other) {
            // none of the old keys will survive so would rather new a map
            Map<String, Integer> newmap = new HashMap<>();
            for (String ck : map.keySet()) {
                for (String ok : other.map.keySet()) {
                    String newkey = combine(ck, ok);
                    int newco = other.map.get(ok) * map.get(ck);
                    updateMap(newmap, newkey, newco);
                }
            }
            map = newmap;
        }

        private void updateMap(Map<String, Integer> map, String key, int value) {
            int newvalue = map.getOrDefault(key, 0) + value;
            if (newvalue == 0) {
                map.remove(key);
            } else {
                map.put(key, newvalue);
            }
        }

        private String combine(String ck, String ok) {
            if (ck.equals(Const) && ok.equals(Const)) {
                return Const;
            } else if (ck.equals((Const))) {
                return ok;
            } else if (ok.equals(Const)) {
                return ck;
            } else {
                String[] strs = (ck + "*" + ok).split("\\*");
                Arrays.sort(strs);
                StringBuilder sb = new StringBuilder();
                for (String s : strs) {
                    if (sb.length() != 0) {
                        sb.append("*");
                    }
                    sb.append(s);
                }
                return sb.toString();
            }
        }

        public void solve(String[] evalvars, int[] evalints) {
            for (int i = 0; i < evalvars.length; i++) {
                String var = evalvars[i];
                Set<String> oldset = new HashSet<>(map.keySet());
                for (String ck : oldset) {
                    if (ck.indexOf(var) != -1) {
                        // var name doesnt contain *
                        String[] cks = ck.split("\\*");
                        StringBuilder sb = new StringBuilder();
                        Integer value = map.get(ck);
                        for (String c : cks) {
                            if (!c.equals(var)) {
                                if (sb.length() > 0) {
                                    sb.append("*");
                                }
                                sb.append(c);
                            } else {
                                value *= evalints[i];
                            }
                        }
                        map.remove(ck);
                        String key = sb.length() == 0 ? Const : sb.toString();
                        updateMap(map, key, value);
                    }
                }
            }
        }
    }

    Deque<Poly> stack = new ArrayDeque<>();
    Deque<Character> opsstack = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {

        for (char c : expression.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == '(') {
                opsstack.push(c);
            } else if (c == ')') {
                pushPoly();
                collapseTillBracket();
            } else if (c == '+' || c == '*' || c == '-') {
                pushPoly();
                sb = new StringBuilder();
                if (opsTopEqualOrLower(c)) {
                    opsstack.push(c);
                } else {
                    collapseOnce();
                    opsstack.push(c);
                }
            } else {
                sb.append(c);
            }
        }
        pushPoly();
        collapseAll();
        Poly last = stack.pop();
        last.solve(evalvars, evalints);
        return prettyPrint(last);
    }

    private void pushPoly() {
        if (sb.length() > 0) {
            stack.push(new Poly(sb.toString()));
        }
        sb = new StringBuilder();
    }

    private void collapseAll() {
        while (!opsstack.isEmpty()) {
            collapseOnce();
        }
    }

    class KeyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.equals(Poly.Const)) {
                return 1;
            } else if (o2.equals(Poly.Const)) {
                return -1;
            } else {
                int t1 = o1.split("\\*").length;
                int t2 = o2.split("\\*").length;
                if (t1 != t2) {
                    return Integer.compare(t2, t1); // big first
                } else {
                    return o1.compareTo(o2);
                }
            }
        }
    }

    private List<String> prettyPrint(Poly poly) {
        List<String> r = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        for (String key : poly.map.keySet()) {
            if (poly.map.get(key) != 0) {
                keys.add(key);
            }
        }
        Collections.sort(keys, new KeyComparator());
        for (String k : keys) {
            int value = poly.map.get(k);
            String output = k.equals(Poly.Const) ? String.valueOf(value) : value + "*" + k;
            r.add(output);
        }
        return r;
    }

    private void collapseOnce() {
        Poly p2 = stack.pop();
        Poly p1 = stack.pop();
        Character c = opsstack.pop();
        boolean reverse = !opsstack.isEmpty() && opsstack.peek() == '-';
        doCalc(p1, p2, c, reverse);
    }

    private void doCalc(Poly p1, Poly p2, Character c, boolean reverse) {
        if (c == '+') {
            if (reverse) {
                p1.minus(p2);
            } else {
                p1.add(p2);
            }
        } else if (c == '-') {
            if (reverse) {
                p1.add(p2);
            } else {
                p1.minus(p2);
            }
        } else {
            p1.multiply(p2);
        }
        stack.push(p1);
    }


    private boolean opsTopEqualOrLower(char c) {
        if (opsstack.isEmpty()) {
            return true;
        }
        char top = opsstack.peek();
        if (top == '(') {
            return true;
        }
        if ((top == '+' || top == '-') && c == '*') {
            return true;
        }
        return false;
    }

    private void collapseTillBracket() {
        while (opsstack.peek() != '(') {
            collapseOnce();
        }
        opsstack.pop();
    }

    public static void main(String[] args) {
        String[] vars = {"a", "b"};
        int[] values = {17, 2};
        System.out.println(new BasicCalculator4().basicCalculatorIV("(13 + a - b) * (a + b - 4)", vars, values));
        /*
        System.out.println(new BasicCalculator4().basicCalculatorIV("(e + 8) * (e - 8)", vars, values));
        System.out.println(new BasicCalculator4().basicCalculatorIV("(1 + 8) * (3 - 4)", vars, values));
        System.out.println(new BasicCalculator4().basicCalculatorIV("7 - 7", vars, values));
        System.out.println(new BasicCalculator4().basicCalculatorIV("a * b * c + b * a * c * 4", vars, values));
        System.out.println(new BasicCalculator4().basicCalculatorIV("((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))", vars, values));
        */

    }
}
