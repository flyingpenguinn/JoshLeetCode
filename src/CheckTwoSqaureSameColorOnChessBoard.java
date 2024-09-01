public class CheckTwoSqaureSameColorOnChessBoard {
    public boolean checkTwoChessboards(String c1, String c2) {
        int a1 = c1.charAt(0) - 'a';
        int b1 = c1.charAt(1) - '1';
        int a2 = c2.charAt(0) - 'a';
        int b2 = c2.charAt(1) - '1';
        return (a1 + b1) % 2 == (a2 + b2) % 2;
    }
}
