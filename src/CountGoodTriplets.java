public class CountGoodTriplets {
    public int countGoodTriplets(int[] input, int a, int b, int c) {
        int res = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (Math.abs(input[i] - input[j]) <= a
                            && Math.abs(input[k] - input[j]) <= b
                            && Math.abs(input[i] - input[k]) <= c) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
