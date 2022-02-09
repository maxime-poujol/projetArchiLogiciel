package fr.iut.projetArchi.dao;

import java.sql.*;
import java.util.Map;

public abstract class DAO {


    private ResultSet rs;
    private Connection cn;

    public DAO() {
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "poujolm", "071563154FB");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param sql requete à la BD
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement requetePrepare(String sql) throws SQLException {
        return cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


    /**
     *
     * @param values
     * @return
     * @throws SQLException
     */
    protected ResultSet create(Map<String,Object> values) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(nameTable()).append(" VALUES(");
        for(Map.Entry<String, Object> entry : values.entrySet()) {
            sql.append("?,");
        }
        PreparedStatement ps = requetePrepare("INSERT INTO " + nameTable() + "VALUES ()");
        return ps.executeQuery();
    }

    /**
     *
     * @param map
     * @return
     * @throws SQLException
     */
    protected ResultSet update(Map<String, Object> map) throws SQLException {
        PreparedStatement ps = requetePrepare("UPDATE " + nameTable() + "SET");
        return ps.executeQuery();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    protected ResultSet delete(String id) throws SQLException {
        PreparedStatement ps = requetePrepare("DELETE FROM " + nameTable() + "WHERE id = ?");
        ps.setString(1,id);
        return ps.executeQuery();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    protected ResultSet findAll() throws SQLException {
        PreparedStatement ps = requetePrepare("SELECT * FROM " + nameTable());
        return ps.executeQuery();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    protected ResultSet find(int id) throws SQLException {
        PreparedStatement ps = requetePrepare("SELECT * FROM " + nameTable() + " WHERE " + idTable() + " = ?");
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    /**
     *
     * @return le nom de la table
     */
    protected abstract String nameTable();

    /**
     *
     * @return l'id de la table
     */
    protected abstract String idTable();

    /**
     * Lance les fixtures afin de remplir la BD pour des tests
     */
    protected abstract void startFixtures();

}
