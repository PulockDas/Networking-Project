import java.io.DataInputStream;
import java.io.IOException;

public class DataRecieve_Thread extends Thread{
    @Override
    public void run() {
        while (true) {

            try {
                DataInputStream dataInputStream = new DataInputStream(Server_Main.socket.getInputStream());
                int commandLen = dataInputStream.readInt();

                byte[] command = new byte[commandLen];
                dataInputStream.readFully(command, 0, commandLen);

                String com = new String(command);

                if (com.equals("upload")) {
                    System.out.println(com);
                    Upload_Maintain upload_maintain = new Upload_Maintain();
                    upload_maintain.maintain();
                } else if (com.equals("fetch")) {
                    Fetch_Maintain fetch_maintain = new Fetch_Maintain();
                    fetch_maintain.maintain();
                } else if (com.equals("download")) {
                    Download_Maintain download_maintain = new Download_Maintain();
                    download_maintain.maintain();
                } else if (com.equals("delete")) {
                    Delete_Maintain delete_maintain = new Delete_Maintain();
                    delete_maintain.maintain();
                } else if (com.equals("closed")) {
                    Server_Main.jlTitle.setText("Disconnected");
                    break;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
