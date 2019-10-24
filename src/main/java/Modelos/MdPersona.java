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

    private final String sqlCrear = "INSERT INTO persona(nombre,apellido,estado,id_tipo_persona) VALUES (?,?,?,?)";
    private final String sqlModficar = "UPDATE persona SET nombre = ?, apellido = ?, estado = ?, id_tipo_persona = ?  WHERE id = ?";
    private final String sqlBuscar = "select p.*, t.descripcion as tipo_persona from persona p\n"
            + "inner join tipo_persona t\n"
            + "on t.id = p.id_tipo_persona";
    private final String sqlValidar = "SELECT * FROM persona WHERE nombre = ?";
    private final Conexion conexion;

    public MdPersona() {
        conexion = new Conexion();
    }

    public void crearPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlCrear)) {
            pstmt.setString(1, map.get("nombre").toString());
            pstmt.setString(2, map.get("apellido").toString());
            pstmt.setBoolean(3, (Boolean) map.get("estado"));
            pstmt.setInt(4, (Integer) map.get("id_tipo_persona"));
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
            pstmt.setBoolean(3, (Boolean) map.get("estado"));
            pstmt.setInt(4, (Integer) map.get("id_tipo_persona"));
            pstmt.setInt(5, (Integer) map.get("id"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public String buscarPersona() throws SQLException {
        String lista;
        Map<String, Object> map = new HashMap<>();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sqlBuscar);
            map.put("f_persona", Utilidades.resultSetToList(pstmt.executeQuery()));
            lista = Utilidades.jsonConvertMapToJson(map);
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public int buscarPersona(String nombre) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlValidar)) {
            pstmt.setString(1, nombre);
            if (pstmt.executeQuery().next()) {
                return 1;
            }
        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }
}
