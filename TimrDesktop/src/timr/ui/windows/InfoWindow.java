/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui.windows;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JWindow;
import timr.ui.MainView;

/**
 *
 * @author kiro
 */
public class InfoWindow extends JWindow implements Runnable {

    public InfoWindow(String text, int y) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel label = new JLabel("<html>" + text.replaceAll("\n", "<br>"));
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        this.add(label);
        this.pack();
        Dimension size = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point location = MainView.getInstance().getLocation();

        int posX;
        int posY;
        if (location.x - size.width <= 0) {
            posX = location.x + size.width;
        } else {
            posX = location.x - size.width;
        }
        if (y - 50 + size.height >= screenSize.height) {
            posY = screenSize.height - size.height - 50;
        }else{
            posY = y - 50;
        }
        this.setLocation(posX, posY);

    }

    @Override
    public void run() {
        try {

            this.setVisible(true);
            Thread.sleep(4000);
            this.setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(InfoWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
