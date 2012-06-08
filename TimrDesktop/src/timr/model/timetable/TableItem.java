package timr.model.timetable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.*;
import timr.model.MainModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import timr.service.Services;
import timr.timrutil.Utils;
import timr.ui.MainView;
import timr.ui.TimrError;
import timr.ui.windows.MouseInfo;
import timr.xml.XmlParser;

/**
 * A timetable item.
 * @author Kiro
 */
public class TableItem {

    public String startTime;
    public String endTime;
    public String className;
    public String _id;
    public String teacherName;
    public String roomNumber;
    public int classType;
    public int frequency;
    public int optionalPackage;
    public Group group;

    @Override
    public String toString() {
        return (startTime + " " + endTime + " " + className + " " + teacherName
                + " " + roomNumber + " " + className + " _id: " + _id + " " + frequency + " "
                + optionalPackage + " " + group);
    }

    /**
     * Returns the data about the timetable item in a panel form.
     * Also ads the necessary mouse and action listeners.
     * @return 
     */
    public JPanel toPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);

        JLabel hourLabel = new JLabel(startTime + " - " + endTime);
        hourLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JLabel subjectLabel = new JLabel(className);
        subjectLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));

        JLabel placeLabel = new JLabel(String.valueOf(roomNumber));
        placeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        Image img;
        if (classType == 0) {
            img = Utils.createImage("../icons/course.png", "Course");
        } else if (classType == 1) {
            img = Utils.createImage("../icons/laboratory.png", "Laboratory");
        } else {
            img = Utils.createImage("../icons/seminar.png", "Seminar");
        }

        JButton jButton = new JButton(new ImageIcon(img));
        jButton.setBorderPainted(false);
        jButton.setBorder(null);
        p.add(jButton, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.add(subjectLabel, BorderLayout.CENTER);
        center.add(placeLabel, BorderLayout.SOUTH);
        center.add(hourLabel, BorderLayout.NORTH);

        p.add(center, BorderLayout.CENTER);

        p.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);

        String[] freq = {"Every Week", "Odd Weeks", "Even Weeks"};

        String info = "<b>Prof:</b><i> " + teacherName
                + "</i>\n<b>" + "Room:</b><i> " + roomNumber
                + "</i>\n<b>" + "Group:</b><i> " + group.toString()
                + "</i>\n" + "<b>Freq:</b><i> " + freq[frequency]
                + "</i>\n" + "<b>Site:</b><i> " + MainModel.getInstance().stud.subjects.get(_id) + "</i>";
        if (optionalPackage != -1) {
            info += "\n<b>" + "Package:</b><i> " + optionalPackage + "</i>";
        }

        p.addMouseListener(new MouseInfo(info));

        jButton.addActionListener(goToSiteAction());

        final JPopupMenu pop = new JPopupMenu();
        JMenuItem goToSite = new JMenuItem("Go to site");
        JMenuItem updateSite = new JMenuItem("Update Site");
        pop.add(goToSite);
        pop.add(updateSite);

        goToSite.addActionListener(goToSiteAction());
        updateSite.addActionListener(updateSiteAction());

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

        return p;
    }

    private ActionListener updateSiteAction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (_id != null && (!_id.isEmpty())) {
                    String site = MainModel.getInstance().stud.subjects.get(_id);
                    if (site != null && (!site.isEmpty())) {
                        String result = JOptionPane.showInputDialog(MainView.getInstance(),
                                "<html>Change Site for Subject:<br/> " + className, site);
                        if (result != null && (!result.isEmpty())) {
                            MainModel.getInstance().stud.subjects.put(_id, result);
                            Services.updateSite(MainModel.getInstance().stud.user, site, result);
                        }
                    } else {
                        String result = JOptionPane.showInputDialog(MainView.getInstance(),
                                "<html>Add Site to Subject<br/>" + className,"http://");
                        if (result != null && (!result.isEmpty())) {
                            MainModel.getInstance().stud.subjects.put(_id, result);
                            Services.insertSite(MainModel.getInstance().stud.user, result, _id);
                        }
                    }
                } else {
                    System.err.println("No _id for subject: " + className);
                }
            }
        };
    }

    private ActionListener goToSiteAction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (_id != null && (!_id.isEmpty())) {
                    String site = MainModel.getInstance().stud.subjects.get(_id);
                    if (site != null && (!site.isEmpty())) {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(site));
                        } catch (Exception ex) {
                            TimrError.show(ex.getMessage());
                        }
                    } else {
                        String result = JOptionPane.showInputDialog(MainView.getInstance(),
                                "<html>Add Site to Subject<br/>" + className,"http://");
                        if (result != null && (!result.isEmpty())) {
                            MainModel.getInstance().stud.subjects.put(_id, result);
                            Services.insertSite(MainModel.getInstance().stud.user, result, _id);
                        }
                    }
                } else {
                    System.err.println("No _id for subject: " + className);
                }
            }
        };
    }

    public TableItem(Node n) {
        Element e = (Element) n;
        _id = e.getAttribute("id");
        startTime = XmlParser.retreive(e, "startTime");
        endTime = XmlParser.retreive(e, "endTime");
        className = XmlParser.retreive(e, "className");
        teacherName = XmlParser.retreive(e, "teacherName");
        roomNumber = XmlParser.retreive(e, "roomNumber");
        classType = Integer.parseInt(XmlParser.retreive(e, "classType"));
        frequency = Integer.parseInt(XmlParser.retreive(e, "frequency"));
        optionalPackage = Integer.parseInt(XmlParser.retreive(e, "optionalPackage"));
        group = new Group((Element) e.getElementsByTagName("group").item(0));

    }

    public TableItem() {
    }

    public static TableItem creatTableItem(Node node) {
        TableItem ti = new TableItem();

        return ti;
    }
}
