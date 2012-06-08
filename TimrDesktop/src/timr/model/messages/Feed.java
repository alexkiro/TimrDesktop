
package timr.model.messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import timr.timrutil.Utils;
import timr.ui.windows.MouseInfo;

/**
 * Class that holds information about a feed sent by a professor.
 * @author Kiro
 */
public class Feed extends Message {

    public String sender;
    public String message;

    @Override
    public JPanel toPanel(boolean mouseListener) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JLabel fromLabel = new JLabel(title);
        fromLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        
        JLabel dateLabel = new JLabel(Utils.compareDate(date));
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));

        Image img = Utils.createImage("../icons/feed_notification.png", "Course");

        JButton jButton = new JButton(new ImageIcon(img));
        jButton.setBorderPainted(false);
        jButton.setBorder(null);
        p.add(jButton, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.add(dateLabel, BorderLayout.CENTER);
        center.add(fromLabel, BorderLayout.NORTH);

        p.add(center, BorderLayout.CENTER);

        p.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);

        if (mouseListener) {

            p.addMouseListener(new MouseInfo("<html><b>Sender:</b> <i>" + sender + "</i><br>"
                    + Utils.wrapMessage(message, 40)));
        }

        this.setMenu(p);

        return p;
    }

    @Override
    public JPanel toPanel() {
        return toPanel(true);
    }
}
