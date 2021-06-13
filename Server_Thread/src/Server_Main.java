import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_Main {
    public static ArrayList<MyFile> myFiles = new ArrayList<>();
    public static JFrame jFrame;
    public static Socket socket;
    public static JPanel jPanel;
    public static int fileId;
    public static JLabel jlTitle;

    public static void main(String[] args) throws Exception {
        fileId = 0;

        jFrame = new JFrame("Server");
        jFrame.setSize(650, 650);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jlTitle = new JLabel("Welcome");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        // Make the GUI show up.
        jFrame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            try {
                socket = serverSocket.accept();
                jlTitle.setText("Connected");

                DataRecieve_Thread dataRecieve_thread = new DataRecieve_Thread();
                dataRecieve_thread.start();

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
    }

}
