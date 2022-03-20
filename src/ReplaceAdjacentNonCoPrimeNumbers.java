import java.util.*;

public class ReplaceAdjacentNonCoPrimeNumbers {
    public List<Integer> replaceNonCoprimes(int[] a) {
        Deque<Long> st = new ArrayDeque<>();

        for(int i=0; i<a.length; ++i){
            long cand = a[i];
            while(!st.isEmpty() && !coprime(cand, st.peek())){
                long top = st.pop();
                cand = lcm(cand, top);
            }
            st.push(cand);
        }
        List<Integer> res = new ArrayList<>();
        while(!st.isEmpty()){
            res.add((int)st.pop().longValue());
        }
        Collections.reverse(res);
        return res;
    }


    private boolean coprime(long a, long b){
        return gcd(a,b)==1;
    }

    private long gcd(long a, long b){
        return b==0?a:gcd(b, a%b);
    }

    private long lcm(long a, long b){
        return (a*b)/gcd(a,b);
    }
}
