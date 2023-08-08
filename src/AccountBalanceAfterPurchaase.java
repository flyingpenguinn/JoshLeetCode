public class AccountBalanceAfterPurchaase {
    public int accountBalanceAfterPurchase(int t) {
        int spent = (int) (Math.ceil(t * 1.0 / 10.0)) * 10;
        int spent2 = t / 10 * 10;
        //   System.out.println(spent+" "+spent2);
        if (Math.abs(t - spent) > Math.abs(t - spent2)) {
            spent = spent2;
        }
        return 100 - spent;
    }
}
