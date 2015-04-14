package sit.khaycake.model;



import sit.khaycake.database.Column;
import sit.khaycake.database.ORM;
import sit.khaycake.database.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Falook Glico on 4/12/2015.
 */
public class Bank implements ORM {

    public static class Branch implements ORM {
        private int id;
        private String name;
        private Bank bank;

        public static final String TABLE_NAME = "BANK_BRANCHES";
        public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "BABR_ID");
        public static final Column COLUMN_NAME_TH = ORM.column(TABLE_NAME, "NAME_TH");
        public static final Column COLUMN_NAME_EN = ORM.column(TABLE_NAME, "NAME_EN");
        public static final Column COLUMN_BANK_ID = ORM.column(TABLE_NAME, "BANK_ID");
        public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);
        public static final List<Column> COLUMN_KEYWORD = ORM.columns(COLUMN_NAME_EN, COLUMN_NAME_TH);

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Bank getBank() {
            return bank;
        }

        public void setBank(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void orm(ResultSet rs) throws Exception {
            this.setId(rs.getInt(COLUMN_ID.getColumnName()));
            this.setName(rs.getString(COLUMN_ID.getColumnName()));
            this.setBank((Bank)SQL.findById(Bank.class,rs.getInt(COLUMN_BANK_ID.getColumnName())));
        }
    }

    private int id;
    private String name;

    public static final String TABLE_NAME = "BANKS";
    public static final Column COLUMN_ID = ORM.column(TABLE_NAME, "BANK_ID");
    public static final Column COLUMN_NAME_TH = ORM.column(TABLE_NAME, "NAME_TH");
    public static final Column COLUMN_NAME_EN = ORM.column(TABLE_NAME, "NAME_EN");
    public static final List<Column> PRIMARY_KEY = ORM.columns(COLUMN_ID);
    public static final List<Column> COLUMN_KEYWORD = ORM.columns(COLUMN_NAME_EN, COLUMN_NAME_TH);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void orm(ResultSet rs) throws SQLException {
        this.setId(rs.getInt(COLUMN_ID.getColumnName()));
        this.setName(rs.getString(COLUMN_NAME_EN.getColumnName()));
    }

    public List<Branch> getBranchList(){
        return null;
    }
}
