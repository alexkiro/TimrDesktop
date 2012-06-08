/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kiro
 */
public class TimrError {
    public static void show(String message){
        System.out.println(message);
        JFrame f = new JFrame();
        f.setVisible(true);
        JOptionPane.showMessageDialog(f, 
                    message,"Error!",JOptionPane.ERROR_MESSAGE);
        f.setVisible(false);
    }
}
