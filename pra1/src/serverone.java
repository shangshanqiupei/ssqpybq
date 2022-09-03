import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class serverone {
    public static void main(String[] args){
        try {
            ServerSocket serverone = new ServerSocket(1234);
            System.out.println("--------服务器连接中------------");
            Socket socketone = serverone.accept();
            System.out.println("连接成功！");
            BufferedReader is = new BufferedReader(new InputStreamReader(socketone.getInputStream()));
            PrintWriter os = new PrintWriter(socketone.getOutputStream());

            String name = is.readLine();
            while (true) {
                String str = is.readLine();
                System.out.println(str);
                if (str.contains("Exit")) {
                    String answer = "Bye";
                    os.println("To "+name+": "+answer);
                    os.flush();
                    break;
                }
                else if (str.contains("what time is it")) {
                    LocalTime time = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //按格式输出当前时间
                    os.println("To "+name+": "+time.format(formatter));
                    os.flush();
                }
                else {
                    String answer = "输入Exit退出或者输入waht time is it获取当前时间。";
                    os.println("To "+name+": "+answer);
                    os.flush();
                }
            }
            os.close();
            is.close();
            socketone.close();
            serverone.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
