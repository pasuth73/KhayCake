/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.khaycake.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sit.khaycake.database.Column;
import sit.khaycake.database.ORM;

/**
 *
 * @author -milk
 */
public class Payment implements ORM {

    public enum Status{

    }

    private int id;
    private int orderId;
    private int baacId;
    private int pastId;
    private Date dateTime;
    private double amount;
    
    public static final String TABLE_NAME = "PAYMENTS";
    public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "PATM_ID");
    public static final Column COLUMN_ORDER_ID = ORM.column(TABLE_NAME, "ORDER_ID");
    public static final Column COLUMN_BAAC_ID = ORM.column(TABLE_NAME, "BAAC_ID");
    public static final Column COLUMN_PAST_ID = ORM.column(TABLE_NAME, "PAST_ID");
    public static final Column COLUMN_DATE_TIME = ORM.column(TABLE_NAME, "DATE_TIME");
    public static final Column COLUMN_AMOUNT = ORM.column(TABLE_NAME, "AMOUNT");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBaacId() {
        return baacId;
    }

    public void setBaacId(int baacId) {
        this.baacId = baacId;
    }

    public int getPastId() {
        return pastId;
    }

    public void setPastId(int pastId) {
        this.pastId = pastId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void orm(ResultSet rs) throws SQLException {
        
        this.setId(rs.getInt(COLUMN_ID.getColumnName()));
        this.setOrderId(rs.getInt(COLUMN_ORDER_ID.getColumnName()));
        this.setBaacId(rs.getInt(COLUMN_BAAC_ID.getColumnName()));
        this.setPastId(rs.getInt(COLUMN_PAST_ID.getColumnName()));
        this.setDateTime(rs.getDate(COLUMN_DATE_TIME.getColumnName()));
        this.setAmount(rs.getDouble(COLUMN_AMOUNT.getColumnName()));
        
    }
}
