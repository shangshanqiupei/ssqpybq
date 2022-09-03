public class firsttest2 {
    public static void up(int[] A){
        int i,j,t=0;
        for(i=0;i<=8;i++){
            for(j=0;j<=8-i;j++){
                if(A[j]>A[j+1]){
                  t=A[j];A[j]=A[j+1];A[j+1]=t;
                }
            }
        }
    }
    public static void main (String[] args){
        int [] arr = new int[]{9,14,5,12,10,4,3,13,2,11};
        for(int i=0;i<=9;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println("\n");
        up(arr);
        for(int i=0;i<=9;i++)
        {
            System.out.print(arr[i]+" ");
        }

    }
}
