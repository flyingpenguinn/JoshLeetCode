public class SumOFDigitsInBaseK {
    public int sumBase(int n, int k) {
        String res = Integer.toString(n, k);
        //     System.out.println(res);
        int ri = 0;
        for (int i = 0; i < res.length(); i++) {
            ri += res.charAt(i) - '0';
        }
        return ri;
    }
}
