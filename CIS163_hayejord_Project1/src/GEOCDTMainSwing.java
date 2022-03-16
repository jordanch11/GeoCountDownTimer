import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************************************************
 * CIS163 Project 1: Count Down Timer
 * Class GEOMainSwing, extends Jpanel
 * @author Jordan Hayes
 * @version 1
 **********************************************************************/

public class GEOCDTMainSwing extends JPanel {

    private JMenuItem quitItem;

    public GEOCDTMainSwing(JMenuItem quitItem) {
        this.quitItem = quitItem;

        JPanel panel = new JPanel();
        panel.add(new GEOCDTPanelSwing());
        panel.add(new GEOCDTPanelSwing());
        panel.add(new GEOCDTPanelSwing());

        add(panel);

        quitItem.addActionListener(new Mylistener());
    }

    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == quitItem){
                System.exit(1);
            }

        }

    }
}