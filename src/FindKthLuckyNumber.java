public class FindKthLuckyNumber {
    public String kthLuckyNumber(int k) {
        long base = 2;
        long sum = 2;
        int digits = 1;
        while (k > sum) {
            base *= 2;
            sum += base;
            //  cout<<"base="<<base<<" sum="<<sum<<endl;
            ++digits;
        }
        sum -= base;
        long diff = k - sum - 1;
        // cout<<sum<<" "<<diff<<" "<<digits<<endl;
        char[] res = new char[digits];
        while (digits > 0) {
            res[digits - 1] = '4';
            --digits;
        }
        int rn = res.length;
        String diffStr = Long.toBinaryString(diff);
        int dn = diffStr.length();
        //  cout<<diffStr<<endl;
        for (int i = dn - 1; i >= 0; --i) {
            int delta = dn - 1 - i;
            if (rn - 1 - delta < 0) {
                break;
            }
            //    cout<<delta<<" "<<diffStr[i]<<endl;
            if (diffStr.charAt(i) == '1') {
                res[rn - 1 - delta] = '7';
            }
        }
        return new String(res);
    }
}
