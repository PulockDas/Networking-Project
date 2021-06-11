import javax.swing.*;

class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    JMenuItem anItem2;

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
            anItem2 = new JMenuItem("Delete");

            add(anItem); add(anItem2);
            anItem.addActionListener(Client_Main.downloadAction( operation ));

            anItem2.addActionListener(Client_Main.deleteAction( operation ));
        }

    }


}