/*
LC#1150
public boolean isMajorityElement(int[] a, int t) {
        int i = 0;
        while(i<a.length){
            int j = i+1;
            while(j<a.length && a[j]==a[i]){
                j++;
            }
            int count = j-i;
            if(count>a.length/2){
                return a[i] == t;
            }
            i=j;
        }
        return false;
    }
 */
public class CheckIfNumberIsMajorityElementSorted {
    public boolean isMajorityElement(int[] a, int t) {
        int i = 0;
        while (i < a.length) {
            int j = i + 1;
            while (j < a.length && a[j] == a[i]) {
                j++;
            }
            int count = j - i;
            if (count > a.length / 2) {
                return a[i] == t;
            }
            i = j;
        }
        return false;
    }
}
