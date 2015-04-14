package sit.khaycake.model;

import sit.khaycake.database.ORM;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Falook Glico on 4/14/2015.
 */
public class Lid implements ORM {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void orm(ResultSet rs) throws SQLException {
        this.setId(rs.getInt(1));
    }

    @Override
    public String toString() {
        return "Lid{" +
                "id=" + id +
                '}';
    }
}
