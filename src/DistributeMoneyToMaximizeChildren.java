public class DistributeMoneyToMaximizeChildren {
    public int distMoney(int money, int children) {

        for (int i = children; i >= 0; --i) {
            if (i * 8 > money) {
                continue;
            }
            int rm = money - i * 8;
            int rc = children - i;
            if (rm < rc) {
                continue;
            } else if (rc == 0 && rm > 0) {
                continue;
            } else if (rm == 4 && rc == 1) {
                continue;
            } else {
                return i;
            }
        }
        return -1;
    }
}
