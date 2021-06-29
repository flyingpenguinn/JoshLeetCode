import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FancySequence {

    // gist is we can always represent a number as *m+a
    // if there is no append,
    // for each idx, it's raw*multi+inc.
    // when we +, we add on inc
    // when we *, we multi on multi and inc alike
    // when we have append, we note down the multi and add value till it. we will later negate it from the accumulated values
    // only trick is multi might be too big for long. so we use mod inverse for / under mod situation
    class Fancy {
        private List<Integer> list = new ArrayList<>();
        private List<Long> adds = new ArrayList<>();
        private List<Long> multis = new ArrayList<>();
        private int mod = 1000000007;
        private long multi = 1;
        private long add = 0;

        public Fancy() {

        }

        // if we ever meet newly added value we try to negate by taking the current add/muti out
        public void append(int val) {
            list.add(val);
            adds.add(add);
            multis.add(multi);
        }

        public void addAll(int inc) {
            add += inc;
            add %= mod;
        }

        public void multAll(int m) {
            multi *= m;
            add *= m;
            multi %= mod;
            add %= mod;
        }

        public int getIndex(int idx) {
            if (idx >= list.size()) {
                return -1;
            }
            long raw = list.get(idx);
            long oldmulti = multis.get(idx); // when we insert this number, we multied to this point
            long cmulti = multi * modInverse((int) oldmulti, mod); // now we multied to multi, so this number has been through all later as multi/oldmulti. we use mod inverse to do divide
            cmulti %= mod;
            long oldadd = adds.get(idx); // we added to oldadd when we inserted idx
            long cadd = add - cmulti * oldadd;
            // ((oldadd+xxx)*yyy+zzz)*ppp+ttt.... we want to get rid of oldadd*yyy*ppp*... but keep the rest as they are what's added on top of the old value.
            // we want to get the value as if oldadd = the raw value. so we make it 0 first then use cmulti
            cadd %= mod;
            if (cadd < 0) {
                cadd += mod;
            }
            // (c*mold+aold)*mnew+anew. to get anew we do a-aold*mnew
            long rt = raw * cmulti + cadd;
            int res = (int) (rt % mod);
            return res;
        }


        // copied mod inverse code
        private int modInverse(int a, int m) {
            int m0 = m;
            int y = 0, x = 1;
            if (m == 1) {
                return 0;
            }
            while (a > 1) {
                // q is quotient
                int q = a / m;
                int t = m;
                // m is remainder now, process
                // same as Euclid's algo
                m = a % m;
                a = t;
                t = y;
                // Update x and y
                y = x - q * y;
                x = t;
            }
            // Make x positive
            if (x < 0) {
                x += m0;
            }
            return x;
        }
    }
}
