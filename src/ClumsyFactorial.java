public class ClumsyFactorial {

    // beware of adj, when we -a+b it's actually -(a-b), or + (-a+b)
    public int clumsy(int n) {
        int f = 0;
        int adj = 1;
        while (n > 0) {
            int l = Math.min(n, 4);

            f += doc(n, l, adj);
            adj = -1;
            // System.out.println(n+" "+l+" "+f);
            n -= l;
        }
        return f;
    }

    int doc(int n, int i, int adj) {
        if (i == 1) {
            return adj * n;
        }
        if (i == 2) {
            return adj * n * (n - 1);
        }
        if (i == 3) {
            return adj * n * (n - 1) / (n - 2);
        }
        return adj * n * (n - 1) / (n - 2) + (n - 3);
    }
}
