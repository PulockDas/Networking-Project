import java.io.DataOutputStream;
import java.io.FileInputStream;

public class Upload_Thread {
    public void suru() {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(Client_Main.fileToSend[0].getAbsolutePath());

            DataOutputStream dataOutputStream = new DataOutputStream(Client_Main.socket.getOutputStream());

            byte[] commandByte = "upload".getBytes();
            dataOutputStream.writeInt(commandByte.length);
            dataOutputStream.write(commandByte);

            String fileName = Client_Main.fileToSend[0].getName();

            byte[] fileNameBytes = fileName.getBytes();
            byte[] fileBytes = new byte[(int) Client_Main.fileToSend[0].length()];
            fileInputStream.read(fileBytes);

            dataOutputStream.writeInt(fileNameBytes.length);

            dataOutputStream.write(fileNameBytes);
            dataOutputStream.writeInt(fileBytes.length);
            dataOutputStream.write(fileBytes);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
