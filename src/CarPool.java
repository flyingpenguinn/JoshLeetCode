import java.util.TreeMap;

public class CarPool {
    public boolean carPooling(int[][] t, int c) {

        // can be an array because we know its 1k stops
        TreeMap<Integer,Integer> tm= new TreeMap<>();
        for(int[] ti:t){
            tm.put(ti[1],tm.getOrDefault(ti[1],0)+ti[0]);
            tm.put(ti[2],tm.getOrDefault(ti[2],0)-ti[0]);

        }
        int base=0;
        for(int v: tm.values()){
            base+=v;
            if(base>c){
                return false;
            }
        }
        return true;
    }
}
