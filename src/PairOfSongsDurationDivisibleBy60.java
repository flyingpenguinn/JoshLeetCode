
public class PairOfSongsDurationDivisibleBy60 {
    // two ways of doing two sum: count sum pairs, or hashmap + pre. below is hashmap + pre
    public int numPairsDivisibleBy60(int[] time) {
        if (time.length == 0) {
            return 0;
        }
        int count = 0;
        int[] songs = new int[60];
        for (int i = 0; i < time.length; i++) {
            int mod = time[i] % 60;
            int key = (60 - mod) % 60;
            // so that mod = 0 will still get 0
            count += songs[key];
            songs[mod]++;
        }
        return count;
    }
}

class PairOfSongsDivisibleAnotherWay {
    // this is count sum pairs
    public int numPairsDivisibleBy60(int[] a) {
        int[] mods = new int[60];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int mod = a[i] % 60;
            mods[mod]++;
        }
        int res = 0;
        for (int i = 1; i <= 29; i++) {
            res += mods[i] * mods[60 - i];
        }
        if (mods[30] != 0) {
            res += mods[30] * (mods[30] - 1) / 2;
        }
        if (mods[0] != 0) {
            res += mods[0] * (mods[0] - 1) / 2;
        }
        return res;
    }
}
