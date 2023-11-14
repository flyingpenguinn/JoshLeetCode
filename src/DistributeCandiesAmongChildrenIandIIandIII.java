public class DistributeCandiesAmongChildrenIandIIandIII {
    // inclusion/ exclusion
    private long countAll(long n) {
        if(n<0){
            return 0;
        }
        // stars and bars, n stars, 2 bars, so c(n+2, 2) ways to put bars
        long allCases = comb(n + 2);
        return allCases;
    }

    public long distributeCandies(int n, int limit) {
        long allCases = countAll(n);

        // we give one of the child limit first, then apply stars and bars
        long bad = limit+1;
        long oneOrMore = 3 * countAll(n - bad);
        long twoOrMore = 3 * countAll(n - 2 * bad);
        long threeOrMore = countAll(n - 3 * bad);
        long res = allCases - oneOrMore + twoOrMore - threeOrMore;
        return res;
    }

    private long comb(long n) {
        long res = n*(n-1)/2;
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new DistributeCandiesAmongChildrenIandIIandIII().distributeCandies(5, 2));
    }
}
