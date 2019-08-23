package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Sortie;
import models.Client;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class SortieController {

    public Result getAll() {
        Object liste = Sortie.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_sortie = request.body().asJson();
        Sortie sortie = new ObjectMapper().treeToValue(json_node_sortie, Sortie.class);
        Client.db().save(sortie);
        return ok(Json.toJson(sortie));
    }



    public Result getById(Long sortieId) {
        Sortie sortie = Sortie.find.query().select("*").where().eq("id_sortie", sortieId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object sortieTest = Sortie.find.query().select("*").where().eq("id_sortie", sortieId).findOne();

        if(sortieTest != null) {
            return ok(Json.toJson(sortie));
        } else {
            return badRequest("Sortie of id: " + sortieId+ " not found");
        }

    }



    public Result update(Http.Request request, Long sortieId) throws JsonProcessingException {
        JsonNode sortie_to_be_modified_temp = request.body().asJson();
        Sortie sortie_to_be_modified = new ObjectMapper().treeToValue(sortie_to_be_modified_temp, Sortie.class);

        List<Sortie> all_sortie = Sortie.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_sortie.forEach(database_sortie -> {
            if(database_sortie.idSortie == sortieId) {
                if (sortie_to_be_modified.idSortie == sortieId ) {
                    Sortie.db().update(sortie_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given sortie was not found");
        else if (test.get() == 119)
            return badRequest("The given sortie's id and the sortie to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(sortie_to_be_modified)));
    }
    
}
