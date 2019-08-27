package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Commande;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CommandeController extends Controller {

    public Result getAll() {
        Object liste = Commande.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_commande = request.body().asJson();
        Commande commande = new ObjectMapper().treeToValue(json_node_commande, Commande.class);
        Client.db().save(commande);
        return ok(Json.toJson(commande));
    }



    public Result getById(Long commandeId) {
        Commande commande = Commande.find.query().select("*").where().eq("id_commande", commandeId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object commandeTest = Commande.find.query().select("*").where().eq("id_commande", commandeId).findOne();

        if(commandeTest != null) {
            return ok(Json.toJson(commande));
        } else {
            return badRequest("Commande of id: " + commandeId+ " not found");
        }

    }



    public Result update(Http.Request request, Long commandeId) throws JsonProcessingException {
        JsonNode commande_to_be_modified_temp = request.body().asJson();
        Commande commande_to_be_modified = new ObjectMapper().treeToValue(commande_to_be_modified_temp, Commande.class);

        List<Commande> all_commande = Commande.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_commande.forEach(database_commande -> {
            if(database_commande.idCmde == commandeId) {
                if (commande_to_be_modified.idCmde == commandeId ) {
                    Commande.db().update(commande_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given commande was not found");
        else if (test.get() == 119)
            return badRequest("The given commande's id and the commande to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(commande_to_be_modified)));
    }
    
}
