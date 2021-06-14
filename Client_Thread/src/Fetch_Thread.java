import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Fetch_Thread{
    public static JPanel mother;
    public static JLabel newFile;

    public Fetch_Thread () {
        Client_Main.y = 370;
    }

    public void maintain() {
        try {

            Client_Main.allServerFiles.removeAll();
            Client_Main.jFrame.revalidate();
            Client_Main.jFrame.repaint();

            DataOutputStream dataOutputStream = null;
            dataOutputStream = new DataOutputStream(Client_Main.socket.getOutputStream());

            byte[] commandByte = "fetch".getBytes();
            dataOutputStream.writeInt(commandByte.length);
            dataOutputStream.write(commandByte);

            DataInputStream dataInputStream = new DataInputStream(Client_Main.socket.getInputStream());
            int fileCount = dataInputStream.readInt();

            for(int i=0; i<fileCount; i++){
                int fileNameLength = dataInputStream.readInt();
                byte[] fileNameBytes = new byte[fileNameLength];

                dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);

                String fileName = new String(fileNameBytes);

                mother = new JPanel();
                mother.setBounds(300, Client_Main.y, 80, 25);

                String s = "Download/Upload@"+String.valueOf(i);
                System.out.println(s);

                newFile = new JLabel(fileName);
                newFile.setBounds(300, Client_Main.y, 80, 25);
                Client_Main.y += 30;
                System.out.println(Client_Main.y);

                newFile.addMouseListener(new PopClickListener( s, fileName ));

                mother.add(newFile);

                Client_Main.allServerFiles.add(mother);

                Client_Main.jFrame.validate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
