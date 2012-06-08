/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.tray;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.event.MouseInputListener;
import timr.model.messages.Message;
import timr.ui.MainView;

/**
 *
 * @author kiro
 */
public class Notification extends JWindow implements Runnable, MouseInputListener {

    private JLabel label = new JLabel();
    private Font font = new Font("Sans", Font.ITALIC, 16);
    private String text = "Default Notification";
    private int sleep = 3000;
    private Color bg = new Color(80, 130, 245);
    private Color border = bg;
    private Color fg = Color.BLACK;
    private boolean dispose = false;
    private Image img = null;

    public Notification(String s) {
        this(s, 0.9f, null);
    }

    public Notification(String s, float opacity) {
        this(s, opacity, null);
    }
    
    public Notification(Message msg){
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.add(msg.toPanel(false));
        
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();

        RoundRectangle2D rr2 = new RoundRectangle2D.Float(0, 0, size.width, size.height, 20, 20);
        this.setShape(rr2);
        //his.setOpacity(opacity);

        this.setLocation(screenSize.width - size.width - 50, 50);
    }

    public Notification(String s, float opacity, String imgString) {
        super();

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel pan = new JPanel(new BorderLayout());
        addMouseListener(this);

        //pan.setBackground(bg);
        //this.getContentPane().setBackground(border);
        this.add(pan);
        pan.add(label, BorderLayout.CENTER);
        text = s;
        label.setText(text);
        label.setForeground(fg);
        label.setFont(font);

        if (imgString != null) {
            //img = Utils.createImage(imgString, "None");  //TODO: fix image
        }

        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();

        RoundRectangle2D rr2 = new RoundRectangle2D.Float(0, 0, size.width, size.height, 20, 20);
        this.setShape(rr2);
        this.setOpacity(opacity);

        this.setLocation(screenSize.width - size.width - 50, 50);

       

    }
    
    private void setUp(){
        
    }

    @Override
    public void run() {
        try {
            this.setVisible(true);
            Thread.sleep(sleep);
            this.setVisible(false);
            this.dispose();


        } catch (InterruptedException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (!MainView.getInstance().isVisible()){
            MainView.getInstance().setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
}
