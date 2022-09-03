import java.util.*;

public class ComputeTime {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException  {
        String transport;
        Scanner reader=new Scanner(System.in);
        transport=(String)reader.next();
        int a=reader.nextInt(),b=reader.nextInt(),c=reader.nextInt();
        Common atransport;
        atransport = (Common)Class.forName(transport).newInstance();
        System.out.println(atransport.calculateVelocity(a, b, c));
        reader.close();
    }

}
