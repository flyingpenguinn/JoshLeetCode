public class HexadecimalAndHexatrigesimalConversion {
    public String concatHex36(int n) {
        String s1 = Integer.toString(n * n, 16);
        String s2 = Integer.toString(n * n * n, 36);
        return (s1 + s2).toUpperCase();
    }
}
