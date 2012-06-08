/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.model.messages;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import timr.model.MainModel;
import timr.service.Services;
import timr.ui.MainView;

/**
 *
 * @author Kiro
 */
public abstract class Message {
    public Date date;
    public boolean solved;
    public String _id;
    public String title;
    /**
     * Arranges the data into a JPanel and returns
     * @return 
     */
    public abstract JPanel toPanel();
    public abstract JPanel toPanel(boolean mouseListener);
    
    protected void setMenu(final JPanel p){
        final JPopupMenu pop = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete");
        
        pop.add(delete);

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                solved = true;
                Services.solveNotification(MainModel.getInstance().stud.user, _id);
                
                if (MainModel.getInstance().unsolved){
                    Container parent = p.getParent();
                    parent.remove(p);
                    parent.revalidate();
                    
                }
            }
        });
        
        p.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            private void doPop(MouseEvent e) {
                pop.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
