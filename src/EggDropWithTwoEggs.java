public class EggDropWithTwoEggs {
    public int twoEggDrop(int n) {
        double p1= Math.sqrt(8*n-1.0);
        double p2 = (-1+p1)/2;
        return (int)Math.ceil(p2);
    }
}
