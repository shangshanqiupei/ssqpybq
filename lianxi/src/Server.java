import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        String readline=null;
        String inTemp=null;

        //创建服务端的端口地址
        int port=4000;
        ServerSocket serverSocket=new ServerSocket(port);//serverSocket需要持久打开

        while(true) {

            Socket socket=serverSocket.accept();//serverSocket需要持续接收新的socket请求
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));//接收客户端发送的信息
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream());//输出流，往客户端发送信息时使用

            inTemp=socketIn.readLine();
            if(inTemp!=null&&inTemp.equals("bye")){
                socketIn.close();
                socketOut.close();
                socket.close();
                break;
            }
            else if(inTemp!=null&&!inTemp.equals("")&&!inTemp.equals("null")){
                //查看客户端socket请求的ip地址和端口信息
                InetAddress inetAddress=socket.getInetAddress();
                String ip=inetAddress.getHostAddress();
                int clientPort=socket.getPort();
                System.out.println("内容为："+inTemp+"\n"+"ip:"+ip+"\n"+"clientPort:"+clientPort);

                readline="server收到了！";
                socketOut.println(readline+inTemp);//往客户端发出反馈信息
                socketOut.flush();
            }

            socketIn.close();
            socketOut.close();
            socket.close();
            //注意在while循环中不要关闭serverSocket

        }
        serverSocket.close();

    }
}
