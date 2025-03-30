public class MaxContainersOnShip {
    public int maxContainers(int n, int w, int maxWeight) {
        int all = n * n;
        int num = maxWeight / w;
        return Math.min(all, num);
    }
}
