import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread{
    JTextField jTextField;
    JTextArea jTextArea;

    //构造方法，把客户端界面的控件对象传入进来
    public ClientThread(JTextField jTextField,JTextArea jTextArea){
        this.jTextField=jTextField;
        this.jTextArea=jTextArea;
    }


    @Override
    public void run() {
        String readLine = "default";//设置默认值
        String inTemp = null;
        //String outTemp = null;
        String turnLine = "\n";
        final String client = "Client:";
        final String server = "Server:";
        //目的端口
        int port = 4000;
        //目的地址
        byte ipAddressTemp[] = {127, 0, 0, 1};
        try{
            //从jTextField把值拿出来
            readLine=jTextField.getText();
            //jTextField值要清空，以便下一次输入
            jTextField.setText("");
            InetAddress ipAddress = null;
            ipAddress = InetAddress.getByAddress(ipAddressTemp);
            //创建指向服务器的地址和端口
            Socket socket = null;
            //开启一个新的socket
            socket = new Socket(ipAddress, port);


            //接受服务器送过来数据的流
            BufferedReader socketIn = null;
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //往服务器端送数据的流
            PrintWriter socketOut = null;
            socketOut = new PrintWriter(socket.getOutputStream());

            //往服务器端送入数据
            socketOut.println(readLine);
            socketOut.flush();

            //服务端返回数据后，socketIn接收返回的数据
            inTemp=socketIn.readLine();
            if(inTemp!=null){
                jTextArea.append(inTemp+"\n");
            }
            //System.out.println("inTemp is "+inTemp);

            socketIn.close();
            socketOut.close();
            socket.close();//socket关闭
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

