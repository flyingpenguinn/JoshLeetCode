import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OnlineElection {

}


class TopVotedCandidate {
    // record top votes at each time point
    TreeMap<Integer, Integer> topvotes = new TreeMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        int[] votes = new int[persons.length];
        int maxi = -1;
        int maxgot = 0;
        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            int voted = persons[i];
            votes[voted]++;
            if (votes[voted] >= maxgot) {
                maxi = voted;
                maxgot = votes[voted];
                // this can accelerate a bit by only saving "changing" ones
                topvotes.put(time, maxi);
            }

        }
    }

    public int q(int t) {
        Integer ceil = topvotes.floorKey(t);
        if (ceil != null) {
            return topvotes.get(ceil);
        }
        return -1;
    }
}
