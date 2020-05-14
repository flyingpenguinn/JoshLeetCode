public class SumWithoutAddOrMinus {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        // add without carry in ^, take carry in & and <<1 to the next digit
        return getSum(a ^ b, (a & b) << 1);
    }
}
