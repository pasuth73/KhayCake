/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.khaycake.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sit.khaycake.database.Column;
import sit.khaycake.database.ORM;

/**
 *
 * @author -milk
 */
public class ProductSale implements ORM {
    
    private int id;
    private int qty;
    private double priceV;
    private double priceN;
    private int prodId;
    private int unitId;
    
    public static final String TABLE_NAME = "PRODUCT_SALES";
    public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "PRSA_ID");
    public static final Column COLUMN_QTY = ORM.column(TABLE_NAME, "QTY");
    public static final Column COLUMN_PRICE_V = ORM.column(TABLE_NAME, "PRICE_V");
    public static final Column COLUMN_PRICE_N = ORM.column(TABLE_NAME, "PRICE_N");
    public static final Column COLUMN_PROD_ID = ORM.column(TABLE_NAME, "PROD_ID");
    public static final Column COLUMN_UNIT_ID = ORM.column(TABLE_NAME, "UNIT_ID");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPriceV() {
        return priceV;
    }

    public void setPriceV(double priceV) {
        this.priceV = priceV;
    }

    public double getPriceN() {
        return priceN;
    }

    public void setPriceN(double priceN) {
        this.priceN = priceN;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    
     public void orm(ResultSet rs) throws SQLException {
        
        this.setId(rs.getInt(COLUMN_ID.getColumnName()));
        this.setQty(rs.getInt(COLUMN_QTY.getColumnName()));
        this.setPriceV(rs.getDouble(COLUMN_PRICE_V.getColumnName()));
        this.setPriceN(rs.getDouble(COLUMN_PRICE_N.getColumnName()));
        this.setProdId(rs.getInt(COLUMN_PROD_ID.getColumnName()));
        this.setUnitId(rs.getInt(COLUMN_UNIT_ID.getColumnName()));
        
        
        
    }
    
    
}
