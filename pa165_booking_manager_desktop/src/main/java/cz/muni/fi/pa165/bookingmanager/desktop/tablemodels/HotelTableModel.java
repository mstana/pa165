/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.desktop.tablemodels;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mstana
 */
public class HotelTableModel extends AbstractTableModel {
    
     private static final long serialVersionUID = 1L;
    
    private List<HotelTO> hotels = new ArrayList<>();

    @Override
    public int getRowCount() {
        return hotels.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Address";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HotelTO hotel = hotels.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hotel.getId();
            case 1:
                return hotel.getName();
            case 2:
                return hotel.getAddress();
            default:
                throw new IllegalArgumentException("columnIndex");
        } 
    }
    
    public void addHotel(HotelTO hotel) {
        if (hotel != null) {
            hotels.add(hotel);
            int lastRow = getRowCount() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
    }
    
    public void updateHotel(HotelTO hotel, int row) {
        if (hotel != null) {
            hotels.set(row, hotel);
            fireTableRowsUpdated(row, row);
        }
    }

    public void setHotels(List<HotelTO> hotels) {
        if (hotels != null) {
            this.hotels = new ArrayList<>(hotels);
            fireTableDataChanged();
        }
    }

    public void removeHotel(HotelTO hotel, int row) {
        if (hotel != null) {
            hotels.remove(hotel);
            fireTableRowsDeleted(row, row);
        }
    }
    
    
}
