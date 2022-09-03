import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
public class clientone {


        private int id;
        private String name;
    public clientone(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public void start() {
            try {
                Socket socketone = new Socket(InetAddress.getLocalHost(),1234);
                BufferedReader is = new BufferedReader(new InputStreamReader(socketone.getInputStream()));
                PrintWriter os = new PrintWriter(socketone.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("-----------客户端-------");
                os.println(name);
                while (true) {
                    String str = in.readLine();
                    os.println("From "+id+" "+name+": "+str);
                    os.flush();
                    System.out.println(is.readLine());
                    if (str.contains("Exit")) {
                        break;
                    }
                }
                os.close();
                is.close();
                socketone.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
