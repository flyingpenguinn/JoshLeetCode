import java.util.Arrays;

public class DividePlayersToEqualSkills {
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        long sum = 0;
        for (int s : skill) {
            sum += s;
        }
        int teams = n / 2;
        long t = sum / teams;
        Arrays.sort(skill);
        int i = 0;
        int j = n - 1;
        long res = 0;
        while (i < j) {
            long csum = skill[i] + skill[j];
            if (csum != t) {
                return -1;
            }
            res += skill[i] * skill[j];
            ++i;
            --j;
        }
        return res;
    }
}
