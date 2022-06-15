public class CalculateAmountPaidInTaxes {
    public double calculateTax(int[][] brackets, int income) {
        int n = brackets.length;
        double res = 0.0;
        for(int i=0; i<n && income>0;  ++i){
            double rev = i==0?brackets[i][0]: (brackets[i][0]-brackets[i-1][0]);
            double taxed = Math.min(rev, income);
            res += taxed*brackets[i][1]/100.0;
            income -= taxed;
        }
        return res;
    }
}
