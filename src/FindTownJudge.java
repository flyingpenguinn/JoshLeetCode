public class FindTownJudge {
    public int findJudge(int n, int[][] trust) {
        int[] ind = new int[n];
        int[] outd = new int[n];
        for (int[] t : trust) {
            ind[t[1]]++;
            outd[t[0]]++;
        }
        for (int i = 0; i < n; i++) {
            if (ind[i] == n - 1 && outd[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}

class FindTownJudgeOneArray {
    // be loser then -1, be winner then +1
    public int findJudge(int n, int[][] trust) {
        int r = 1;
        int[] score = new int[n + 1];
        for (int[] t : trust) {
            score[t[0]]--;
            score[t[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (score[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}