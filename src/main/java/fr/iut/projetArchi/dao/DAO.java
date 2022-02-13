package fr.iut.projetArchi.dao;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class DAO {


    private ResultSet rs;
    private Connection cn;

    public DAO() {
//            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "poujolm", "071563154FB");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "naberte", "040010611EG");

        } catch (SQLException | ClassNotFoundException e) {
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


    private void func_264852_p(PreparedStatement ps, int index, Object value) throws SQLException {
        switch (value.getClass().getSimpleName()) {
            case "Integer" -> ps.setInt(index,(Integer) value);
            case "String" -> ps.setString(index,(String) value);
            case "Float" -> ps.setFloat(index,(Float) value);
        }
    }

    /**
     *
     * @param values
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet create(Map<String,Object> values) throws SQLException {
        //creation debut string sql
        StringBuilder sql = new StringBuilder("INSERT INTO " + nameTable() + "(");

        //parcours keys pour INSERT INTO table (key1,key2,...)
        for (String key : values.keySet()) {
            sql.append(key).append(",");
        }

        // suppression de la dernière virgule
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));

        // suite de la string sql ajout de (?,?,?,...) après VALUES
        sql.append(") VALUES (").append(String.join("", Collections.nCopies(values.size(), "?,")));

        // suppression de la dernière virgule
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));

        //fin de la string sql
        sql.append(");");

        System.out.println(sql);
//        PreparedStatement ps = requetePrepare(sql.toString());
//
//        int i = 1;
//        for (Map.Entry<String,Object> entry: values.entrySet()) {
//            func_264852_p(ps,i,entry.getValue());
//            i++;
//        }
//        return ps.executeQuery();
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("test", 2);
        map.put("test1", 2);
        map.put("test2", 2);
        map.put("test3", 2);
        map.put("test4", 2);

        try {
            new ProduitDAO().create(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param values
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet update(Map<String, Object> values, int id) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE " + nameTable() + " SET ");

        for (String key : values.keySet()) {
            sql.append(key).append(" = ?,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(" WHERE ").append(idTable()).append(" = ?");

        System.out.println(sql);

        PreparedStatement ps = requetePrepare(sql.toString());

        int i = 1;

        for (Map.Entry<String,Object> entry: values.entrySet()) {
            func_264852_p(ps,i,entry.getValue());
            i++;
        }

        func_264852_p(ps,i,id);
        return ps.executeQuery();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    protected ResultSet delete(int id) throws SQLException {
        PreparedStatement ps = requetePrepare("DELETE FROM " + nameTable() + " WHERE " + idTable() + " = ?");
        ps.setInt(1,id);
        return ps.executeQuery();
    }

    /**
     *
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet findAll() throws SQLException {
        PreparedStatement ps = requetePrepare("SELECT * FROM " + nameTable());
        return ps.executeQuery();
    }

    /**
     *
     * @param id ID de l'élement
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet find(int id) throws SQLException {
        PreparedStatement ps = requetePrepare("SELECT * FROM " + nameTable() + " WHERE " + idTable() + " = ?");
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    /**
     * retourne le nom de la table
     * @return String
     */
    protected abstract String nameTable();

    /**
     * retourne l'id de la table
     * @return String
     */
    protected abstract String idTable();

    /**
     * Lance les fixtures afin de remplir la BD pour des tests
     */
    protected abstract void startFixtures();

}
