public class CountMonobitIntegers {
    public int countMonobit(int n) {
        int base = 1;
        int res = 0;
        while (base - 1 <= n) {
            ++res;
            base *= 2;
        }
        return res;
    }
}
