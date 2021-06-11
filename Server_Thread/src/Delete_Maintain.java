import javax.swing.*;
import java.io.DataInputStream;

public class Delete_Maintain {
    public void maintain() {
        try {
            DataInputStream dataInputStream = new DataInputStream(Server_Main.socket.getInputStream());

            int fileId = dataInputStream.readInt();

            if(fileId < Server_Main.myFiles.size()) {
                Server_Main.myFiles.remove(fileId);

                Server_Main.jPanel.removeAll();
                Server_Main.jFrame.revalidate();
                Server_Main.jFrame.repaint();

                for(MyFile myFile: Server_Main.myFiles){
                    Upload_Maintain.showServerFile( myFile.getName() );
                }
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
