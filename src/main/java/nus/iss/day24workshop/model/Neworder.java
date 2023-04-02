package nus.iss.day24workshop.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Neworder {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;

    public Neworder() {
    }

    public Neworder(Date orderDate, String customerName, String shipAddress, String notes) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Neworder [orderDate=" + orderDate + ", customerName=" + customerName + ", shipAddress=" + shipAddress
                + ", notes=" + notes + "]";
    }

    
    
    
    

    


    
}
