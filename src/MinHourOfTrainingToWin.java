public class MinHourOfTrainingToWin {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int n = energy.length;
        int esum = 0;
        for (int ei : energy) {
            esum += ei;
        }
        int engap = Math.max(0, esum + 1 - initialEnergy);
        int sum = initialExperience;
        int exgap = 0;
        for (int exi : experience) {
            if (sum < exi + 1) {
                int delta = exi + 1 - sum;
                sum += delta;
                exgap += delta;
            }
            sum += exi;
        }
        return engap + exgap;
    }
}
