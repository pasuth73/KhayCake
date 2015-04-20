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
import sit.khaycake.database.SQL;
import sit.khaycake.database.exception.ColumnValueMismatchException;

/**
 * @author -milk
 */
public class BankAccount implements ORM {

    public enum Type{

        SAVING(1),
        CURRENT(2);

        private BankAccountType type;
        private int id;
        private String name;

        Type(int id){
            try {
                this.id = id;
                BankAccountType bt = (BankAccountType) SQL.findById(BankAccountType.class, id);
                this.name = (bt == null) ? null : bt.getName();
            }catch(Exception ex){
                //แก้ไข จาก โยน Exception ออกไป เป็น try catch
            }
        }



        public static Type getType(int id){
            switch (id)
            {
                case 1: return SAVING;
                case 2: return CURRENT;
                default:return SAVING;
            }
        }
        public int getId(){return this.id;}

    }

    private int id;
    private Bank.Branch branch;
    private Type type;
    private String accNo;
    private String accName;

    public static final String TABLE_NAME = "BANKACCOUNTS";
    public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "BAAC_ID");
    public static final Column COLUMN_BABR_ID = ORM.column(TABLE_NAME, "BABR_ID");
    public static final Column COLUMN_BAAT_ID = ORM.column(TABLE_NAME, "BAAT_ID");
    public static final Column COLUMN_ACC_NO = ORM.column(TABLE_NAME, "ACC_NO");
    public static final Column COLUMN_ACC_NAME = ORM.column(TABLE_NAME, "ACC_NAME");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bank.Branch getBranch() {
        return branch;
    }

    public void setBranch(Bank.Branch branch) {
        this.branch = branch;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String acc_name) {
        this.accName = accName;
    }

    public void orm(ResultSet rs) throws Exception {
        this.setId(rs.getInt(COLUMN_ID.getColumnName()));
        this.setBranch((Bank.Branch) SQL.findById(Bank.Branch.class,rs.getInt(COLUMN_BABR_ID.getColumnName())));
        this.setType(Type.getType(rs.getInt(COLUMN_BAAT_ID.getColumnName())));
        this.setAccNo(rs.getString(COLUMN_ACC_NO.getColumnName()));
        this.setAccName(rs.getString(COLUMN_ACC_NAME.getColumnName()));
    }

    public void save() throws NoSuchFieldException, IllegalAccessException {
        SQL insert = new SQL();
        insert.into(TABLE_NAME,COLUMN_ACC_NAME,COLUMN_ACC_NO,COLUMN_BAAT_ID,COLUMN_BAAT_ID,COLUMN_BABR_ID)
                .values(this.accName,this.accNo,this.type.getId(),this.branch.getId());
    }

}
