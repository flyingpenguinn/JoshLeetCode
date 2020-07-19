public class WaterBottles {

    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        while (numBottles >= numExchange) {
            int quot = numBottles / numExchange;
            int mod = numBottles % numExchange;
            sum += quot;
            numBottles = quot + mod;
        }
        return sum;
    }

}
