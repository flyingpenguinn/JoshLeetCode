public class CalcDelayedArrivalTime {
    public int findDelayedArrivalTime(int a, int d) {
        int t = a + d;
        return t % 24;
    }
}
