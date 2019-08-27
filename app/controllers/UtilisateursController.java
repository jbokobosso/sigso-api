package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Utilisateurs;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class UtilisateursController extends Controller {

    public Result getAll() {
        Object liste = Utilisateurs.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_utilisateurs = request.body().asJson();
        Utilisateurs utilisateurs = new ObjectMapper().treeToValue(json_node_utilisateurs, Utilisateurs.class);
        Client.db().save(utilisateurs);
        return ok(Json.toJson(utilisateurs));
    }



    public Result getById(Long utilisateursId) {
        Utilisateurs utilisateurs = Utilisateurs.find.query().select("*").where().eq("id_utilisateur", utilisateursId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object utilisateursTest = Utilisateurs.find.query().select("*").where().eq("id_utilisateur", utilisateursId).findOne();

        if(utilisateursTest != null) {
            return ok(Json.toJson(utilisateurs));
        } else {
            return badRequest("Utilisateurs of id: " + utilisateursId+ " not found");
        }

    }



    public Result update(Http.Request request, Long utilisateursId) throws JsonProcessingException {
        JsonNode utilisateurs_to_be_modified_temp = request.body().asJson();
        Utilisateurs utilisateurs_to_be_modified = new ObjectMapper().treeToValue(utilisateurs_to_be_modified_temp, Utilisateurs.class);

        List<Utilisateurs> all_utilisateurs = Utilisateurs.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_utilisateurs.forEach(database_utilisateurs -> {
            if(database_utilisateurs.idUtilisateur == utilisateursId) {
                if (utilisateurs_to_be_modified.idUtilisateur == utilisateursId ) {
                    Utilisateurs.db().update(utilisateurs_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given utilisateurs was not found");
        else if (test.get() == 119)
            return badRequest("The given utilisateurs's id and the utilisateurs to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(utilisateurs_to_be_modified)));
    }
}
