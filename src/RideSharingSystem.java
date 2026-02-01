import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RideSharingSystem {

    private TreeMap<Integer, Integer> riders = new TreeMap<>();
    private TreeMap<Integer, Integer> drivers = new TreeMap<>();
    private Map<Integer, Integer> rm = new HashMap<>();
    private Map<Integer, Integer> dm = new HashMap<>();
    int ri = 0;
    int di = 0;

    public RideSharingSystem() {

    }

    public void addRider(int riderId) {
        riders.put(ri, riderId);
        rm.put(riderId, ri);
        ++ri;
    }

    public void addDriver(int driverId) {
        drivers.put(di, driverId);
        dm.put(driverId, di);
        ++di;
    }

    public int[] matchDriverWithRider() {
        if (drivers.isEmpty() || riders.isEmpty()) {
            return new int[]{-1, -1};
        }
        int minrtime = riders.firstKey();
        int mindtime = drivers.firstKey();
        int rres = riders.get(minrtime);
        int dres = drivers.get(mindtime);
        rm.remove(rres);
        dm.remove(dres);
        riders.remove(minrtime);
        drivers.remove(mindtime);
        return new int[]{dres, rres};
    }

    public void cancelRider(int riderId) {
        if (!rm.containsKey(riderId)) {
            return;
        }
        int rtime = rm.get(riderId);
        riders.remove(rtime);
        rm.remove(riderId);
    }

}
