
public class PairOfSongsDurationDivisible {
    public int numPairsDivisibleBy60(int[] time) {
        if (time.length == 0) {
            return 0;
        }

        int count = 0;
        int[] songs = new int[60];
        songs[time[0] % 60] = 1;
        for (int i = 1; i < time.length; i++) {
            int mod = time[i] % 60;
            int key = (60 - mod) % 60;// could be 0 and we dont wnat 60
            count += songs[key];
            songs[mod]++;
        }
        return count;
    }
}
