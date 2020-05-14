public class ConvertToBase_2 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int rem = n % (-2);
            n = n / (-2);
            if (rem < 0) {
                rem += 2;
                n+=1;
            }
            sb.append(rem);
        }
        return sb.reverse().toString();

    }


    public static void main(String[] args) {
        System.out.println(new ConvertToBase_2().baseNeg2(13));
    }
}
