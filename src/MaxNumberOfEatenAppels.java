public class MaxNumberOfEatenAppels {
    // similar to interval problem, we negate when appels rot
    public int eatenApples(int[] a, int[] d) {

        int[] apps = new int[200001];
        for (int i = 0; i < a.length; i++) {
            apps[i] += a[i];
            apps[i + d[i]] -= a[i];
        }
        int sum = 0;
        int res = 0;
        int accu = 0;// in this sprint how many did we eat- we will need to discount these when apps[i] is <0
        for (int i = 0; i < apps.length; i++) {
            if (apps[i] < 0) {
                sum += apps[i] + accu;
                accu = 0;
            } else {
                sum += apps[i];
            }
            if (sum - res > 0) {
                res++;
                // note that we ate apples so that when we negate we pay these back
                accu++;
            }
        }
        return res;
    }
}
