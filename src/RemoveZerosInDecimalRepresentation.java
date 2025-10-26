public class RemoveZerosInDecimalRepresentation {
    public long removeZeros(long n) {
        String sn = String.valueOf(n);
        sn = sn.replaceAll("0", "");
        return Long.parseLong(sn);
    }
}
