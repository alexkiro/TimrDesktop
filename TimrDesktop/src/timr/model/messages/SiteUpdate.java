/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.model.messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;
import timr.timrutil.Utils;
import timr.ui.TimrError;
import timr.ui.windows.MouseInfo;

/**
 * Class that holds information about a monitored site update
 * @author Kiro
 */
public class SiteUpdate extends Message{
    public String site;
 

    @Override
    public JPanel toPanel() {
        return toPanel(true);
    }

    @Override
    public JPanel toPanel(boolean mouseListener) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JLabel siteLabel = new JLabel(title);
        siteLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JLabel dateLabel = new JLabel(Utils.compareDate(date)); 
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));

        Image img = Utils.createImage("../icons/site_notification.png", "Course");

        JButton jButton = new JButton(new ImageIcon(img));
        jButton.setBorderPainted(false);
        jButton.setBorder(null);
        p.add(jButton, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.add(dateLabel, BorderLayout.CENTER);
        center.add(siteLabel, BorderLayout.NORTH);

        p.add(center, BorderLayout.CENTER);

        p.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);

        if (mouseListener){
            p.addMouseListener(new MouseInfo("<html><b>Site changed:</b><br> <i><u>" + site+ "</u></i>"));
        }
        
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI(site));
                } catch (Exception ex) {
                    TimrError.show(ex.getMessage());                            
                }
            }
        });
        
        this.setMenu(p);
        
        return p;
    }
    
}
