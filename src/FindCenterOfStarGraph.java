public class FindCenterOfStarGraph {
    public int findCenter(int[][] es) {
        return (es[0][0] == es[1][0] || es[0][0] == es[1][1]) ? es[0][0] : es[0][1];
    }
}
