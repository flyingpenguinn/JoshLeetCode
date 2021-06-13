public class FindStudentThatWillReplaceChalk {
    public int chalkReplacer(int[] chalk, int k) {
        long sum = 0;
        int n = chalk.length;
        for(int i=0; i<n; i++){
            sum += chalk[i];
        }
        long mod = k % sum;
        for(int i=0; i<n; i++){
            if(mod<chalk[i]){
                return i;
            }else{
                mod -= chalk[i];
            }
        }
        return 0;
    }
}
