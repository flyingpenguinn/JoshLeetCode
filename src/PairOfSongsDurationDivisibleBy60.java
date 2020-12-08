
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
    // this is count sum pairs. note the similarity with 3sum multiplicity
    public int numPairsDivisibleBy60(int[] a) {
        int[] mods = new int[60];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int mod = a[i] % 60;
            mods[mod]++;
        }
        int res = mods[0] * (mods[0] - 1) / 2;
        int i = 0;
        int j = 59;
        while (i <= j) {
            if (i + j == 60) {
                if (i == j) {
                    res += mods[i] * (mods[i] - 1) / 2;
                } else {
                    res += mods[i] * mods[j];
                }
                i++;
                j--;
            } else if (i + j < 60) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
