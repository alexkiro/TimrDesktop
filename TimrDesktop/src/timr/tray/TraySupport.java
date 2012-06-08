package timr.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import timr.model.messages.Feed;
import timr.ui.MainView;

public class TraySupport {

    private TraySupport() {
    }
    private static TraySupport instance = null;

    public static TraySupport getInstance() {
        if (instance == null) {
            return instance = new TraySupport();
        } else {
            return instance;
        }
    }
    private TrayIcon trayIcon;
    private SystemTray tray;
    private JPopupMenu jpop;

    public void show() {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }

    public void removeTray() {
        tray.remove(trayIcon);
    }

    private void createAndShowGUI() {
        //Check the SystemTray support

        jpop = new JPopupMenu();
        JMenuItem jmiShow = new JMenuItem("Show");
        JMenuItem jmiLogout = new JMenuItem("Logout");
        JMenuItem jmiQuit = new JMenuItem("Quit");
        JMenuItem jtest = new JMenuItem("Test Notifications");

        jpop.add(jmiShow);
        jpop.add(new JPopupMenu.Separator());
        jpop.add(jmiLogout);
        jpop.add(new JPopupMenu.Separator());
        jpop.add(jmiQuit);
        
        jpop.add(new JPopupMenu.Separator());
        jpop.add(jtest);
        
        jtest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Feed feed = new Feed();
                feed.date = Calendar.getInstance().getTime();
                feed.sender = "busaco";
                feed.solved = false;
                feed.title = "busaco has posted a message !!!";
                feed.message = "Asta e doar un test.... :( ... ";
                
                
                Notification n = new Notification(feed);
                new Thread(n).start();
            }
        });
        

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        trayIcon = new TrayIcon(createImage("../icons/timr.png", "tray icon"));
        tray = SystemTray.getSystemTray();




        trayIcon.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    jpop.setLocation(e.getX(), e.getY());
                    jpop.setInvoker(jpop);
                    jpop.setVisible(true);
                } else {
                    if (!MainView.getInstance().isVisible()) {
                        MainView.getInstance().setVisible(true);
                    }

                }
                e.consume();
            }

            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    jpop.setLocation(e.getX(), e.getY());
                    jpop.setInvoker(jpop);
                    jpop.setVisible(true);
                } else {
                    if (!MainView.getInstance().isVisible()) {
                        MainView.getInstance().setVisible(true);
                    }
                }
                e.consume();
            }
        });


        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        ActionListener act = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!MainView.getInstance().isVisible()) {
                    MainView.getInstance().setVisible(true);
                } else {
                    MainView.getInstance().setVisible(false);
                }

            }
        };

        jmiShow.addActionListener(act);
        //trayIcon.addActionListener(act);

        jmiLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Notification not = new Notification("Message from Vlad Radulescu", 0.9f, "../icons/feed_notification.png");
                new Thread(not).start();
            }
        });

        jmiQuit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.getInstance().exitApplication();
            }
        });


    }

    //Obtain the image URL
    private static Image createImage(String path, String description) {
        URL imageURL = TraySupport.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
