import javax.swing.*;


/**********************************************************************
 * CIS163 Project 1: Count Down Timer
 * Class
 * @author Jordan Hayes
 * @version 1
 **********************************************************************/

public class GEOCDTGUISwing {
    public static void main(String arg[]){

        JMenu fileMenu;
        JMenuItem quitItem;
        JMenuBar menus;

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        menus.add(fileMenu);

        JFrame gui = new JFrame("GEO Count Down Timer");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GEOCDTMainSwing panel = new GEOCDTMainSwing(quitItem);
        gui.getContentPane().add(panel);

        gui.setSize(800,600);
        gui.setJMenuBar(menus);
        gui.pack();
        gui.setVisible(true);
    }



}