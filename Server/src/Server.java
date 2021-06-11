import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    // Array list to hold information about the files received.
    static ArrayList<MyFile> myFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // Used to track the file (jpanel that has the file name in it on a label).
        int fileId = 0;

        //Create Server Window
        JFrame jFrame = new JFrame("Server");
        jFrame.setSize(400, 400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create Server Window

        //Create a Panel
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Create a Panel

        JLabel jlTitle = new JLabel("File Server");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add everything to the main GUI.
        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        // Make the GUI show up.
        jFrame.setVisible(true);

        // Create a server socket that the server will be listening with.
        ServerSocket serverSocket = new ServerSocket(1234);

        // This while loop will run forever so the server will never stop unless the application is closed.
        while (true) {
            try {
                Socket socket = serverSocket.accept();

//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//                int l = myFiles.size();
//                dataOutputStream.writeInt(l);
//
//                for( MyFile myFile: myFiles ){
//                    String fileName = myFile.getName();
//                    byte[] fileNameBytes = fileName.getBytes();
//
//                    dataOutputStream.writeInt(fileNameBytes.length);
//                    dataOutputStream.write(fileNameBytes);
//                }
//
//                socket.close();
//                socket = null;
//
//                socket = serverSocket.accept();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                // Read the size of the file name so know when to stop reading.
                int fileNameLength = dataInputStream.readInt();

                // If the file exists
                if (fileNameLength > 0) {

                    byte[] fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);

                    String fileName = new String(fileNameBytes);
                    int fileContentLength = dataInputStream.readInt();

                    if (fileContentLength > 0) {

                        byte[] fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);

                        System.out.println(fileName);

                        // Panel to hold the picture and file name.
                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));

                        // Set the name to be the fileId so you can get the correct file from the panel.
                        jpFileRow.setName((String.valueOf(fileId)));
                        // Add everything.
                        jpFileRow.add(jlFileName);
                        jPanel.add(jpFileRow);
                        jFrame.validate();

                        // Add the new file to the array list which holds all our data.
                        myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
                        // Increment the fileId for the next file to be received.

                        fileId++;

                    }
                }
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }


    }

    public static String getFileExtension(String fileName) {
        // Get the file type by using the last occurence of . (for example aboutMe.txt returns txt).
        // Will have issues with files like myFile.tar.gz.
        int i = fileName.lastIndexOf('.');
        // If there is an extension.
        if (i > 0) {
            // Set the extension to the extension of the filename.
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }
}
