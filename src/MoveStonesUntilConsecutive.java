import java.util.Arrays;

public class MoveStonesUntilConsecutive {
    public int[] numMovesStones(int a1, int a2, int a3) {
        int[] a= new int[3];
        a[0]=a1;
        a[1]=a2;
        a[2]=a3;
        Arrays.sort(a);

        if(a[0]==a[1]-1 && a[1]==a[2]-1){
            return toarr(0,0);
        }else if(a[0]==a[1]-1){
            return toarr(1,a[2]-a[1]-1);
        }else if(a[2]==a[1]+1){
            return toarr(1,a[1]-a[0]-1);
        }else{
            int min=2;
            if(a[0]==a[1]-2 || a[2]==a[1]+2){
                min=1;
            }
            return toarr(min,a[2]-a[1]-1+a[1]-a[0]-1);
        }
    }

    int[] toarr(int a, int b){
        int[] r= new int[2];
        r[0]=a;
        r[1]=b;
        return r;
    }
}
