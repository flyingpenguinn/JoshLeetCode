import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.*;

public class CountWaysToBuildRoomInAntColony {
    //@TODO: write myself
    int mod = 1000000007;
    List<Integer> graph[];
    int cnt[];
    long fact[];

    public int waysToBuildRooms(int[] A) {
        int n = A.length;
        graph = new ArrayList[n];
        cnt = new int[n];
        Arrays.setAll(graph, e -> new ArrayList<>());


        fact = new long[A.length + 10];
        fact[0] = fact[1] = 1;
        for (int i = 2; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i;
            fact[i] %= mod;
        }


        for (int i = 1; i < A.length; i++) {
            int pre = A[i];
            graph[pre].add(i);
        }


        long res[] = dfs(0);
        return (int) (res[1] % mod);
    }


    public long[] dfs(int root) {
        long res[] = new long[]{1, 0};
        int cnt = 0;
        List<long[]> list = new ArrayList<>();
        for (int next : graph[root]) {
            long v[] = dfs(next);
            cnt += v[0];
            list.add(v);
        }

        res[0] += cnt;
        long com = 1;
        for (long p[] : list) {
            long choose = C(cnt, (int) (p[0]));
            cnt -= p[0];
            com = com * choose;
            com %= mod;
            com = com * p[1];
            com %= mod;
        }

        res[1] = com;
        return res;
    }


    public long C(int i, int j) { // C(20,3)=20!/(17!*3!)
        long mod = 1000000007;
        long a = fact[i];
        long b = ((fact[i - j] % mod) * (fact[j] % mod)) % mod;
        BigInteger B = BigInteger.valueOf(b);
        long binverse = B.modInverse(BigInteger.valueOf(mod)).longValue();
        return ((a) * (binverse % mod)) % mod;

    }
}
