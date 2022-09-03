import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Client extends JFrame {
    public static void main(String[] args){
        new Client();
    }

    //构造方法
    public Client(){
        initFrame();
    }

    //初始化图形化界面
    public void initFrame(){
        this.setLayout(new BorderLayout());
        this.setTitle("Client");
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(600,300,500,200);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new FlowLayout());

        JTextField jTextField_1=new JTextField(31);
        JButton jButton=new JButton("发送");

        JTextArea jTextArea=new JTextArea();
        //jTextArea.setPreferredSize(new Dimension(460,150));
        jTextArea.setEditable(false);//设置为不可编辑
        jTextArea.setLineWrap(true);//文字比控件的宽度还长时会自动换行
        jTextArea.setWrapStyleWord(true);//在单词边界换行，而不是粗暴的直接在字符边界换行

        //为发送按钮添加点击事件
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jTextField_1.getText().equals("")){
                    Thread thread=new ClientThread(jTextField_1,jTextArea);//创建新的客户端socket网络通信线程
                    thread.start();//客户端socket网络通信线程启动
                }
            }
        });

        //为JTextArea添加滚动条
        JScrollPane jScrollPane=new JScrollPane(jTextArea);//jTextArea就不能在设置边界大小了
        jScrollPane.setPreferredSize(new Dimension(460,100));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel.add(jTextField_1);
        jPanel.add(jButton);
        jPanel.add(jScrollPane);

        this.add(jPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }
}