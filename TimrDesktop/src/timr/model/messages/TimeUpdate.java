/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.model.messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import timr.timrutil.Utils;
import timr.ui.windows.MouseInfo;

/**
 *
 * @author Kiro
 */
public class TimeUpdate extends Message {

    public String type;
    public String day;
    public String classType;
    public String className;
    public String start;
    public String end;

    @Override
    public JPanel toPanel() {
        return toPanel(true);
    }

    @Override
    public JPanel toPanel(boolean mouseListener) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);


        JLabel subjectLabel = new JLabel();
        subjectLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JLabel dateLabel = new JLabel(Utils.compareDate(date));
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));

        Image img = Utils.createImage("../icons/time_notification.png", "Course");

        JButton jButton = new JButton(new ImageIcon(img));
        jButton.setBorderPainted(false);
        jButton.setBorder(null);
        p.add(jButton, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.add(dateLabel, BorderLayout.CENTER);
        center.add(subjectLabel, BorderLayout.NORTH);

        p.add(center, BorderLayout.CENTER);

        p.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);

        subjectLabel.setText(className);
        if (mouseListener) {
            p.addMouseListener(new MouseInfo("<html><i>" + type + "</i><br>"
                    + "<b>Subject: </b> <i>" + className + "</i><br>"
                    + "<b>Day: </b> <i>" + day + "</i><br>"
                    + "<b>Type: </b> <i>" + classType + "</i><br>"
                    + "<b>Start: </b> <i>" + start + "</i><br>"
                    + "<b>End: </b> <i>" + end + "</i><br>"));
        }

        this.setMenu(p);

        return p;
    }
}
