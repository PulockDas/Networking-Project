import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {

    private static String serverName = "";
    private static int portNum;
    private static JFrame jFrame;
    private static JLabel server;
    private static JLabel port;
    private static JTextField s;
    private static JTextField p;
    private static JButton connect;
    private static File[] fileToSend =  new File[1];
    private static String fileName = "";
    private static int y = 370;
    private static JLabel uploadFiles;
    private static Socket socket;
    private static JPanel serverfiles;

    public static void main(String[] args) {

        //Setting the GUI for client to use

        //Here is the window
        jFrame = new JFrame("Client Interface");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Here is the window


        //Here is the scrollable Panel
        JPanel host = new JPanel();
        host.setLayout(null);
        JScrollPane jScrollPane = new JScrollPane(host);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Here is the scrollable Panel


        //For taking Host Information Panel
        server = new JLabel("Server");
        server.setBounds(10,20, 165,25);
        s = new JTextField("localhost", 20);
        s.setBounds(100, 20, 165, 25);

        host.add(server);
        host.add(s);
        //For taking Host Information Panel

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

                System.out.println(serverName);
                System.out.println(portNum);
            }
        });


        // Here is the See Files Button

        JLabel l = new JLabel("Choose a File");
        l.setBounds(10, 140, 200, 25);

        JButton chooseFile = new JButton("Open File");
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
        serverfiles.setBounds(0, 290, 450, 200);

        JLabel seeServerFiles = new JLabel("Server Files");
        seeServerFiles.setBounds(10, 350, 80, 25);

        serverfiles.add(seeServerFiles);
        host.add(serverfiles);


        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file to send.");

                if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {

                    // Get the selected file.
                    fileToSend[0] = jFileChooser.getSelectedFile();

                    uploadFiles.setText("The file can be uploaded");
                    jlFile.setText(fileToSend[0].getName());

                }
            }
        });

        jlFile.addMouseListener(new PopClickListener());


        jFrame.setVisible(true);


    }

    public static ActionListener uploadAction() {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileToSend[0] != null) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());

                        // Create a socket connection to connect with the server.
                        socket = new Socket( serverName, portNum );

                        // Create an output stream to write to write to the server over the socket connection.
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        // Get the name of the file you want to send and store it in filename.
                        String fileName = fileToSend[0].getName();
                        // Convert the name of the file into an array of bytes to be sent to the server.
                        byte[] fileNameBytes = fileName.getBytes();
                        // Create a byte array the size of the file so don't send too little or too much data to the server.
                        byte[] fileBytes = new byte[(int)fileToSend[0].length()];
                        // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                        fileInputStream.read(fileBytes);

                        // Send the length of the name of the file so server knows when to stop reading.
                        dataOutputStream.writeInt(fileNameBytes.length);
                        // Send the file name.
                        dataOutputStream.write(fileNameBytes);
                        // Send the length of the byte array so the server knows when to stop reading.
                        dataOutputStream.writeInt(fileBytes.length);
                        // Send the actual file.
                        dataOutputStream.write(fileBytes);

                        System.out.println("upload deo");

                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        };
    }
}
