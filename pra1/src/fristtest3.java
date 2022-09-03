import java.util.*;
public class fristtest3 {
    public static void main (String[] args){
        int [][]arr = new int [5][5];
        Random rand = new Random();
        int i,j,sum=0,max;
        for(i=0;i<=4;i++){
            for(j=0;j<=4;j++){
                arr[i][j] = rand.nextInt(50);
            }
        }
        for(i=0;i<=4;i++){
            for(j=0;j<=4;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("\n");
        }
        for(i=0;i<=4;i++){
            if(i==0||i==4)
            {
                for(j=0;j<=4;j++){
                    sum+=arr[i][j];
                }
            }
            else {
                sum+=arr[i][0];
                sum+=arr[i][4];
            }
        }
        System.out.println("最外一圈之和为"+sum);
        max=arr[0][0];
        j=0;
        for(i=1;i<=4;i++){
            if(arr[i][i]>max){
                max=arr[i][i];
                j=i;
            }
        }
        System.out.println("最大值为"+max);
        System.out.println("位置是第"+(j+1)+"行第"+(j+1)+"列");


    }
}
