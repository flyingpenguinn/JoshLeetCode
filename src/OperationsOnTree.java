import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OperationsOnTree {
    class LockingTree {

        private int[] m;
        private Map<Integer,Set<Integer>> g=new HashMap<>();
        private int[] pa;
        public LockingTree(int[] parent) {
            this.pa = parent;
            m = new int[parent.length];
            for(int i=0; i<parent.length; ++i){
                if(parent[i] ==-1){
                    continue;
                }
                g.computeIfAbsent(parent[i], k-> new HashSet<>()).add(i);
            }
        }

        public boolean lock(int num, int user) {
            if(m[num] != 0){
                return false;
            }
            m[num] = user;
            return true;
        }

        public boolean unlock(int num, int user) {
            if(m[num]==user){
                m[num] = 0;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            if(m[num] != 0){
                return false;
            }
            int cur = num;
            while(pa[cur] !=-1){
                if(m[pa[cur]] != 0){
                    return false;
                }
                cur = pa[cur];
            }
            if(!dfsunlock(num)){
                return false;
            }
            m[num] = user;
            return true;
        }


        private boolean dfsunlock (int num){
            boolean rt = false;
            if(m[num] != 0){
                rt = true;
                m[num] = 0;
            }
            Set<Integer> dec = g.getOrDefault(num, new HashSet<>());
            for (int ne: dec){
                rt |= dfsunlock(ne);
            }
            return rt;
        }
    }

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */
}
