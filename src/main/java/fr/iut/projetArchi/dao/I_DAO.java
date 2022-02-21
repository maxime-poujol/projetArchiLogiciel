package fr.iut.projetArchi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface I_DAO {

    /**
     *
     * @param values
     * @throws SQLException
     */
    void create(Map<String,Object> values) throws SQLException;

    /**
     *
     * @param values
     * @throws SQLException
     */
    void update(Map<String,Object> values) throws SQLException;

    /**
     *
     * @param id
     * @throws SQLException
     */
    void delete(int id) throws SQLException;

    /**
     *
     * @return
     */
    ResultSet findAll() throws SQLException;

    /**
     *
     * @return
     */
    ResultSet find(int id) throws SQLException;

    /**
     *
     * @return the name of the table
     */
    String nameTable();

    /**
     *
     * @return the id of the table
     */
    String idTable();

}
