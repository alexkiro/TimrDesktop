/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import timr.tray.TraySupport;

/**
 * Entry point of the application.
 * @author kiro
 */
public class TimrMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        LoginView v = new LoginView();
        v.setVisible(true);
//      TraySupport.getInstance().show();
//      MainView.getInstance().setVisible(true);

    }
}
