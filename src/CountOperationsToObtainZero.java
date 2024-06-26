public class CountOperationsToObtainZero {
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        return 1 + (num1 >= num2 ? countOperations(num1 - num2, num2) : countOperations(num1, num2 - num1));
    }
}
