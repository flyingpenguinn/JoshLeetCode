import java.util.Stack;

public class MinCostTreeFromLeaf {

    // O(n3) dp solution
    class Rt {
        int res;
        int maxleaf;

        public Rt(int res, int maxleaf) {
            this.res = res;
            this.maxleaf = maxleaf;
        }
    }

    Rt[][] dp;

    public int mctFromLeafValues(int[] a) {
        dp = new Rt[a.length][a.length];
        return doMct(a, 0, a.length - 1).res;
    }

    private Rt doMct(int[] a, int l, int u) {
        if (l > u) {
            return null;
        }
        if (l == u) {
            return new Rt(0, a[l]);
        }
        if (dp[l][u] != null) {
            return dp[l][u];
        }
        Rt min = null;
        for (int i = l; i < u; i++) {
            Rt left = doMct(a, l, i);
            Rt right = doMct(a, i + 1, u);
            if (left != null && right != null) {
                int curmaxleaf = Math.max(left.maxleaf, right.maxleaf);
                int leftright = left.res + right.res;
                int product = left.maxleaf * right.maxleaf;
                Rt cur = new Rt(leftright + product, curmaxleaf);
                //   System.out.println(i + " " + curmaxleaf + " " + leftright + " " + product + " " + cur);
                if (min == null || min.res > cur.res) {
                    min = cur;
                }
            }
        }
        dp[l][u] = min;
        return min;
    }
}

class MinCostTreeFromLeafStack {
    // use stack to get nearest > on both sides
    // TODO: write this myself
    public int mctFromLeafValues(int[] A) {
        int res = 0, n = A.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}