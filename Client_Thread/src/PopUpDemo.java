import javax.swing.*;

class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;

    String operation;

    public PopUpDemo( String op ) {

        operation = op;

        if(operation.equals("Upload")){
            anItem = new JMenuItem(operation);
            add(anItem);
            anItem.addActionListener(Client_Main.uploadAction());
        }
        else{
            anItem = new JMenuItem("Download");
            add(anItem);
            anItem.addActionListener(Client_Main.downloadAction( operation ));
        }

    }


}