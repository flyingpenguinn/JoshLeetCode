public class StoneRemovalGame {
    public boolean canAliceWin(int n) {
        return win(n, 10);
    }

    private boolean win(int n, int t){
        if(n<t){
            return false;
        }
        return !win(n-t, t-1);
    }
}
