import java.util.*;

public class DetectSquares {

    private Map<Integer,Map<Integer, Integer>> samey = new HashMap<>();
    private Map<Integer,Map<Integer, Integer>> samex = new HashMap<>();

    public DetectSquares() {

    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        Map<Integer,Integer> smy = samey.getOrDefault(y, new HashMap<>());
        smy.put(x, smy.getOrDefault(x, 0)+1);
        samey.put(y, smy);
        Map<Integer,Integer> smx = samex.getOrDefault(x, new HashMap<>());
        smx.put(y, smx.getOrDefault(y, 0)+1);
        samex.put(x, smx);
    }

    public int count(int[] point) {
        int rt = refresh(point);
        //  add(point);
        return rt;
    }

    private int refresh(int[] p){

        int x = p[0];
        int y = p[1];
        int count = 0;
        Map<Integer,Integer> xs = samey.getOrDefault(y, new HashMap<>());
        for(int xi: xs.keySet()){
            int delta = Math.abs(x-xi);
            int xcount = 1; // just this point nothing else
            int xicount = xs.get(xi);
            if(delta == 0){
                continue;
            }
            Map<Integer, Integer> xiys = samex.getOrDefault(xi, new HashMap<>());
            Map<Integer,Integer> xys = samex.getOrDefault(x, new HashMap<>());
            int ty1 = y+delta;
            if(xiys.containsKey(ty1) && xys.containsKey(ty1)){
                count += xiys.get(ty1)*xcount* xys.get(ty1)*xcount*xicount;
            }
            int ty2 = y-delta;
            if(xiys.containsKey(ty2) && xys.containsKey(ty2)){
                count += xiys.get(ty2)*xcount* xys.get(ty2)*xcount*xicount;
            }
        }
        return count;
    }
}
