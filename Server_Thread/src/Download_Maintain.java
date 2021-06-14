import java.io.*;

public class Download_Maintain {
    public void maintain () {
        try {
            DataInputStream dataInputStream = new DataInputStream(Server_Main.socket.getInputStream());

            int fileId = dataInputStream.readInt();

            if(Server_Main.myFiles.size() > fileId){

                MyFile myFile = Server_Main.myFiles.get(fileId);
                DataOutputStream dataOutputStream = new DataOutputStream(Server_Main.socket.getOutputStream());
                dataOutputStream.writeInt(myFile.getData().length);
                dataOutputStream.write(myFile.getData());

                dataOutputStream.writeInt(myFile.getFileExtension().getBytes().length);
                dataOutputStream.write(myFile.getFileExtension().getBytes());

            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
