/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.desktop;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.desktop.rest.HotelRESTManager;
import cz.muni.fi.pa165.bookingmanager.desktop.tablemodels.HotelTableModel;
import cz.muni.fi.pa165.bookingmanager.desktop.tablemodels.UserTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.desktop.rest.UserRESTManager;
import javax.swing.*;
/**
 *
 * @author mstana
 */
public class Main extends javax.swing.JFrame {

    private static UserRESTManager userRESTManager = new UserRESTManager();
    private static HotelRESTManager hotelRESTManager = new HotelRESTManager();
    private UserTableModel userTableModel = new UserTableModel();
    private HotelTableModel hotelTableModel = new HotelTableModel();
/**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private void initTableModels() {
        userTable.setModel(userTableModel);
        hotelTable.setModel(hotelTableModel);
    }

    public void refreshUserTable() {
        try {
            userTableModel.setUsers(userRESTManager.findAllUsers());
            userTableModel.fireTableDataChanged();

        } catch (ClientHandlerException ex) {
            JOptionPane.showMessageDialog(this, "Server connection is unavailable. Please contact the administrator for further information. The application will now close.", "Cannot connect to server.", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (UniformInterfaceException uie) {
            if (uie.getResponse().getStatus() == 500) {
                JOptionPane.showMessageDialog(this, "Error on server side. Contact administrator for more information", "Error while getting hotel list.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private UserTO getSelectedUser(int row) {
        try {
            return userRESTManager.findUser((Long) userTable.getValueAt(row, 0));
        } catch (UniformInterfaceException uie) {
            int status = uie.getResponse().getStatus();
            switch (status) {
                case 500:
                    JOptionPane.showMessageDialog(this, "Error on server side. Contact administrator for more information", "Error while getting client list.", JOptionPane.ERROR_MESSAGE);
                    break;
                case 404:
                    JOptionPane.showMessageDialog(this, "Client does not exist anymore. The client might have been deleted already.", "Error while getting client info.", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void refreshHotelTable() {
        try {

            hotelTableModel.setHotels(hotelRESTManager.findAllHotels());
            hotelTableModel.fireTableDataChanged();

        } catch (ClientHandlerException ex) {
            JOptionPane.showMessageDialog(this, "Server connection was not established correctly. Application is closing.", "No server connection.", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (UniformInterfaceException uie) {
            if (uie.getResponse().getStatus() == 500) {
                JOptionPane.showMessageDialog(this, "Error", "Error in getting hotel list", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private HotelTO getSelectedHotel(int row) {
        try {
            return hotelRESTManager.findHotel((Long) hotelTable.getValueAt(row, 0));
        } catch (ClientHandlerException ex) {
            JOptionPane.showMessageDialog(this, "Server connection was not established correctly. Application is closing.", "No server connection.", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (UniformInterfaceException uie) {
            int status = uie.getResponse().getStatus();
            switch (status) {
                case 500:
                    JOptionPane.showMessageDialog(this, "Error", "Error in getting hotel list", JOptionPane.ERROR_MESSAGE);
                    break;
                case 404:
                    JOptionPane.showMessageDialog(this, "Hotel does not exist.", "Error in getting hotel detail", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonCreateUser = new javax.swing.JButton();
        jButtonUpdateUser = new javax.swing.JButton();
        jButtonDeleteUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jButtonDeleteUser1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        hotelTable = new javax.swing.JTable();
        jButtonCreateHotel = new javax.swing.JButton();
        jButtonUpdateHotel = new javax.swing.JButton();
        jButtonDeleteHotel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItemExit = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jButtonCreateUser.setText("Create");
        jButtonCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateUserActionPerformed(evt);
            }
        });

        jButtonUpdateUser.setText("Update");
        jButtonUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateUserActionPerformed(evt);
            }
        });

        jButtonDeleteUser.setText("Delete");

        jLabel1.setText("List Of Users");

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "User Name", "User Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jButtonDeleteUser1.setText("Delete");
        jButtonDeleteUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteUser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButtonCreateUser)
                        .addGap(69, 69, 69)
                        .addComponent(jButtonUpdateUser)
                        .addGap(75, 75, 75)
                        .addComponent(jButtonDeleteUser1)
                        .addGap(283, 283, 283)
                        .addComponent(jButtonDeleteUser))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreateUser)
                    .addComponent(jButtonUpdateUser)
                    .addComponent(jButtonDeleteUser)
                    .addComponent(jButtonDeleteUser1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Users", jPanel1);

        jLabel2.setText("List Of Hotels");

        hotelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Address"
            }
        ));
        jScrollPane3.setViewportView(hotelTable);

        jButtonCreateHotel.setText("Create");
        jButtonCreateHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateHotelActionPerformed(evt);
            }
        });

        jButtonUpdateHotel.setText("Update");
        jButtonUpdateHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateHotelActionPerformed(evt);
            }
        });

        jButtonDeleteHotel.setText("Delete");
        jButtonDeleteHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteHotelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButtonCreateHotel)
                        .addGap(56, 56, 56)
                        .addComponent(jButtonUpdateHotel)
                        .addGap(77, 77, 77)
                        .addComponent(jButtonDeleteHotel)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreateHotel)
                    .addComponent(jButtonUpdateHotel)
                    .addComponent(jButtonDeleteHotel))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hotels", jPanel2);

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        MenuItemExit.setText("About");
        MenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExitActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemExit);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void MenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExitActionPerformed
        new AboutDialog().setVisible(true);
    }//GEN-LAST:event_MenuItemExitActionPerformed

    private void jButtonCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateUserActionPerformed
        new UserDialog(userTableModel).setVisible(true);
    }//GEN-LAST:event_jButtonCreateUserActionPerformed

    private void jButtonCreateHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateHotelActionPerformed
        new HotelDialog(hotelTableModel).setVisible(true); 
    }//GEN-LAST:event_jButtonCreateHotelActionPerformed

    private void jButtonUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateUserActionPerformed
        if (userTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Select a user to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            new UserDialog(getSelectedUser(userTable.getSelectedRow()), userTableModel).setVisible(true);
        }
    }//GEN-LAST:event_jButtonUpdateUserActionPerformed

    private void jButtonDeleteHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteHotelActionPerformed
      if (hotelTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a hotel you to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this hotel?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    int status = hotelRESTManager.deleteHotel(getSelectedHotel(hotelTable.getSelectedRow())).getStatus();
                    switch (status) {
                        case 404:
                            JOptionPane.showMessageDialog(this, "Selected hotel cannot be deleted. The hotel is not present in the databse anymore - The record might have been deleted by someone else.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 417:
                            JOptionPane.showMessageDialog(this, "Selected hotel cannot be deleted. The hotel still has an existing room.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 500:
                            JOptionPane.showMessageDialog(this, "There was an error on the server side. Please contact the administrator for furhter information.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    refreshHotelTable();
                } catch (ClientHandlerException che) {
                    JOptionPane.showMessageDialog(this, "Server connection was lost. Please check your connection, or contact the administrator for further information. The application will now close.", "Cannot connect to server.", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(this, "Cannot delete a nonexistent hotel.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonDeleteHotelActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
            switch (jTabbedPane1.getSelectedIndex()) {
            case 0:
                if (!userTable.getModel().equals(userTableModel)) {
                    initTableModels();
                }
                refreshUserTable();
                break;
            case 1:
                if (!hotelTable.getModel().equals(hotelTableModel)) {
                    initTableModels();
                }
                refreshHotelTable();
                break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButtonUpdateHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateHotelActionPerformed
        new HotelDialog(hotelTableModel, getSelectedHotel(hotelTable.getSelectedRow())).setVisible(true);
    }//GEN-LAST:event_jButtonUpdateHotelActionPerformed

    private void jButtonDeleteUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteUser1ActionPerformed
              if (userTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a user you to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    int status = userRESTManager.deleteUser(getSelectedUser(userTable.getSelectedRow())).getStatus();
                    switch (status) {
                        case 404:
                            JOptionPane.showMessageDialog(this, "Selected user cannot be deleted. The user is not present in the databse anymore - The record might have been deleted by someone else.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 417:
                            JOptionPane.showMessageDialog(this, "Selected user cannot be deleted. The user still has an existing room.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 500:
                            JOptionPane.showMessageDialog(this, "There was an error on the server side. Please contact the administrator for furhter information.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    refreshUserTable();
                } catch (ClientHandlerException che) {
                    JOptionPane.showMessageDialog(this, "Server connection was lost. Please check your connection, or contact the administrator for further information. The application will now close.", "Cannot connect to server.", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(this, "Cannot delete a nonexistent user.", "Error while deleting.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonDeleteUser1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemExit;
    private javax.swing.JTable hotelTable;
    private javax.swing.JButton jButtonCreateHotel;
    private javax.swing.JButton jButtonCreateUser;
    private javax.swing.JButton jButtonDeleteHotel;
    private javax.swing.JButton jButtonDeleteUser;
    private javax.swing.JButton jButtonDeleteUser1;
    private javax.swing.JButton jButtonUpdateHotel;
    private javax.swing.JButton jButtonUpdateUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
