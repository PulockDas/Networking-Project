import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;

public class Upload_Maintain {
    public void maintain() {

            try {

                DataInputStream dataInputStream = new DataInputStream(Server_Main.socket.getInputStream());

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

                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));

                        jpFileRow.setName((String.valueOf(Server_Main.fileId)));
                        jpFileRow.add(jlFileName);

                        System.out.println(fileName);

                        Server_Main.jPanel.add(jpFileRow);
                        Server_Main.jFrame.validate();

                        Server_Main.myFiles.add(new MyFile(Server_Main.fileId, fileName, fileContentBytes, getFileExtension(fileName)));

                        Server_Main.fileId++;

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }
}
