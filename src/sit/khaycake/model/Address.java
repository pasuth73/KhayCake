/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.khaycake.model;

import java.sql.ResultSet;
import java.util.List;

import sit.khaycake.database.Column;
import sit.khaycake.database.ORM;
import sit.khaycake.database.SQL;


/**
 * @author -milk
 */
public class Address implements ORM{
    private int id;
    private SubDistrict subDistrict;
    private String street;
    private String addrAdd;
    private String addrNo;

    public static final String TABLE_NAME = "addresses";
    public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "ADDR_ID");
    public static final Column COLUMN_SUB_DISTRICT_ID = ORM.column(TABLE_NAME, "SUDT_ID");
    public static final Column COLUMN_ADDR_NO = ORM.column(TABLE_NAME, "ADDR_NO");
    public static final Column COLUMN_STREET = ORM.column(TABLE_NAME, "STREET");
    public static final Column COLUMN_ADDR_ADD = ORM.column(TABLE_NAME, "ADDR_ADD");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddrAdd() {
        return addrAdd;
    }

    public void setAddrAdd(String addrAdd) {
        this.addrAdd = addrAdd;
    }

    public String getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(String addrNo) {
        this.addrNo = addrNo;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
    }

    public void orm(ResultSet rs) throws Exception {
        this.setId(rs.getInt(COLUMN_ID.getColumnName()));
        this.setSubDistrict((SubDistrict) SQL.findById(SubDistrict.class, rs.getInt(COLUMN_SUB_DISTRICT_ID.getColumnName())));
        this.setAddrNo(rs.getString(COLUMN_ADDR_NO.getColumnName()));
        this.setStreet(rs.getString(COLUMN_STREET.getColumnName()));
        this.setAddrAdd(rs.getString(COLUMN_ADDR_ADD.getColumnName()));
    }
}
