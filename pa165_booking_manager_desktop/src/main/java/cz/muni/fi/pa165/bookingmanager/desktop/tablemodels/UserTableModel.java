/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.desktop.tablemodels;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mstana
 */
public class UserTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;
    
    private List<UserTO> users = new ArrayList<>();

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "First name";
            case 2:
                return "Last name";
            case 3:
                return "Email";     
            case 4:
                return "Is Admin";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserTO user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
            case 3:
                return user.getEmail();
            case 4:
                return user.getIsAdmin().toString();
            default:
                throw new IllegalArgumentException("columnIndex");
        } 
    }
    
    public void addClient(UserTO user) {
        if (user != null) {
            users.add(user);
            int lastRow = getRowCount() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
    }
    
    public void updateUser(UserTO user, int row) {
        if (user != null) {
            users.set(row, user);
            fireTableRowsUpdated(row, row);
        }
    }

    public void setUsers(List<UserTO> user) {
        if (users != null) {
            this.users = new ArrayList<>(users);
            fireTableDataChanged();
        }
    }

    public void removeUser(UserTO user, int row) {
        if (user != null) {
            users.remove(user);
            fireTableRowsDeleted(row, row);
        }
    }
    
    
}
