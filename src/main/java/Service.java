
/**
 *
 * @author steisy crisostomo
 */
import Modelos.MdPersona;
import java.sql.SQLException;
import modulos.Utilidades;
import static spark.Spark.post;

public class Service {

    public static void main(String[] args) {

        MdPersona mdPersona = new MdPersona();

        post("/get_persona", (request, response) -> {
            try {
                return mdPersona.buscarPersona();
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });

        post("/crear_persona", (request, response) -> {
            try {
                mdPersona.crearPersona(Utilidades.jsonConvertJsonToMap(request.queryParams("json")));
                return Utilidades.SOF(1);
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });

        post("/modificar_persona", (request, response) -> {
            try {
                mdPersona.modificarPersona(Utilidades.jsonConvertJsonToMap(request.queryParams("json")));
                return Utilidades.SOF(1);
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });

        post("/eliminar_persona", (request, response) -> {
            try {
                mdPersona.eliminarPersona(Utilidades.jsonConvertJsonToMap(request.queryParams("json")));
                return Utilidades.SOF(1);
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });
    }
}
