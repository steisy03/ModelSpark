
/**
 *
 * @author steisy crisostomo
 */
import Modelos.MdPersona;
import Modelos.MdTipoPersona;
import java.sql.SQLException;
import modulos.Utilidades;
import static spark.Spark.post;

public class Service {

    public static void main(String[] args) {

        MdPersona mdPersona = new MdPersona();
        MdTipoPersona mdTipoPersona = new MdTipoPersona();

        post("/get_persona", (request, response) -> {
            try {
                return mdPersona.buscarPersona();
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });
        
        post("/get_persona/:nombre", (request, response) -> {
            try {
                return Utilidades.SOF(mdPersona.buscarPersona(request.params(":nombre")));
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
        //
        //tipo persona
        //
        post("/get_tipo_persona", (request, response) -> {
            try {
                return mdTipoPersona.buscarTipoPersona();
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });
        
        post("/crear_tipo_persona", (request, response) -> {
            try {
                mdTipoPersona.crearTipoPersona(Utilidades.jsonConvertJsonToMap(request.queryParams("json")));
                return Utilidades.SOF(1);
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });  

        post("/modificar_tipo_persona", (request, response) -> {
            try {
                mdTipoPersona.modificarTipoPersona(Utilidades.jsonConvertJsonToMap(request.queryParams("json")));
                return Utilidades.SOF(1);
            } catch (SQLException e) {
                return Utilidades.SOF(e.getMessage());
            }
        });
    }
}
