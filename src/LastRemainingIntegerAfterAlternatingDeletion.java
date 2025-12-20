public class LastRemainingIntegerAfterAlternatingDeletion {
    // Joseph ring!
    public long lastInteger(long n) {
        return j(n);
    }

    private long j(long n) {
        if (n == 1) {
            return 1;
        }
        if (n % 2 == 0) {
            return 2 * q(n / 2) - 1;
        } else {
            return 2 * q((n + 1) / 2) - 1;
        }
    }

    private long q(long n) {
        if (n == 1) {
            return 1;
        }
        if (n % 2 == 0) {
            return 2 * j(n / 2);
        } else {
            return 2 * j((n + 1) / 2) - 1;
        }
    }
}
