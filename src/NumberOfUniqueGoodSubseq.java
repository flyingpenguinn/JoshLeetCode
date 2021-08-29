public class NumberOfUniqueGoodSubseq {
    private int Mod = 1000000007;

    public int numberOfUniqueGoodSubsequences(String binary) {
        int ends0 = 0;
        int ends1 = 0;
        int has0 = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                ends1 = (ends0 + ends1 + 1);
                ends1 %= Mod;
            } else {
                ends0 = (ends0 + ends1);
                ends0 %= Mod;
                has0 = 1;
            }
        }
        return (ends0 + ends1 + has0) % Mod;
    }
}
