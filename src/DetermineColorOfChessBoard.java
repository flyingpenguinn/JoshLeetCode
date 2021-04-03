public class DetermineColorOfChessBoard {
    public boolean squareIsWhite(String cs) {
        int cind = cs.charAt(0) - 'a';
        int rind = cs.charAt(1) - '1';
        if (cind % 2 == 0) {
            return rind % 2 == 1;
        } else {
            return rind % 2 == 0;
        }
    }
}
