package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Client;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import models.Achat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class AchatController {

    public Result getAll() {
        Object liste = Achat.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_achat = request.body().asJson();
        Achat achat = new ObjectMapper().treeToValue(json_node_achat, Achat.class);
        Client.db().save(achat);
        return ok(Json.toJson(achat));
    }



    public Result getById(Long achatId) {
        Achat achat = Achat.find.query().select("*").where().eq("id_achat", achatId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object achatTest = Achat.find.query().select("*").where().eq("id_achat", achatId).findOne();

        if(achatTest != null) {
            return ok(Json.toJson(achat));
        } else {
            return badRequest("Achat of id: " + achatId+ " not found");
        }

    }



    public Result update(Http.Request request, Long achatId) throws JsonProcessingException {
        JsonNode achat_to_be_modified_temp = request.body().asJson();
        Achat achat_to_be_modified = new ObjectMapper().treeToValue(achat_to_be_modified_temp, Achat.class);

        List<Achat> all_achat = Achat.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_achat.forEach(database_achat -> {
            if(database_achat.idAchat == achatId) {
                if (achat_to_be_modified.idAchat == achatId ) {
                    Achat.db().update(achat_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given achat was not found");
        else if (test.get() == 119)
            return badRequest("The given achat's id and the achat to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(achat_to_be_modified)));
    }
}
