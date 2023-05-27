import java.util.*;

public class GreatestCommonDivisorTraversal {
    private static int LIMIT = 100000;
    private int[] pa = new int[LIMIT+1];
    private static boolean[] isprime = new boolean[LIMIT+1];
    private static List<Integer> primes = new ArrayList<>();
    private int find(int i){
        if(pa[i] == -1){
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int i, int j){
        int ri = find(i);
        int rj = find(j);
        if(ri == rj){
            return;
        }
        pa[ri] = rj;
    }

    private static void init(){
        if(primes.size()>0){
            return;
        }
        Arrays.fill(isprime, true);
        isprime[1] = false;
        for(int i=2; i<=LIMIT; ++i){
            if(!isprime[i]){
                continue;
            }
            if(i>2 && i%2==0){
                isprime[i] = false;
                continue;
            }
            for(int j=2*i; j<=LIMIT; j+=i){
                isprime[j] = false;
            }
        }
        for(int i=2; i<=LIMIT; ++i){
            if(isprime[i]){
                primes.add(i);
            }
        }
    }

    private Map<Integer, List<Integer>> m = new HashMap<>();

    public boolean canTraverseAllPairs(int[] a) {
        init();
        for(int ai: a){
            if(ai==1){
                return a.length==1?true:false;
            }
        }
        Arrays.fill(pa, -1);
        Set<Integer> seen = new HashSet<>();

        for(int ai: a){
            if(seen.contains(ai)){
                continue;
            }
            seen.add(ai);
            if(isprime[ai]){
                m.computeIfAbsent(ai,k-> new ArrayList<>()).add(ai);
                continue;
            }
            int oai = ai;
            for(int i=0; i<primes.size(); ++i){
                int p = primes.get(i);
                if(p*p>ai){
                    break;
                }

                if(ai%p==0){
                    m.computeIfAbsent(p,k-> new ArrayList<>()).add(oai);
                    while(ai%p==0){
                        ai /= p;
                    }

                }
                // what's left must either be 1 or a prime that is bigger than sqrt(ai)
                if(isprime[ai]){

                    m.computeIfAbsent(ai,k-> new ArrayList<>()).add(oai);
                    break;
                }
            }
        }
        for(int k: m.keySet()){
            List<Integer> list = m.get(k);
            for(int i=0; i+1<list.size(); ++i){
                int v1 = list.get(i);
                int v2 = list.get(i+1);
                union(v1, v2);
            }
        }
        int root = 0;
        for(int ai: seen){
            if(pa[ai]==-1){
                ++root;
            }
            if(root>1){
                return false;
            }
        }
        return true;
    }
}
