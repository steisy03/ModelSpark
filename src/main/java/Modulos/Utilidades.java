/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author Y700
 */
public class Utilidades {

    public static String jsonConvertMapToJson(Map map) {
        return new JSONObject(map).toString();
    }

    public static Map jsonConvertJsonToMap(String json) {
        return new JSONObject(json).toMap();
    }

    public static String SOF(int opt) {
        Map<String, Object> res = new HashMap<>();
        res.put("respuesta", opt);
        return Utilidades.jsonConvertMapToJson(res);
    }

    public static String SOF(String opt) {
        Map<String, Object> res = new HashMap<>();
        res.put("respuesta", opt);
        return Utilidades.jsonConvertMapToJson(res);
    }

    public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }
    
    public static String convertMD5(String s) throws NoSuchAlgorithmException {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }

}
