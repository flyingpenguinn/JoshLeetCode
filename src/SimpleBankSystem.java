import java.util.HashMap;
import java.util.Map;

public class SimpleBankSystem {
    class Bank {
        private Map<Integer,Long> m = new HashMap<>();
        private int n = 0;
        public Bank(long[] b) {
            for(int i=0; i<b.length; ++i){
                m.put(i+1, b[i]);
            }
            n = b.length;
        }

        public boolean transfer(int a1, int a2, long money) {
            if(a1<1 || a1>n || a2<1 || a2>n){
                return false;
            }
            if(m.getOrDefault(a1, 0L) < money){
                return false;
            }
            update(m, a1, -money);
            update(m, a2, money);
            return true;
        }

        public boolean deposit(int account, long money) {
            if(account<1 || account>n){
                return false;
            }
            update(m, account, money);
            return true;
        }

        public boolean withdraw(int account, long money) {
            if(account<1 || account>n ){
                return false;
            }
            if(m.getOrDefault(account, 0L) < money){
                return false;
            }
            update(m, account, -money);
            return true;
        }

        private void update(Map<Integer,Long> m, int k, long d){
            long nv = m.getOrDefault(k, 0L)+d;
            m.put(k, nv);
        }
    };

/**
 * Your Bank object will be instantiated and called as such:
 * Bank* obj = new Bank(balance);
 * bool param_1 = obj->transfer(account1,account2,money);
 * bool param_2 = obj->deposit(account,money);
 * bool param_3 = obj->withdraw(account,money);
 */
}
