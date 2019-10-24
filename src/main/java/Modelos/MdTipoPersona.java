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
 * @author Steisy Crisostomo
 */
public class MdTipoPersona {

    private final String sqlCrear = "INSERT INTO tipo_persona(descripcion) VALUES (?)";
    private final String sqlModficar = "UPDATE tipo_persona SET descripcion = ? WHERE id = ?";
    private final String sqlBuscar = "SELECT id, descripcion FROM tipo_persona";
    private final Conexion conexion;

    public MdTipoPersona() {
        conexion = new Conexion();
    }

    public void crearTipoPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlCrear)) {
            pstmt.setString(1, map.get("descripcion").toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void modificarTipoPersona(Map map) throws SQLException {
        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sqlModficar)) {
            pstmt.setString(1, map.get("descripcion").toString());
            pstmt.setInt(2, (Integer) map.get("id"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public String buscarTipoPersona() throws SQLException {
        String lista;
        Map<String, Object> map = new HashMap<>();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sqlBuscar);
            map.put("f_tipo_persona", Utilidades.resultSetToList(pstmt.executeQuery()));
            lista = Utilidades.jsonConvertMapToJson(map);
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
}
