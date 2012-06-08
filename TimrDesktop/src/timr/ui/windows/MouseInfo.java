/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui.windows;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 * Mouse listener for displaying InfoWindows on hover.
 * @author kiro
 */
public class MouseInfo implements MouseInputListener {

    private String info;

    public MouseInfo(String info) {        
        this.info = info;           
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        InfoWindowHolder.createWindow(info, e.getYOnScreen());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        InfoWindowHolder.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
