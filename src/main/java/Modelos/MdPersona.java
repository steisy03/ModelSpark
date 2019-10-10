package Modelos;

import Modulos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import modulos.Utilidades;

/**
 *
 * @author steisy crisostomo
 */

public class MdPersona {
    
    private final String sqlCrear = "INSERT INTO persona(nombre,apellido) VALUES (?,?)";
    private final String sqlModficar = "UPDATE persona SET nombre = ?, apellido = ?  WHERE id = ?";
    private final String sqlEliminar = "UPDATE persona SET estado = ? WHERE id = ?";
    private final String sqlBuscar = "SELECT id, nombre, apellido FROM persona";
    private final Conexion conexion;

    public MdPersona() {
        conexion = new Conexion();
    }

    public void crearPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlCrear)) {
            pstmt.setString(1, map.get("nombre").toString());
            pstmt.setString(2, map.get("apellido").toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void modificarPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlModficar)) {
            pstmt.setString(1, map.get("nombre").toString());
            pstmt.setString(2, map.get("apellido").toString());
            pstmt.setInt(3, (Integer) map.get("id"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public String buscarPersona() throws SQLException {
        String lista;
        Map<String,Object> map = new HashMap<>();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sqlBuscar);
            map.put("f_persona",Utilidades.resultSetToList(pstmt.executeQuery()));
            lista = Utilidades.jsonConvertMapToJson(map);
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public void eliminarPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlEliminar)) {
            pstmt.setInt(1, 0);
            pstmt.setInt(2, (Integer) map.get("id"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
