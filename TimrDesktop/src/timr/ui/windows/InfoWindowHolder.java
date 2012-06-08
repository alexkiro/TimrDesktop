/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui.windows;

/**
 * A InfoWindows Holder, that makes sure that there is only one InfoWindow at one time.
 * @author Kiro
 */
public class InfoWindowHolder {
    private static InfoWindow instance = null;
    public static void createWindow(String str, int y){
        if (instance!=null){
            instance.setVisible(false);
            instance.dispose();
        }
        instance = new InfoWindow(str, y);
        instance.setVisible(true);
    }
    public static void dispose(){
       instance.setVisible(false);
       instance.dispose();
    }
}
