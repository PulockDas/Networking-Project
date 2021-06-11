import javax.swing.*;

class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo() {
        anItem = new JMenuItem("Upload");
        add(anItem);

        anItem.addActionListener(Client_Main.uploadAction());
    }


}