import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopClickListener implements MouseListener {

    String operation = null;
    String fileName = null;

    public PopClickListener(String s) {
        operation = s;
    }

    public PopClickListener(String s, String file) {
        operation = s;
        fileName = file;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private void doPop(MouseEvent e) {
        PopUpDemo menu = new PopUpDemo( operation, fileName );
        menu.show(e.getComponent(), e.getX(), e.getY());
    }



}
