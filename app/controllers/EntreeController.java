package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Client;
import models.Entree;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EntreeController extends Controller {

    public Result getAll() {
        Object liste = Entree.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_entree = request.body().asJson();
        Entree entree = new ObjectMapper().treeToValue(json_node_entree, Entree.class);
        Client.db().save(entree);
        return ok(Json.toJson(entree));
    }



    public Result getById(Long entreeId) {
        Entree entree = Entree.find.query().select("*").where().eq("id_entree", entreeId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object entreeTest = Entree.find.query().select("*").where().eq("id_entree", entreeId).findOne();

        if(entreeTest != null) {
            return ok(Json.toJson(entree));
        } else {
            return badRequest("Entree of id: " + entreeId+ " not found");
        }

    }



    public Result update(Http.Request request, Long entreeId) throws JsonProcessingException {
        JsonNode entree_to_be_modified_temp = request.body().asJson();
        Entree entree_to_be_modified = new ObjectMapper().treeToValue(entree_to_be_modified_temp, Entree.class);

        List<Entree> all_entree = Entree.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_entree.forEach(database_entree -> {
            if(database_entree.idEntree == entreeId) {
                if (entree_to_be_modified.idEntree == entreeId ) {
                    Entree.db().update(entree_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given entree was not found");
        else if (test.get() == 119)
            return badRequest("The given entree's id and the entree to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(entree_to_be_modified)));
    }

}
