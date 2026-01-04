public class ReverseStringPrefix {
    public String reversePrefix(String s, int k) {
        String sub = s.substring(0, k);
        String sub2 = s.substring(k);
        return new StringBuilder(sub).reverse().toString() + sub2;
    }
}
