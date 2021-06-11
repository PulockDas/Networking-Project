import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_Main {

    private static String serverName = "";
    private static int portNum;
    public static JFrame jFrame;
    private static JLabel server;
    private static JLabel port;
    private static JTextField s;
    private static JTextField p;
    private static JButton connect;
    public static File[] fileToSend = new File[1];
    private static String fileName = "";
    public static int y = 370;
    private static JLabel uploadFiles;
    public static Socket socket;
    public static JPanel serverfiles;
    private static JButton chooseFile;
    public static JPanel allServerFiles;

    public static void main(String[] args) {

        jFrame = new JFrame("Client Interface");
        jFrame.setSize(650, 650);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel host = new JPanel();
        host.setLayout(null);
        JScrollPane jScrollPane = new JScrollPane(host);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        server = new JLabel("Server");
        server.setBounds(10, 20, 165, 25);
        s = new JTextField("localhost", 20);
        s.setBounds(100, 20, 165, 25);

        host.add(server);
        host.add(s);

        jFrame.add(jScrollPane);

        port = new JLabel("Port");
        port.setBounds(10, 50, 80, 25);
        host.add(port);

        p = new JTextField(20);
        p.setBounds(100, 50, 165, 25);
        host.add(p);

        connect = new JButton("Connect");
        connect.setBounds(10, 80, 100, 25);
        host.add(connect);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                serverName = s.getText();
                portNum = Integer.parseInt(p.getText());

                try {
                    socket = new Socket(serverName, portNum);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(serverName);
                System.out.println(portNum);
            }
        });



        JLabel l = new JLabel("Choose a File");
        l.setBounds(10, 140, 200, 25);

        chooseFile = new JButton("Open File");
        chooseFile.setBounds(10, 170, 120, 25);

        host.add(l);
        host.add(chooseFile);

        uploadFiles = new JLabel("Please choose a file to upload");
        uploadFiles.setBounds(10, 230, 220, 25);

        host.add(uploadFiles);

        JLabel jlFile = new JLabel();
        jlFile.setBounds(10, 260, 220, 25);

        host.add(jlFile);

        //See Server Files
        serverfiles = new JPanel();
        serverfiles.setBounds(0, 290, 450, 100);

        JButton seeServerFiles = new JButton("Server Files");
        seeServerFiles.setBounds(300, 320, 80, 25);

        serverfiles.add(seeServerFiles);
        host.add(serverfiles);

        allServerFiles = new JPanel();
        allServerFiles.setBounds(0, 350, 450, 200);

        host.add(allServerFiles);

        jFrame.setVisible(true);

        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file to send.");

                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    // Get the selected file.
                    fileToSend[0] = jFileChooser.getSelectedFile();

                    uploadFiles.setText("The file can be uploaded");
                    jlFile.setText(fileToSend[0].getName());

                }
            }
        });

        jlFile.addMouseListener(new PopClickListener());

        seeServerFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Fetch_Thread fetch_thread = new Fetch_Thread();
                fetch_thread.maintain();
            }
        });
    }

    public static ActionListener uploadAction() {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileToSend[0] != null) {

                    Upload_Thread upload_thread = new Upload_Thread();
                    upload_thread.suru();
                }
            }
        };
    }
}
