/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import timr.model.MainModel;
import timr.model.user.UserType;
import timr.tray.TraySupport;

/**
 *
 * @author kiro
 */
public class MainView extends JFrame {

    private TimePanel timePanel;
    private SendPanel sendPanel;
    private JProgressBar progressBar;
    private boolean watch;

    private MainView() {

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        progressBar = new JProgressBar(0, 0);
        progressBar.setStringPainted(true);
        progressBar.setString("");
        progressBar.setIndeterminate(true);
        this.add(progressBar);
        setMenu(UserType.Student);

        this.pack();
        setToPosition();

        initStudentView("Adrian2", "Adrian2");
        //initProfView("busaco", "busaco");
    }

    public void startNotificationWatch() {
        watch = true;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (watch) {
                    System.out.println("update wait");
                    try {
                        Thread.sleep(MainModel.getInstance().updateDelay * 1000);
                        System.out.println("update start");
                        if (MainModel.getInstance().updateAndCompare()) {
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    
                                    timePanel.updateNotifications();
                                }
                            });
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();

    }

    public void initProfView(final String user, final String pass) {
        sendPanel = new SendPanel();

        Thread userInfoThread = new Thread(new Runnable() {

            @Override
            public void run() {
                MainModel.getInstance().loadProfInfo(user, pass);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        sendPanel.updatePanel();
                        MainView.getInstance().remove(progressBar);
                        MainView.getInstance().add(sendPanel);
                        MainView.getInstance().pack();
                        MainView.getInstance().setToPosition();
                    }
                });

            }
        });

        userInfoThread.start();

    }

    public void updateNotifications() {
        Thread notificationsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                MainModel.getInstance().loadUserNotificatons();
                startNotificationWatch();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        timePanel.updateNotifications();
                    }
                });

            }
        });
        notificationsThread.start();
    }

    public boolean initStudentView(final String user, final String pass) {

        timePanel = new TimePanel();

        Thread userInfoThread = new Thread(new Runnable() {

            @Override
            public void run() {
                MainModel.getInstance().loadStudInfo(user, pass);
                updateNotifications();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        timePanel.updateTimetable();
                        MainView.getInstance().remove(progressBar);
                        MainView.getInstance().add(timePanel);
                        MainView.getInstance().pack();
                        MainView.getInstance().setToPosition();
                    }
                });

            }
        });

        userInfoThread.start();

        return true;
    }

    public void setToPosition() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        this.setLocation(screenSize.width - size.width - 50, 50);
    }

    private void setMenu(UserType type) {


        JMenuBar mb = new JMenuBar();

        JMenu mAccount = new JMenu("Account");
        JMenu mAbout = new JMenu("About");

        JMenuItem miLogout = new JMenuItem("Logout");
        JMenuItem miQuit = new JMenuItem("Quit");
        mAccount.add(miLogout);
        mAccount.add(miQuit);
        mb.add(mAccount);
        
        if (type == UserType.Student){        
            JMenu mView = new JMenu("View");
            JMenuItem miCourse = new JCheckBoxMenuItem("Courses");
            JMenuItem miLaboratory = new JCheckBoxMenuItem("Laboratories");
            JMenuItem miSeminars = new JCheckBoxMenuItem("Seminars");
            mView.add(miCourse);
            mView.add(miLaboratory);
            mView.add(miSeminars);        
            mb.add(mView);
        } else {
            
        }

        JMenuItem miLicense = new JMenuItem("License");
        JMenuItem miAbout = new JMenuItem("About");
        mAbout.add(miLicense);
        mAbout.add(miAbout);

        
        
        mb.add(mAbout);
        miLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TraySupport.getInstance().removeTray();
                setVisible(false);
                dispose();
                LoginView v = new LoginView();
                v.setVisible(true);
            }
        });
        miQuit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        this.setJMenuBar(mb);
    }

    public void exitApplication() {
        watch = false;  //stop thread
        System.exit(0);
    }

    public static MainView getInstance() {
        return MainViewHolder.INSTANCE;
    }

    private static class MainViewHolder {

        private static final MainView INSTANCE = new MainView();
    }
}
