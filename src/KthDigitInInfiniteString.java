class Solution {
    private long pow10(long n) {
        return (long) Math.pow(10, n);
    }

    public int kthDigit(long k) {
        long accu = 0;
        long remcurbd = 0;
        int curbd = 0;

        for (int bd = 0; bd <= 15; ++bd) {
            long curaccu = 9 * pow10(bd) * (bd + 1);
            long prevaccu = accu;
            accu += curaccu;

            if (accu >= k) {
                remcurbd = k - prevaccu;
                curbd = bd;
                break;
            }
        }

        --remcurbd;

        if (curbd == 0) {
            return (int) remcurbd + 1;
        }

        long cbandblock = remcurbd / (10 * (curbd + 1));
        long bnum = cbandblock + pow10(curbd - 1);

        long rembnum = remcurbd % (10 * (curbd + 1));

        long bnumth = rembnum / (curbd + 1);

        if (bnum % 2 == 1) {
            bnumth = 9 - bnumth;
        }

        long thenum = 10 * bnum + bnumth;

        int index = (int) (rembnum % (curbd + 1));

        String nums = String.valueOf(thenum);
        return nums.charAt(index) - '0';
    }
}
