public class EvenNumberOfKnightMoves {
    // one move of knight gets to diff color
    public boolean canReach(int[] start, int[] target) {
        return (start[0] + start[1]) % 2 == (target[0] + target[1]) % 2;
    }
}
