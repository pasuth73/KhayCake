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
 *
 * @author -milk
 */
public class District implements ORM {
    private int id;
    private Province province;
    private String name;
    
    public static final String TABLE_NAME = "DISTRICT";
    public static final Column COLUMN_DIST_ID = ORM.column(TABLE_NAME, "DIST_ID");
    public static final Column COLUMN_PROV_ID = ORM.column(TABLE_NAME, "PROV_ID");
    public static final Column COLUMN_NAME = ORM.column(TABLE_NAME, "NAME");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_DIST_ID);
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void orm(ResultSet rs) throws Exception {
        
        this.setId(rs.getInt(COLUMN_DIST_ID.getColumnName()));
        this.setProvince((Province) SQL.findById(Province.class, (rs.getObject(COLUMN_PROV_ID.getColumnName()))));
        this.setName(rs.getString(COLUMN_NAME.getColumnName()));
        
    }

    public List<SubDistrict> getSubDistrictList(){
        return null;
    }
    
    
}
