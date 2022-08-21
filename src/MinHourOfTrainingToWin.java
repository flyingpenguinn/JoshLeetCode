public class MinHourOfTrainingToWin {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int n = energy.length;
        int esum = 0;
        for (int ei : energy) {
            esum += ei;
        }
        int engap = Math.max(0, esum + 1 - initialEnergy);
        int l = 0;
        int u = (int) 1e4;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int ce = initialExperience + mid;
            boolean good = true;
            for (int i = 0; i < n; ++i) {
                if (ce <= experience[i]) {
                    good = false;
                    break;
                }
                ce += experience[i];
            }
            if (good) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return engap + l;
    }
}
