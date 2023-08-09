package com.backend.integrador.dao.impl;

import com.backend.integrador.dao.H2Connection;
import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class OdontologoDaoH2 implements IDao <Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
        Odontologo odontologo1 = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, odontologo.getMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            connection.commit();

            //select a la base de datos para obtener lo que se registro mas su id
            odontologo1 = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            ResultSet key = ps.getGeneratedKeys();
            while (key.next()){
                odontologo1.setId(key.getInt(1));
            }
            LOGGER.info("Odontologo Registrado: " + odontologo1);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return odontologo1;
        }

    @Override
    public Odontologo buscarPorId(int id) {
        Odontologo odontologo = null;

        Connection connection = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MEDICAMENTOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                odontologo = crearObjetoOdontologo(rs);

            }
            LOGGER.info("Se ha encontrado el medicamento " + odontologo);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }
    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        int matricula = rs.getInt("codigo");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("laboratorio");

        return new Odontologo(id, matricula, nombre, apellido);

    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }
}
