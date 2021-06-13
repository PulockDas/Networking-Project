import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

public class Fetch_Maintain {

    public void maintain() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(Server_Main.socket.getOutputStream());

            dataOutputStream.writeInt(Server_Main.myFiles.size());

            for(MyFile myFile: Server_Main.myFiles) {
                String fileName = myFile.getName();
                byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);

                dataOutputStream.writeInt(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);
//                dataOutputStream.writeInt(myFile.getId());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
