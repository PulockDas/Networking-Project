import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Fetch_Thread{
    public void maintain() {
        try {

            Client_Main.allServerFiles.removeAll();

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

                JPanel mother = new JPanel();
                mother.setBounds(0, Client_Main.y, 450, 30);
                JLabel newFile = new JLabel(fileName);
                newFile.setBounds(300, Client_Main.y, 80, 25); Client_Main.y+= 30;
                System.out.println(Client_Main.y);

                mother.add(newFile);
                Client_Main.allServerFiles.add(mother);

                Client_Main.jFrame.validate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
