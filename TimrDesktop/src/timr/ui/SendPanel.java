/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import timr.model.MainModel;
import timr.model.user.Faculty;
import timr.model.user.Prof;
import timr.service.Services;

/**
 *
 * @author Kiro
 */
public class SendPanel extends javax.swing.JPanel {
 
    /**
     * Creates new form SendPanel
     */
    public SendPanel() {
        initComponents();      
        jComboBox1.getModel().addListDataListener(comboModified);
    }
    
    
    
    public void updatePanel(){
        jComboBox1.removeAllItems();
        System.err.println("jComboBox1 = " + jComboBox1);
        
        for (Faculty faculty : MainModel.getInstance().prof.faculties){           
            jComboBox1.addItem(faculty.name);
        }       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(300, 275));
        setRequestFocusEnabled(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        setLayout(layout);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = -189;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(12, 8, 0, 0);
        add(jComboBox1, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.ipady = 210;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 13, 12, 0);
        add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Faculty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(17, 10, 0, 0);
        add(jLabel1, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timr/icons/feed_notification.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 1, 12, 0);
        add(jButton2, gridBagConstraints);

        jLabel2.setText("To");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        add(jLabel2, gridBagConstraints);

        jTextField1.setToolTipText("List of groups separated by \",\"");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        add(jTextField1, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timr/icons/add.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jButton1, gridBagConstraints);

        jLabel3.setText("Send message to groups");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabel3, gridBagConstraints);

        jLabel4.setText("Timr feed provider");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(jLabel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //on send
        String name = jComboBox1.getSelectedItem().toString();
        Prof prof = MainModel.getInstance().prof;
        Faculty faculty = prof.getFacultyByName(name);
        String message = jTextArea1.getText();
        
        List<String> groupIds = new LinkedList<String>();
        
        String inputText = jTextField1.getText();
        StringTokenizer st = new StringTokenizer(inputText, " \n\t\r,");
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            String _id = faculty.groups.get(token);
            if (_id!=null && (!_id.isEmpty())){
                groupIds.add(_id);
            }
        }
        if (groupIds.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error no valid group name found", "Timr Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (message.isEmpty()){
                JOptionPane.showMessageDialog(this, "You cannot send an empty message feed", "Timr Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Services.insertFeed(faculty.name, prof.user, groupIds, message);
                JOptionPane.showMessageDialog(this, "Feed sent succesfully","Timr",JOptionPane.INFORMATION_MESSAGE);
                jTextArea1.setText("");
                jTextField1.setText("");
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //add groups
        AddGroupsDialog dialog = new AddGroupsDialog();
        String name = jComboBox1.getSelectedItem().toString();
        Faculty faculty = MainModel.getInstance().prof.getFacultyByName(name);
        dialog.setData(faculty.groups.keySet());
        dialog.setVisible(true);
        
        if (dialog.ok){
            for (String string : dialog.selected){
                jTextField1.setText(jTextField1.getText()+", " + string);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private ListDataListener comboModified = new ListDataListener() {

            @Override
            public void intervalAdded(ListDataEvent e) {               
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                jTextField1.setText("");
            }
        };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
